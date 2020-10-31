package com.cardIntegration.roiim.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Card {
    public String lastDigits;
    public CardExpiry cardExpiry;
    public String cardBin;
    public String cardType;
    public String holderName;
}
