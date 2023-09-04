package com.lianne.properties;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

public class XMLAuthorizationAuditProperties {

    private static String GOAL_ELEMENT;
    private static XMLAuthorizationAuditProperties instance;

    private XMLAuthorizationAuditProperties() throws IOException
    {
        Properties props = new Properties();
        props.load(Files.newInputStream(new File("config/config.ini").toPath()));

        GOAL_ELEMENT = props.getProperty("GOAL_ELEMENT");
    }

    public String getGoalElement() {
        return GOAL_ELEMENT;
    }

    public static XMLAuthorizationAuditProperties getInstance() throws IOException {
        if (instance == null) {
            instance = new XMLAuthorizationAuditProperties();
        }
        return instance;
    }
}
