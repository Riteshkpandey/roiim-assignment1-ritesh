package com.cardIntegration.roiim.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentHandle {
    public String id;
    public String status;
    public String usage;
    public String paymentType;
    public String paymentHandleToken;
    public String billingDetailsId;
    public Card card;
}
