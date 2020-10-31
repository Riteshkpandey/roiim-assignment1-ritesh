package com.cardIntegration.roiim.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewCustomerDto {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String contact_number;
    private String paysafeID;

}
