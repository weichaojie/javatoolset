package com.weichaojie;

import java.lang.Thread;

import org.apache.log4j.*;
import util.HttpClientUtil;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class);
    private static String urlAddr = "http://www.baidu.com/";

    public static void main(String[] args) {
        // write your code here
//        TestMultiGetHttpData();

        TestImportLogforJava();

    }

    private static void TestMultiGetHttpData() {
        for (int i = 0; i < Long.MAX_VALUE; i++) {
            try {
                String result = HttpClientUtil.doGet(urlAddr);
                logger.info(result);
                Thread.sleep(2000);

            } catch (Exception e) {
                logger.warn(e.getLocalizedMessage());
            }
        }
    }

    private static void TestImportLogforJava() {
        logger.debug("This is debug message.");
        logger.warn("This is a warning message");
        //        logger.info("This is info message.");
//        logger.error("This is error message.");

    }

}


