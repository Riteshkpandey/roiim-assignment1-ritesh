package com.cardIntegration.roiim.service;

import com.cardIntegration.roiim.Entity.Customer;
import com.cardIntegration.roiim.dto.RequestDtos.CustomerRequestDto;
import com.cardIntegration.roiim.dto.RequestDtos.MakePaymentRequestDto;
import com.cardIntegration.roiim.dto.RequestDtos.PaymentHandlerTokenRequestDto;
import com.cardIntegration.roiim.dto.ResponseDtos.CustomerResponseDto;
import com.cardIntegration.roiim.dto.ResponseDtos.MakePaymentResponseDto;
import com.cardIntegration.roiim.dto.ResponseDtos.PaymentHandlerTokenResponseDto;
import com.cardIntegration.roiim.model.DOB;
import com.cardIntegration.roiim.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

@Service
public class CheckoutService {

    private CustomerRepository customerRepository;
    private RestTemplate restTemplate = new RestTemplate();
    private String baseurl;
    private String APIKeyId;
    private String APIKeyPassword;
    private Random rd;
    public CheckoutService(){}

    @Autowired
    public CheckoutService(CustomerRepository customerRepository ){

        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
        baseurl = "https://api.test.paysafe.com/paymenthub/v1";
        APIKeyId = "private-7751";
        APIKeyPassword = "B-qa2-0-5f031cdd-0-302d0214496be84732a01f690268d3b8eb72e5b8ccf94e2202150085913117f2e1a8531505ee8ccfc8e98df3cf1748";
        rd = new Random();
    }


    public PaymentHandlerTokenResponseDto creatSingleUserCustomerToken(CustomerRequestDto CustomerRequestDto){
        Customer customer=null;
        customer = customerRepository.findByEmail( CustomerRequestDto.getEmail());
        if(customer==null)
            customer =  fetchingCustomer(CustomerRequestDto);

        // get API url in the string
        String url = baseurl + "/customers/" + customer.getPaysafeId() + "/singleusecustomertokens" ;

        // create new http header and set content type to application/json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // set basic authorization with api key and its value
        headers.setBasicAuth( APIKeyId, APIKeyPassword );

        // create request object
        PaymentHandlerTokenRequestDto PaymentHandlerTokenRequestDto = new PaymentHandlerTokenRequestDto();
        PaymentHandlerTokenRequestDto.setMerchantRefNum( String.valueOf( rd.nextInt() ) );
        PaymentHandlerTokenRequestDto.setPaymentTypes( Arrays.asList( "CARD" ) );

        // convert request object in to json object
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString( PaymentHandlerTokenRequestDto );
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // build the request
        HttpEntity< String > entity = new HttpEntity<>( jsonString, headers );

        // send POST request
        ResponseEntity< PaymentHandlerTokenResponseDto > responseEntity = restTemplate.postForEntity( url, entity, PaymentHandlerTokenResponseDto.class );

        if( responseEntity.getStatusCode().equals( HttpStatus.CREATED ) ) {
            PaymentHandlerTokenResponseDto PaymentHandlerTokenResponseDto = responseEntity.getBody();
            PaymentHandlerTokenResponseDto.setMerchantRefNum(PaymentHandlerTokenRequestDto.getMerchantRefNum());
//            System.out.println(responseEntity.getBody());
            return responseEntity.getBody();
        }

        return null;
    }



    public Customer fetchingCustomer(CustomerRequestDto CustomerRequestDto){


        String url = baseurl + "/customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // set basic authorization with api key and its value
        headers.setBasicAuth( APIKeyId, APIKeyPassword );

//        // create a new map for the body of the request and put all the values received from the user in the map
        CustomerRequestDto.setMerchantCustomerId( String.valueOf( rd.nextInt() ) );
        CustomerRequestDto.setLocale( "en_US" );
        CustomerRequestDto.setMiddleName( "pqr" );
        DOB dob = new DOB( 2, 3, 1998 );
        CustomerRequestDto.setDateOfBirth( dob );
        CustomerRequestDto.setCellPhone( "9056482124" );
        CustomerRequestDto.setGender( "M" );
        CustomerRequestDto.setNationality( "Canadian" );
        CustomerRequestDto.setIp( "192.0.126.111" );




        // convert request object in to json object
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(CustomerRequestDto);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // build the request
        HttpEntity< String > entity = new HttpEntity< String >( jsonString, headers );
        // send POST request
        ResponseEntity<CustomerResponseDto> responseEntity = restTemplate.postForEntity( url, entity, CustomerResponseDto.class );
        // check if user is successfully created
        if( responseEntity.getStatusCode() == HttpStatus.CREATED ){

            // get the response
            CustomerResponseDto response = responseEntity.getBody();

            // create new record for the customer in local database and set it's attributes values
            Customer newCustomer = new Customer();
            newCustomer.setPaysafeId( response.getId() );
            newCustomer.setEmail( response.getEmail() );
            customerRepository.save( newCustomer );

            return newCustomer;

        }
        else {

            System.out.println( "failed user creation" );
            Customer c = null;
            return c;
        }
    }

    public MakePaymentResponseDto makePayment( MakePaymentRequestDto makePaymentRequestDto ){

//        System.out.println( "In make payment" );

        // create an url for the payemnt api
        String url = baseurl + "/payments";

        // create new http header and set content type to application/json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // set basic authorization with api key and its value
        headers.setBasicAuth( APIKeyId, APIKeyPassword );

        makePaymentRequestDto.setCustomerIp( "10.10.12.64" );
        makePaymentRequestDto.setMerchantRefNum( String.valueOf( rd.nextInt() ) );

        // convert request object into json object
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {

            jsonString = objectMapper.writeValueAsString( makePaymentRequestDto );
        }
        catch (JsonMappingException e) {
            e.printStackTrace();
        }
        catch (JsonGenerationException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // build the request
        HttpEntity< String > entity = new HttpEntity<>( jsonString, headers );

        // send POST request
        ResponseEntity<MakePaymentResponseDto> responseEntity = restTemplate.postForEntity( url, entity, MakePaymentResponseDto.class );

        if( responseEntity.getStatusCode() != HttpStatus.CREATED ){

            // throw an exception
        }

        return responseEntity.getBody();
    }
}
