package io.zipcoder.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * @ATTENTION_TO_STUDENTS - You are forbidden from modifying this class
 */
public class FileReader {
    public static String readFile(String fileName) {
        try {
            return IOUtils.toString(FileReader.class.getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
            throw new Error(e);
        }
    }
}
