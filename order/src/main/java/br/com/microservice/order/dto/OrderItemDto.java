package br.com.microservice.order.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private Integer amount;
    private String description;
}
