package com.cardIntegration.roiim.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GatewayResponse {
    public String authCode;
    public String avsResponse;
    public String cvvVerification;

}
