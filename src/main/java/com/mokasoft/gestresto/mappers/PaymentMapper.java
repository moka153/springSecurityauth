package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.PaymentRequest;
import com.mokasoft.gestresto.dtos.PaymentResponse;
import com.mokasoft.gestresto.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment paymentRequestToPayment(PaymentRequest paymentRequest){
        Payment payment = Payment.builder()
                .paymentType(paymentRequest.getPaymentType())
                .payment(paymentRequest.getPayment())
                .build();
        return payment;
    }
    public PaymentResponse paymentToPaymentResponse(Payment payment){
        PaymentResponse paymentResponse = PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .paymentType(payment.getPaymentType())
                .payment(payment.getPayment())
                .saleId(payment.getSale().getSaleId())
                .build();
        return paymentResponse;
    }
}
