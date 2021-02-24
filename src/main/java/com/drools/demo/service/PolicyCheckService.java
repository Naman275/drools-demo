package com.drools.demo.service;

import com.drools.demo.dto.GenericResponse;
import com.drools.demo.dto.GenericDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PolicyCheckService {
    GenericResponse validateCreditPolicyCheck(MultipartFile file, GenericDto appFormString) throws IOException;
}
