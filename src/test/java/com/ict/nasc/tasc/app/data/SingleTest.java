/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.tasc.app.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import com.ict.nasc.tasc.model.TrainResult;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: SingleTest.java, v 0.1 2015-10-20 下午3:57:14  Exp $
 */
public class SingleTest extends TestCase {

    /**
     * 
     */
    public void testDataRelation() {
        /**
        System.out.println(655.0 * 0.3450381679389313);
        //System.out.println(655.0 * 0.4102406428073628);
        //System.out.println(655.0 * 0.3819363114159835);

        System.out.println(1419.0 * 0.4102406428073628);
        System.out.println(1419.0 * 0.3819363114159835);

        System.out.println(655.0 / 0.4102406428073628);
        System.out.println(655.0 / 0.3819363114159835);

        System.out.println(226.0 / 0.4102406428073628);
        System.out.println(226.0 / 0.3819363114159835);

        System.out.println(300.0 / 0.4102406428073628);
        System.out.println(300.0 / 0.3819363114159835);
        **/
        List<Integer> lists = new ArrayList<Integer>();
        for (int i = 1; i < 20; i++) {
            lists.add(i);
        }
        /**
        for (int i = 1; i < 20; i++) {
            if(i==15){
                lists.remove(i-1);
            }
        }**/
        System.out.println(lists.size());
        for (int i = 0; i < lists.size(); i++) {
            //System.out.print("The " + i + "th is :");
            //System.out.println(lists.get(i) );
        }
        Set<TrainResult> sets = new HashSet<TrainResult>();
        for (int i = 1; i < 20; i++) {
            TrainResult t = new TrainResult();
            sets.add(t);
        }
        System.out.println(sets.size());

    }

    /**
     * 
     */
    public void test2() {
        System.out.println(System.getProperty("PATH"));
    }

}
