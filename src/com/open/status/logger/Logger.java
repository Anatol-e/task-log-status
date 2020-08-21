package com.open.status.logger;

import java.util.HashMap;
import java.util.Map;

public class Logger {

    private final String className;

    private static Map<String, Logger> loggerMap;

    private static final String SPACE = " ";
    private static final String SPLITTER = " : ";

    private Logger(String className) {
        this.className = className;
    }

    public static Logger getLogger(String className) {
        if (loggerMap == null) {
            loggerMap = new HashMap<>();
        }
        Logger logger = loggerMap.get(className);
        if (logger == null) {
            logger = new Logger(className);
            loggerMap.put(className, logger);
        }
        return logger;
    }

    public void print(String message) {
        String sb = className +
                SPACE +
                SPLITTER +
                message;
        System.out.println(sb);
    }


}
