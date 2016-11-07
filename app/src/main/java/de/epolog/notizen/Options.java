package de.epolog.notizen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Options {

    public static final String DN_MODE_ID = "dnMode";
    public static final String START_TIME_ID = "startTime";
    public static final String END_TIME_ID = "endTime";
    public static final String BC1_ID = "bc1";
    public static final String TC1_ID = "tc1";
    public static final String BC2_ID = "bc2";
    public static final String TC2_ID = "tc2";

    private boolean dnMode;
    private int startTime;
    private int endTime;
    private String bc1;
    private String tc1;
    private String bc2;
    private String tc2;

    private File config;

    public Options(){}

    public Options(File file) throws IOException {
        this.config = file;
        this.load();
    }

    public void load() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(config), "UTF-8"));
        String line = input.readLine();
        while(line != null){
            int index = line.indexOf(':');
            if(index != -1 && line.length() >= 9){
                String field = line.substring(0, index);
                String value = line.substring(index + 1, line.length() - 1);
                switch(field){
                    case DN_MODE_ID:
                        dnMode = (value.equals("true") ? true : false);
                        break;
                    case START_TIME_ID:
                        startTime = Integer.parseInt(value);
                        break;
                    case END_TIME_ID:
                        endTime = Integer.parseInt(value);
                        break;
                    case BC1_ID:
                        bc1 = value;
                        break;
                    case TC1_ID:
                        tc1 = value;
                        break;
                    case BC2_ID:
                        bc2 = value;
                        break;
                    case TC2_ID:
                        tc2 = value;
                }
            }
        }
    }

    public void save(){
        
    }

    public boolean isDnMode() {
        return dnMode;
    }

    public void setDnMode(boolean dnMode) {
        this.dnMode = dnMode;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public String getBc1() {
        return bc1;
    }

    public void setBc1(String bc1) {
        this.bc1 = bc1;
    }

    public String getTc1() {
        return tc1;
    }

    public void setTc1(String tc1) {
        this.tc1 = tc1;
    }

    public String getBc2() {
        return bc2;
    }

    public void setBc2(String bc2) {
        this.bc2 = bc2;
    }

    public String getTc2() {
        return tc2;
    }

    public void setTc2(String tc2) {
        this.tc2 = tc2;
    }
}
