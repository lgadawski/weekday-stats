package com.gadawski.drools;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Scope("prototype")
public class DroolsApp {

    public static final Logger log = LoggerFactory.getLogger(DroolsApp.class);

    public void bootstrapDrools(List<Object> data) {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession();

        // the KieSession was injected so we can use it
        for (Object o : data) {
            kieSession.insert(o);
            log.debug("Insert data - {} ", o);
        }

        int rulesFired = kieSession.fireAllRules();
        kieSession.dispose();

        log.info("Number of rules fired - {}", rulesFired);
    }

    public static void main(String[] args) {
        // Bootstap the CDI container, in this case WELD
        Weld w = new Weld();

        WeldContainer wc = w.initialize();
        DroolsApp app = wc.instance().select(DroolsApp.class).get();
        app.bootstrapDrools(Collections.emptyList());

        w.shutdown();
    }

}
