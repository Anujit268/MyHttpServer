package com.myfullstackprojects.httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.myfullstackprojects.httpserver.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
    private static ConfigurationManager myConfigurationmanager;
    private static Configuration myCurrentConfiguration;
    private ConfigurationManager(){
    }
    public static ConfigurationManager getInstance(){
        if(myConfigurationmanager==null)myConfigurationmanager=new ConfigurationManager();
        return myConfigurationmanager;
    }
    /**
     *
     * Used to load a configuration file by the path provided
     */
    public void loadConfigurationFile(String filepath){
        FileReader fileReader= null;
        try {
            fileReader = new FileReader(filepath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb=new StringBuffer();
        int i;
        while(true){
            try {
                if (!((i=fileReader.read())!=-1)) break;
            } catch (IOException e) {
                throw new HttpConfigurationException(e);
            }
            sb.append((char)i);
        }
        JsonNode conf= null;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing the file",e);
        }
        try {
            myCurrentConfiguration=Json.fromJson(conf,Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the file internally",e);
        }
    }
    /**
     * Returns the current loaded configuration
     */
    public Configuration getConfiguration(){
        if(myCurrentConfiguration==null){
            throw new HttpConfigurationException("No current configuration set.");
        }
        return myCurrentConfiguration;
    }
}
