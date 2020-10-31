package com.cardIntegration.roiim.dto.RequestDtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class PaymentHandlerTokenRequestDto {
    private String merchantRefNum;
    private List< String > paymentTypes;
}
