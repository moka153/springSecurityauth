package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.PaymentRequest;
import com.mokasoft.gestresto.dtos.PaymentResponse;
import com.mokasoft.gestresto.entities.Sale;

import java.util.List;

public interface PaymentService {
    void savePayment(List<PaymentRequest> paymentRequests, Long saleId);
    PaymentResponse updatePayment(PaymentRequest paymentRequest,Long paymentId);
    List<PaymentResponse> findPaymentBySale(Sale sale);
}
