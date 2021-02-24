package com.drools.demo.controller;

import com.drools.demo.dto.GenericDto;
import com.drools.demo.dto.GenericResponse;
import com.drools.demo.service.PolicyCheckService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.drools.demo.CommonConstant.API_VERSION_V1;

@RestController
@RequestMapping(path = API_VERSION_V1)
public class PolicyCheckController extends ApiBaseController{

    @Autowired
    private PolicyCheckService policyCheckService;

    @Autowired
    private ObjectMapper objectMapper;
    @PostMapping(value = "/policy/validate",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GenericResponse> validateCreditPolicyCheck(@RequestPart(value = "file", required = true)
                                                                           MultipartFile file,
                                                                     @RequestPart(value = "input" ,required = true) String genericDtoString
                                                                    ) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GenericDto genericDto=objectMapper.readValue(genericDtoString, GenericDto.class);
        GenericResponse genericResponse = policyCheckService.validateCreditPolicyCheck(file,genericDto);
        return new ResponseEntity<>(genericResponse, headers, HttpStatus.OK);
    }


}