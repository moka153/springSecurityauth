package com.mokasoft.gestresto.services.impl;

import com.mokasoft.gestresto.dtos.PaymentRequest;
import com.mokasoft.gestresto.dtos.PaymentResponse;
import com.mokasoft.gestresto.entities.Payment;
import com.mokasoft.gestresto.entities.Sale;
import com.mokasoft.gestresto.exceptions.NotFoundException;
import com.mokasoft.gestresto.mappers.PaymentMapper;
import com.mokasoft.gestresto.repositories.PaymentRepository;
import com.mokasoft.gestresto.repositories.SaleRepository;
import com.mokasoft.gestresto.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final SaleRepository saleRepository;

    @Override
    public void savePayment(List<PaymentRequest> paymentRequests, Long saleId) {
        if (!saleRepository.existsById(saleId)) {
            throw new NotFoundException("sale not found");
        }
        Sale sale = new Sale();
        sale.setSaleId(saleId);
        List<Payment> payments = paymentRequests.stream()
                .map(paymentRequest -> paymentMapper.paymentRequestToPayment(paymentRequest))
                .collect(Collectors.toList());
        for(Payment p : payments){
            p.setSale(sale);
            paymentRepository.save(p);
        }
    }

    @Override
    public PaymentResponse updatePayment(PaymentRequest paymentRequest, Long paymentId) {
        return null;
    }

    @Override
    public List<PaymentResponse> findPaymentBySale(Sale sale) {
        List<Payment> payments = paymentRepository.findBySale(sale);
        List<PaymentResponse> paymentResponseList = payments.stream()
                .map(payment -> paymentMapper.paymentToPaymentResponse(payment))
                .collect(Collectors.toList());
        return paymentResponseList;
    }
}
