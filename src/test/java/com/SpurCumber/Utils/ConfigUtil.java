package com.SpurCumber.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

    static InputStream inputStream;

    public static String getPropValues(String Property) throws IOException {
        String propValue = "";

        try {
            Properties prop = new Properties();
            String propFileName = "/config.properties";

            inputStream = ConfigUtil.class.getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            propValue = prop.getProperty(Property);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return propValue;
    }
}
