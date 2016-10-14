package com.johnson.myapplication;

import android.os.Environment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Created by johnsmac on 9/21/16.
 */


public class MyApplication extends android.app.Application {

    public Logger gLogger;

    @Override
    public void onCreate() {
        super.onCreate();

        configLog();

    }

    public void configLog() {
        final LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator.setFileName(Environment.getExternalStorageDirectory() + File.separator + "mine_log4j.log");
        // Set the root log level
        logConfigurator.setRootLevel(Level.DEBUG);
        // Set log level of a specific logger
        logConfigurator.setLevel("org.apache", Level.ERROR);
        logConfigurator.configure();
        //gLogger = Logger.getLogger(this.getClass());
        gLogger = Logger.getLogger("MainActivity");

    }
}
