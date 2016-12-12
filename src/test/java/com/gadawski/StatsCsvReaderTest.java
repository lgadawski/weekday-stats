package com.gadawski;

import com.gadawski.reader.CsvReader;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StatsCsvReaderTest {

    @Test
    public void testOpeningCsvFile() {
        // given
        String filePath = "/home/lukasz/IdeaProjects/weekday-stats/src/test/resources/data.csv";

        // when
        CsvReader reader = new CsvReader(filePath);
        List<String> lines = reader.getLines();
        String first = lines.get(0);
        String second = lines.get(1);

        // then
        assertThat(first).isNotNull();
        assertThat(first.charAt(0)).isEqualTo(',');

        assertThat(second).isEqualTo("1,monday,18,18,18,18,18,18,21,21,18,18,18,18,18,18,18,18,18,21,21,21,21,21,21,21,21");
    }

}
