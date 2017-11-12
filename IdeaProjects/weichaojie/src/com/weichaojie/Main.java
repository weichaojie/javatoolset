package com.weichaojie;


import org.apache.log4j.*;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
	// write your code here

        TestImportLogforJava();

    }

    private static void TestImportLogforJava() {
        logger.debug("This is debug message.");
        logger.info("This is info message.");
        logger.error("This is error message.");

    }

}


