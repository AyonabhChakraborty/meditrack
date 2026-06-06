package com.airtribe.meditrack.util;
import com.airtribe.meditrack.constants.Constants;

public final class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();

    private final String appName;
    private final String version;
    private final boolean debugMode;

    private AppConfig(){
        this.appName = Constants.APP_NAME;
        this.version = Constants.APP_VERSION;
        this.debugMode = false;
     }

    public static AppConfig getInstance(){
        return INSTANCE;
    }

    public String getAppName() {
        return appName;
    }

    public String getVersion() {
        return version;
    }

    public boolean isDebugMode() {
        return debugMode;
    }
}