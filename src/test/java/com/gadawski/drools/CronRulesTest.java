package com.gadawski.drools;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class CronRulesTest {

    @Inject
    @KSession()
    private KieSession kieSession;

    public CronRulesTest() {}

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void cronTest() throws InterruptedException {
        assertNotNull(kieSession);

        new Thread(() -> kieSession.fireUntilHalt()).start();

        kieSession.insert("Hi there!");

        for (int i = 0; i < 3; i++) {
            Thread.sleep(1005);

            kieSession.insert("hello + " + i + " ! :D");
        }

        kieSession.halt();
    }

    @Test
    public void normalFiring() {
        assertNotNull(kieSession);

        kieSession.insert(new RuleDate());
        kieSession.insert(new PersonPresence());

        kieSession.fireAllRules();

        kieSession.insert(new RuleDate());

        kieSession.fireAllRules();
    }
}
