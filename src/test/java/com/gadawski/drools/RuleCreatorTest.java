package com.gadawski.drools;

import org.junit.Test;

public class RuleCreatorTest {

    @Test
    public void creatorTest() {
        RuleCreator rc = new RuleCreatoreImpl();

        rc.build();
    }
}
