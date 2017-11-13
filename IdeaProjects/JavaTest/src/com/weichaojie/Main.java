package com.weichaojie;

import Data.FileStreamMetaData;
import Data.MetaData;
import Data.RDBMetaData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.*;


public class Main {

    private static Logger logger = Logger.getLogger("myFirstLog4jTest");

    public static void main(String[] args) {
	// write your code here

//        TestFactoryPattern();
        SortArrayList();
    }

    private static void SortArrayList() {
        List<MetaData>  myArray = new ArrayList<>();
        myArray.add(new RDBMetaData());
        myArray.add(new FileStreamMetaData());
        myArray.add(new RDBMetaData());
        myArray.add(new FileStreamMetaData());
        myArray.add(new RDBMetaData());
        myArray.add(new FileStreamMetaData());

        for (MetaData md : myArray)
            System.out.println(md.getNumberId());

        // use java8 Lambda的list排序方法
        Collections.sort(myArray, Comparator.comparing(MetaData::getNumberId));

        for (MetaData md : myArray)
            System.out.println(md.getNumberId());
    }

    private static void TestFactoryPattern(){
        List<MetaData>  myArray = new ArrayList<>();
        myArray.add(new RDBMetaData());
        myArray.add(new RDBMetaData());
        myArray.add(new FileStreamMetaData());
        myArray.add(new FileStreamMetaData());
        myArray.add(new RDBMetaData());
        myArray.add(new FileStreamMetaData());

        for (MetaData md : myArray)
            md.read();
    }

    private static void TestImportLogforJava() {
        logger.debug("This is debug message.");
        logger.info("This is info message.");
        logger.error("This is error message.");
    }

}
