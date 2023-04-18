package com.mokasoft.gestresto.services.impl;

import com.mokasoft.gestresto.dtos.PaymentRequest;
import com.mokasoft.gestresto.dtos.SaleDetailRequest;
import com.mokasoft.gestresto.dtos.SaleRequest;
import com.mokasoft.gestresto.dtos.SaleResponse;
import com.mokasoft.gestresto.entities.*;
import com.mokasoft.gestresto.enums.SaleStatus;
import com.mokasoft.gestresto.exceptions.NotFoundException;
import com.mokasoft.gestresto.mappers.SaleDetailMapper;
import com.mokasoft.gestresto.mappers.SaleMapper;
import com.mokasoft.gestresto.repositories.*;
import com.mokasoft.gestresto.services.PaymentService;
import com.mokasoft.gestresto.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final AppUserRepository userRepository;
    private final AppTableRepository tableRepository;
    private final ProductRepository productRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final PaymentService paymentService;

    @Override
    public SaleResponse saveSale(SaleRequest saleRequest) {
        if (!saleRequest.getSaleDetail().isEmpty()) {
            if (!userRepository.findById(saleRequest.getUserId()).isPresent()) {
                throw new NotFoundException("user not found");
            }
            if (!tableRepository.findById(saleRequest.getTableId()).isPresent()) {
                throw new NotFoundException("table not found");
            }
            AppUser appUser = userRepository.findById(saleRequest.getUserId()).get();
            AppTable appTable = tableRepository.findById(saleRequest.getTableId()).get();
            Sale sale = saleMapper.saleRequestToSale(saleRequest);

            BigDecimal amount = BigDecimal.ZERO;
            BigDecimal benefit = BigDecimal.ZERO;

            for (SaleDetailRequest sd : saleRequest.getSaleDetail()) {
                Long productId = sd.getProductId();
                Product p = productRepository.findById(productId).get();
                BigDecimal costPrice = p.getCostPrice();
                BigDecimal price = sd.getUnitPrice();
                BigDecimal quantity = sd.getQuantity();
                // TODO check out benefit
                amount = amount.add((price.multiply(quantity)));
                benefit = benefit.add((amount.subtract(costPrice.multiply(quantity))));
            }
            sale.setAmount(amount);
            sale.setBenefit(benefit);
            sale.setAppTable(appTable);
            sale.setAppUser(appUser);
            sale.setSaleStatus(SaleStatus.IN_PROGRESS);
            Sale savedSale = saleRepository.save(sale);
            List<SaleDetail> saleDetails = saleRequest.getSaleDetail()
                    .stream()
                    .map(saleDetailRequest -> new SaleDetailMapper()
                            .saleDetailRequestToSaleDetail(saleDetailRequest))
                    .collect(Collectors.toList());
            for (SaleDetail sd : saleDetails) {
                sd.setSale(savedSale);
                saleDetailRepository.save(sd);
            }
            tableRepository.availableTable(saleRequest.getTableId(), false,
                    saleRequest.getCustomerNumber(), savedSale);

            return saleMapper.saleToSaleResponse(savedSale);
        } else {
            throw new NotFoundException("sale details is empty");
        }
    }

    @Override
    public SaleResponse updateSale(Long saleId) {
        if(!saleRepository.findById(saleId).isPresent()){
            throw new NotFoundException("sale not found");
        }
        Sale sale = saleRepository.findById(saleId).get();
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal benefit = BigDecimal.ZERO;

        for (SaleDetail sd : sale.getSaleDetails()) {
            BigDecimal costPrice = sd.getProduct().getCostPrice();
            BigDecimal price = sd.getUnitPrice();
            BigDecimal quantity = sd.getQuantity();
            amount = amount.add((price.multiply(quantity)));
            benefit = benefit.add((amount.subtract(costPrice.multiply(quantity))));
        }
        sale.setAmount(amount);
        sale.setBenefit(benefit);
        sale.setSaleId(saleId);
        Sale updatedSale = saleRepository.save(sale);
        return saleMapper.saleToSaleResponse(updatedSale);
    }

    @Override
    public void deleteSale(Long saleId) {
        if (!saleRepository.findById(saleId).isPresent()) {
            throw new NotFoundException("sale not found");
        }
        Sale sale = saleRepository.findById(saleId).get();
        if (sale.getSaleStatus().equals(SaleStatus.IN_PROGRESS)) {
            tableRepository.availableTable(sale.getAppTable().getTableId(), true, 0, null);
        }

        saleRepository.deleteById(saleId);
    }

    @Override
    public List<SaleResponse> getAllSales() {
        List<Sale> sales = saleRepository.findAll();
        List<SaleResponse> saleResponses = sales.stream()
                .map(sale -> saleMapper.saleToSaleResponse(sale))
                .collect(Collectors.toList());
        return saleResponses;
    }

    @Override
    public void saleValidation(List<PaymentRequest> paymentRequests,Long saleId) {
        if (!saleRepository.findById(saleId).isPresent()) {
            throw new NotFoundException("sale not found");
        }
        if(paymentRequests.isEmpty()){
            throw new NotFoundException("payments not found");
        }
        saleRepository.saleValidation(saleId);
        Sale sale = saleRepository.findById(saleId).get();
        paymentService.savePayment(paymentRequests,saleId);
        tableRepository.availableTable(sale.getAppTable().getTableId(), true, 0, null);
    }

    @Override
    public SaleResponse getSaleById(Long saleId) {
        if(!saleRepository.existsById(saleId)){
            throw new NotFoundException("sale not found");
        }
        Sale sale = saleRepository.findById(saleId).get();
        return saleMapper.saleToSaleResponse(sale);
    }

}
