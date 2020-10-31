package com.cardIntegration.roiim.dto.ResponseDtos;

import com.cardIntegration.roiim.model.DOB;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class CustomerResponseDto {
    public String id;
    public String merchantCustomerId;
    public String locale;
    public String firstName;
    public String middleName;
    public String lastName;
    public DOB dateOfBirth;
    public String email;
    public String phone;
    public String ip;
    public String gender;
    public String nationality;
    public String cellPhone;
    public String status;
}
