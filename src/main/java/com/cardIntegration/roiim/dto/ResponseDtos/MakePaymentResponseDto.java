package com.cardIntegration.roiim.dto.ResponseDtos;

import com.cardIntegration.roiim.model.GatewayResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MakePaymentResponseDto {
    public String id;
    public Integer amount;
    public String merchantRefNum;
    public Boolean settleWithAuth;
    public String paymentHandleToken;
    public String txnTime;
    public String customerIp;
    public Boolean dupCheck;
    public String description;
    public String currencyCode;
    public String paymentType;
    public String status;
    public Integer availableToSettle;
    public GatewayResponse gatewayResponse;
    public String customerId;
    public String merchantCustomerId;
}
