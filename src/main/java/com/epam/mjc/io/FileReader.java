package com.epam.mjc.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        try {
            String fileData = readFile(file);
            Map<String, String> keyValuePairs = parseData(fileData);
            return createProfile(keyValuePairs);
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private String readFile(File file) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        }
        return fileContent.toString();
    }

    private Map<String, String> parseData(String fileData) {
        Map<String, String> keyValuePairs = new HashMap<>();
        String[] lines = fileData.split("\n");
        for (String line : lines) {
            int colonIndex = line.indexOf(':');
            if (colonIndex != -1) {
                String key = line.substring(0, colonIndex);
                String value = line.substring(colonIndex + 1).trim();
                keyValuePairs.put(key, value);
            }
        }
        return keyValuePairs;
    }

    private Profile createProfile(Map<String, String> keyValuePairs) {
        Profile profile = new Profile();
        profile.setName(keyValuePairs.get("Name"));
        profile.setAge(Integer.valueOf(keyValuePairs.get("Age")));
        profile.setEmail(keyValuePairs.get("Email"));
        profile.setPhone(Long.valueOf(keyValuePairs.get("Phone")));
        return profile;
    }
}
