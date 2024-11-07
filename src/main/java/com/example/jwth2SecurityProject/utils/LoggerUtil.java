package com.example.jwth2SecurityProject.utils;

import org.apache.log4j.Logger;

public class LoggerUtil {

    private LoggerUtil() {}
    /**
     * Returns a Log4j Logger for the calling class.
     * @return a Log4j Logger instance for the calling class
     */
    public static Logger getLogger() {
        // Use StackWalker to find the calling class
        String callingClassName = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass()
                .getName();

        return Logger.getLogger(callingClassName);
    }
}
