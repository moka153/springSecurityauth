package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.PaymentRequest;
import com.mokasoft.gestresto.dtos.PaymentResponse;

import java.util.List;

public interface PaymentService {
    void savePayment(List<PaymentRequest> paymentRequests, Long saleId);
    PaymentResponse updatePayment(PaymentRequest paymentRequest,Long paymentId);
}
