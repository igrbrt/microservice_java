package br.com.microservice.payment.dto;

import br.com.microservice.payment.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDto {
    private Long id;
    private BigDecimal value;
    private String name;
    private String cardNumber;
    private String expirationDate;
    private String cardCode;
    private Status status;
    private Long paymentMethodId;
    private Long orderId;
}
