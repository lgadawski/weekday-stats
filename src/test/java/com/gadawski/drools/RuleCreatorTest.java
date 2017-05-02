package com.gadawski.drools;

import com.gadawski.stats.WeekStats;
import com.gadawski.stats.reader.CsvReader;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class RuleCreatorTest {

    @Test
    public void creatorTest() throws IOException {
        // given
        String parent = "/home/lukasz/IdeaProjects/weekday-stats/src/test/resources/";
        String filePath = parent + "data.csv";

        // when
        CsvReader reader = new CsvReader(filePath);
        WeekStats weekStats = new WeekStats(reader.getData());

        RuleCreator rc = new RuleCreatoreImpl(weekStats);
        String rules = rc.build();

        File file = new File(parent + "rules.drl");
        Files.write(rules.getBytes(), file);
    }
}
