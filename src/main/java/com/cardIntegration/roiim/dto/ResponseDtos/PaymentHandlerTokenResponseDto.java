package com.cardIntegration.roiim.dto.ResponseDtos;

import com.cardIntegration.roiim.model.Address;
import com.cardIntegration.roiim.model.DOB;
import com.cardIntegration.roiim.model.PaymentHandle;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PaymentHandlerTokenResponseDto {

    public String id;
    public String merchantRefNum;
    public String profileId;
    public Integer timeToLiveSeconds;
    public String status;
    public String singleUseCustomerToken;
    public List<String> paymentTypes;
    public String locale;
    public String firstName;
    public String middleName;
    public String lastName;
    public DOB dateOfBirth;
    public String email;
    public String phone;
    public String ip;
    public String nationality;
    public List<Address> addresses;
    public List<PaymentHandle> paymentHandles;

}
