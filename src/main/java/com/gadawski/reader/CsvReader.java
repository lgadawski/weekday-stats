package com.gadawski.reader;

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
        InputStream is = null;
        try {
            is = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        return br.lines().collect(Collectors.toList());
    }
}
