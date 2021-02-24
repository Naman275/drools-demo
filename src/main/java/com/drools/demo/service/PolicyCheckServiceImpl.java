package com.drools.demo.service;

import com.drools.demo.config.RuleEngineConfig;
import com.drools.demo.config.RuleEngineUtil;
import com.drools.demo.dto.GenericResponse;
import com.drools.demo.dto.GenericDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class PolicyCheckServiceImpl implements PolicyCheckService {

    @Autowired
    private RuleEngineConfig ruleEngineConfig;

    @Autowired
    private RuleEngineUtil ruleEngineUtil;

    @Override
    public GenericResponse validateCreditPolicyCheck(MultipartFile file, GenericDto genericDto) throws IOException {
        Map<String,Boolean> rulesResult=null;
        rulesResult = ruleEngineConfig.execute(file.getBytes(), genericDto);
        return prepareResponse(genericDto,rulesResult);
    }

    private GenericResponse prepareResponse(GenericDto genericDto, Map<String,Boolean> rulesResult){
        GenericResponse genericResponse =new GenericResponse();
        genericResponse.setGenericDto(genericDto);
        genericResponse.setRulesExecuted(rulesResult);
        genericResponse.setTotalPassed(ruleEngineUtil.fetchTotalRulesPassed(rulesResult));
        genericResponse.setTotalRules(rulesResult.size());
        genericResponse.setOverallStatus(ruleEngineUtil.fetchOverallResult(rulesResult));
        return genericResponse;
    }
}
