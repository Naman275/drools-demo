package com.drools.demo.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RuleEngineConfig {

    @Autowired
    private RuleEngineUtil ruleEngineUtil;

    public Map<String,Boolean> execute(byte[] fileArray, Object appForm) throws IOException {
        StatelessKieSession kieSession=config(fileArray);
        List<String> totalRulesSet = ruleEngineUtil.getTotalRulesSet(kieSession.getKieBase());
        AgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);
        kieSession.execute( appForm );
        return  getRulesResult(((TrackingAgendaEventListener)agendaEventListener), totalRulesSet);
    }

    private  Map<String,Boolean> getRulesResult(TrackingAgendaEventListener agendaEventListener, List<String> totalRulesSet) {
        Map<String,Boolean> rulesResult=new HashMap<>();
        for(String rule: totalRulesSet) {
            if(agendaEventListener.isRuleFired(rule)) {
                rulesResult.put(rule, true);
                continue;
            }
            rulesResult.put(rule, false);
        }
        return rulesResult;
    }

    public synchronized StatelessKieSession config(byte[] fileArray) throws IOException {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem=kieServices.newKieFileSystem();
        Resource resource=ResourceFactory.newByteArrayResource(fileArray);
        resource.setSourcePath("src/main/resources/ruleFile");
        resource.setResourceType(ResourceType.DRL);
        kieFileSystem.write(resource);
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        KieBase kieBase = kContainer.getKieBase();
        StatelessKieSession kSession = kieBase.newStatelessKieSession();
        return kSession;
    }
}