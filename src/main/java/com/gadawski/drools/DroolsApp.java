package com.gadawski.drools;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DroolsApp {

    public static final Logger log = LoggerFactory.getLogger(DroolsApp.class);

    @Autowired
    private KieSession kieSession;

    public void bootstrapDrools() {
        // the KieSession was injected so we can use it
        LocalDateTime now = LocalDateTime.now();
        RuleDate ruleDate = new RuleDate(now.getDayOfWeek(), now.getHour());

        log.debug("Insert RD - {} ", ruleDate);

        kieSession.insert(ruleDate);

//        kieSession.fireUntilHalt();
        int rulesFired = kieSession.fireAllRules();

        log.info("Fired - {}", rulesFired);
    }

    public static void main(String[] args) {
        // Bootstap the CDI container, in this case WELD
        Weld w = new Weld();

        WeldContainer wc = w.initialize();
        DroolsApp app = wc.instance().select(DroolsApp.class).get();
        app.bootstrapDrools();

        w.shutdown();
    }

}
