package com.example.restcontroller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ImpVerify;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RestVerifyController {
    
    private final IamportClient iamportClient;

    @Value("${iamport.apikey}") String apikey;
    @Value("${iamport.apisecret}") String apisecret;

    final String format = "RestVerifyController => {}";

    public RestVerifyController() {
        this.iamportClient = new IamportClient("1555645806852740", "BS5kGCNjOEqWVTaf2Pt5eyAxVbR5W2afxLMFFFQD9HBFebNWuGBAiniqcFP2RIOsmeCnXQ4Y0wBFzZpY");
    }
    
    @PostMapping(value = "/api/verifyiamport.json")
    public IamportResponse<Payment> paymentyByImUid(@RequestBody ImpVerify obj) throws IamportResponseException, IOException {

        //log.info(format, apikey);
        //log.info(format, apisecret);
        //log.info(format, obj.getImp_uid());

        //log.info(format, "paymentByImpUid 진입");

        return iamportClient.paymentByImpUid(obj.getImp_uid());

    }

}
