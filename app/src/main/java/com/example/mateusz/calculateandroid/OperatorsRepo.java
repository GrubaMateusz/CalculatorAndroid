package com.example.mateusz.calculateandroid;

import java.util.StringTokenizer;

/**
 * Created by Mateusz on 2017-03-22.
 */

public class OperatorsRepo {

    private int pioritet;
    private long wynikl;
    public String a,b,c,d;
    private StringTokenizer weString;


    public OperatorsRepo(String inputString) {
        weString=new StringTokenizer(inputString,"+*");
        while(weString.hasMoreTokens())
        {
            a=weString.nextToken();
            b=weString.nextToken();
            c=weString.nextToken();
            d=weString.nextToken();
        }
        System.out.println(a+"/n"+b+"/n"+c+"/n"+d+"/n");
        System.out.println(inputString);
    }
}
