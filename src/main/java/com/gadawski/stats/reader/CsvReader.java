package com.gadawski.stats.reader;

import com.gadawski.stats.day.DayData;
import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reader is not optimized for big files!
 */
public class CsvReader {
    private final String filePath;

    public CsvReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getLines() {
        BufferedReader br = null;
        try {
            InputStream is = new FileInputStream(new File(filePath));
            br = new BufferedReader(new InputStreamReader(is));

            return br.lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        throw new RuntimeException("Problem while reading file! [pathname='" + filePath + "']");
    }

    public List<DayData> getData() {
        List<String> lines = getLines();
        List<DayData> data = Lists.newArrayList();
        for (String s : lines.subList(1, lines.size())) {
            data.add(new DayData(s));
        }

        return data;
    }
}
