package com.ason.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AppConfig {
    public static String VIDEO_DIR = getDir();
    public static List<String> FILE_LIST=new ArrayList<String>();


    static {
        File dir = new File(VIDEO_DIR);
        for (String file : dir.list()) {
            FILE_LIST.add(file);
        }
    }


    private static String getDir() {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("data.properties"));
            return prop.getProperty("video_dir");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
