package com.cardIntegration.roiim.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    public String id;
    public String nickName;
    public String street;
    public String street2;
    public String city;
    public String state;
    public String country;
    public String zip;
    public String phone;
    public String status;
    public Boolean defaultShippingAddressIndicator;

}
