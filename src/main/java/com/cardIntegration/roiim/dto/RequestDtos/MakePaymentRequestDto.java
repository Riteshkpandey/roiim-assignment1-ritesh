package com.cardIntegration.roiim.dto.RequestDtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MakePaymentRequestDto {
    private String merchantRefNum;
    private Integer amount;
    private String currencyCode;
    private boolean dupCheck;
    private boolean settleWithAuth;
    private String paymentHandleToken;
    private String customerIp;
    private String description;

}
