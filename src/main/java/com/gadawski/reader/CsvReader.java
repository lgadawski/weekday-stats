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
}
