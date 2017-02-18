package com.gadawski.drools;

import com.gadawski.stats.WeekStats;
import com.gadawski.stats.reader.CsvReader;
import org.junit.Test;

public class RuleCreatorTest {

    @Test
    public void creatorTest() {
        // given
        String filePath = "/home/lukasz/IdeaProjects/weekday-stats/src/test/resources/data.csv";

        // when
        CsvReader reader = new CsvReader(filePath);
        WeekStats weekStats = new WeekStats(reader.getData());

        RuleCreator rc = new RuleCreatoreImpl(weekStats);
        rc.build();
    }
}
