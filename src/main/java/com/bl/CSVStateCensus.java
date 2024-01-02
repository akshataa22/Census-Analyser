package com.bl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

class CSVStateCensus implements Iterable<String[]> {
    private String csvFile;

    public CSVStateCensus(String csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public Iterator<String[]> iterator() {
        return new CSVIterator();
    }

    private class CSVIterator implements Iterator<String[]> {
        private BufferedReader reader;

        private CSVIterator() {
            try {
                reader = new BufferedReader(new FileReader(csvFile));
                // Assuming the first line contains headers, so we skip it
                reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean hasNext() {
            try {
                return reader.ready();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public String[] next() {
            try {
                String line = reader.readLine();
                if (line != null) {
                    return line.split(",");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}