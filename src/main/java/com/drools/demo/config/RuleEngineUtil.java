package com.drools.demo.config;

import org.kie.api.KieBase;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RuleEngineUtil {

    public Integer fetchTotalRulesPassed(Map<String,Boolean> rulesSet){
        int passed=0;
        for(String key:rulesSet.keySet()){if(rulesSet.get(key)==true)passed++;}
        return passed;
    }

    public  Boolean fetchOverallResult(Map<String,Boolean> rulesSet){
        for(String key:rulesSet.keySet()){if(rulesSet.get(key)==false)return false;}
        return true;
    }

    public List<String> getTotalRulesSet(KieBase kieBase) {
        List<String> totalRulesSet = new ArrayList<>();
        for ( KiePackage kp : kieBase.getKiePackages() ) {
            for (Rule rule : kp.getRules()) {
                totalRulesSet.add(rule.getName());
            }
        }
        return totalRulesSet;
    }

}
