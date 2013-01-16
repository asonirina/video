package com.ason.configuration;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class AppConfig {

    private static final String PROPERTY_FILE = "data.properties";

    private static AppConfig instance;

    private Properties props = new Properties();

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    private AppConfig() {
        //props = BaseConfig.load(PROPERTY_FILE);
        try {
           // props.load(AppConfig.class.getClassLoader().getResourceAsStream(PROPERTY_FILE));
            props.load(new FileInputStream(PROPERTY_FILE));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Properties getProps() {
        return props;
    }

//    public static String VIDEO_DIR;
//
//    public static List<String> FILE_LIST;

//    public AppConfig() {
//        getDir();
//        getList();
//    }
//    public static void init() {
//        getDir();
//        getList();
//
//    }

//    private static void getList() {
//        FILE_LIST = new ArrayList<String>();
//        File dir = new File(VIDEO_DIR);
//        for (String file : dir.list()) {
//            FILE_LIST.add(file);
//        }
//    }
//
//
//    private static void getDir() {
//        try {
//            Properties prop = new Properties();
//            prop.load(new FileInputStream("data.properties"));
//            VIDEO_DIR = prop.getProperty("video_dir");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }
}
