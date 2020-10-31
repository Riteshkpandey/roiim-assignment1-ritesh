package com.cardIntegration.roiim.dto.RequestDtos;

import com.cardIntegration.roiim.model.DOB;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerRequestDto {
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

}
