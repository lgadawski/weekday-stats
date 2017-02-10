package com.gadawski.drools;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;

import javax.inject.Inject;

public class DroolsApp {

    @Inject
    @KSession()
    private KieSession kieSession;

    private void bootstrapDrools() {
        // the KieSession was injected so we can use it
        kieSession.insert("Hi there!");
//        kieSession.fireUntilHalt();
        int rulesFired = kieSession.fireAllRules();
        System.out.println("Rules fired: " + rulesFired);
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
