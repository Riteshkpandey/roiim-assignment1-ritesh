package com.cardIntegration.roiim.controller;
import com.cardIntegration.roiim.dto.RequestDtos.CustomerRequestDto;
import com.cardIntegration.roiim.dto.RequestDtos.MakePaymentRequestDto;
import com.cardIntegration.roiim.dto.ResponseDto;
import com.cardIntegration.roiim.dto.ResponseDtos.MakePaymentResponseDto;
import com.cardIntegration.roiim.dto.ResponseDtos.PaymentHandlerTokenResponseDto;
import com.cardIntegration.roiim.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RestEndPointController {
    @Autowired
    private CheckoutService checkoutService;
    @PostMapping( "/SingleUseCustomerToken2" )
    public ResponseDto createUserAndSingleUseCustomerToken(@RequestBody CustomerRequestDto customerRequestDto){
        System.out.println("Post method executing SingleUseCustomerToken2");

        ResponseDto<PaymentHandlerTokenResponseDto> responseDto = new ResponseDto< PaymentHandlerTokenResponseDto >();
        responseDto.setStatus( HttpStatus.OK );
        responseDto.setMessage( "SingleUseCustomerToken Created" );
        responseDto.setData(checkoutService.creatSingleUserCustomerToken(customerRequestDto) );
        return responseDto;
    }

    
    @PostMapping( "/MakePayment/{emailId}" )
    public ResponseDto makePayment(@PathVariable String emailId,
                                  @RequestBody MakePaymentRequestDto makePaymentRequestDto ){

        ResponseDto<MakePaymentResponseDto> responseDto = new ResponseDto< MakePaymentResponseDto >();
        responseDto.setStatus( HttpStatus.OK );
        responseDto.setMessage( "Payment Done Successfully" );
        responseDto.setData( checkoutService.makePayment( makePaymentRequestDto ) );
        return responseDto;
    }
}
