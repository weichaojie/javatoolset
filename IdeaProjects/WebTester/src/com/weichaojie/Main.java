package com.weichaojie;

import org.apache.log4j.*;
import util.HttpClientUtil;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class);
    private static String test_url = "http://www.baidu.com/";

    public static void main(String[] args) {
	// write your code here
        TestHttpClientUtil();
    }

    private static void TestHttpClientUtil(){

        try{
            String result = HttpClientUtil.doGet(test_url);
            logger.info(result);
        }
        catch (Exception e){
            logger.error("httpclient 获取数据发生异常！");
        }

    }
}
