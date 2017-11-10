package com.weichaojie;

import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Data.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        SortArrayList();
    }

    private static void SortArrayList() {
        List<MetaData>  myArray = new ArrayList<MetaData>();
        myArray.add(new RDBMetaData());
        myArray.add(new FileStreamMetaData());
        myArray.add(new RDBMetaData());
        myArray.add(new FileStreamMetaData());
        myArray.add(new RDBMetaData());
        myArray.add(new FileStreamMetaData());

        for (MetaData md : myArray)
            System.out.println(md.getNumberId());

        MyComparator mc = new MyComparator();
        Collections.sort(myArray, mc);

        for (MetaData md : myArray)
            System.out.println(md.getNumberId());
    }
}
