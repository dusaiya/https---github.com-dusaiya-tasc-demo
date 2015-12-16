/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.tasc.app.fact.find.population;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * 生成population训练文本
 * 运行环境 spring 框架 +  jdbc(mysql 包) + junit(jar包)
 * jar包建议maven管理 
 * @author xueye.duanxy
 * @version $Id: PopulationTest.java, v 0.1 2015-12-8 下午3:29:38  Exp $
 */
public class PopulationTest extends TestCase {

    /**生成population训练文本核心程序**/
    public void testGenCsv() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            String url = "jdbc:mysql://10.60.1.92:3306/factFind?"
                         + "user=root&password=ictsoft&useUnicode=true&characterEncoding=UTF8";
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sql = "select id, contributor from pop_contributor_2 order by id asc";
            ResultSet rs = stmt.executeQuery(sql);

            List<Contributor> contributorList = new ArrayList<PopulationTest.Contributor>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String contributor = rs.getString("contributor");
                contributorList.add(new Contributor(id, contributor));
            }
            System.out.println("city,year,population" + listNameToStr(contributorList));
            //System.out.println(contributorList.size());
            //System.out.println(listToStr(contributorList));
            //cleanClaimMsg(contributorList);
            //System.out.println(listToStr(contributorList));
            sql = "select city_full_name, year,population,matrix_id,contributor,claim_pop from pop_final_2 "
                  + "order by city_full_name , matrix_id asc ";
            rs = stmt.executeQuery(sql);
            String city = "";
            String year = null;
            String population = null;
            while (rs.next()) {
                String newCity = rs.getString("city_full_name").replace(",", "_");
                if ("".equals(city)) {
                    city = newCity;
                } else if (!city.equals(newCity)) {
                    System.out.println(city + "," + year + "," + population
                                       + listToStr(contributorList));
                    cleanClaimMsg(contributorList);
                    city = newCity;
                }

                //if(!city.equals(newCity)){
                //    if(city)
                //}
                city = newCity;
                year = rs.getString("year");
                population = rs.getString("population").replace("\r", "").replace("\n", "");
                String claimPop = rs.getString("claim_pop").replace("\r", "").replace("\n", "");
                int id = rs.getInt("matrix_id");
                Contributor ctb = contributorList.get(id - 1);
                ctb.setClaimMsg(claimPop);
            }
            System.out.println(city + "," + year + "," + population + listToStr(contributorList));
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException;" + e);
        } catch (SQLException e) {
            System.out.println("SQLException:" + e);
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

    }

    /**
     * 
     * 
     * @author xueye.duanxy
     * @version $Id: PopulationTest.java, v 0.1 2015-12-8 下午3:35:06  Exp $
     */
    public class Contributor {
        /**矩阵中的 matrix_id **/
        private Integer id;
        /**claim贡献者**/
        private String  contributor;
        /**claim信息**/
        private String  claimMsg;

        /**
         * 用来从数据库读取contributor的数据进行初始化
         * claimMsg为数据库没有的字段，构造时进行初始化位空字段
         * @param id
         * @param contributor
         */
        public Contributor(Integer id, String contributor) {
            this.id = id;
            this.contributor = contributor;
            this.claimMsg = "";

        }

        /**
         * Getter method for property <tt>id</tt>.
         * 
         * @return property value of id
         */
        public Integer getId() {
            return id;
        }

        /**
         * Getter method for property <tt>contributor</tt>.
         * 
         * @return property value of contributor
         */
        public String getContributor() {
            return contributor;
        }

        /**
         * Getter method for property <tt>claimMsg</tt>.
         * 
         * @return property value of claimMsg
         */
        public String getClaimMsg() {
            return claimMsg;
        }

        /**
         * Setter method for property <tt>id</tt>.
         * 
         * @param id value to be assigned to property id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * Setter method for property <tt>contributor</tt>.
         * 
         * @param contributor value to be assigned to property contributor
         */
        public void setContributor(String contributor) {
            this.contributor = contributor;
        }

        /**
         * Setter method for property <tt>claimMsg</tt>.
         * 
         * @param claimMsg value to be assigned to property claimMsg
         */
        public void setClaimMsg(String claimMsg) {
            this.claimMsg = claimMsg;
        }

    }

    /**
     * 清除 claimMsg 里面的数据
     * 
     * @param contributorList
     */
    private void cleanClaimMsg(List<Contributor> contributorList) {
        if (null == contributorList || contributorList.size() < 1) {
            return;
        }
        for (Contributor ctb : contributorList) {
            ctb.setClaimMsg("");
        }
    }

    /**
     * 前拼接 , 
     * 格式为   ,contributor1,{claimMsg1},contributor2,"",contributor3,{claimMsg3} ... ,contributorlast,""
     * 
     * @param contributorList
     * @return 转换后的字符串
     */
    private String listToStr(List<Contributor> contributorList) {
        StringBuilder strBuilder = new StringBuilder();
        if (null != contributorList && 0 < contributorList.size()) {
            for (Contributor ctb : contributorList) {
                //strBuilder.append(",").append(ctb.getContributor());
                strBuilder.append(",").append(ctb.getClaimMsg());
            }
        }
        return strBuilder.toString();
    }

    /**
     * 
     * 数据
     * @param contributorList
     * @return
     */
    private String listNameToStr(List<Contributor> contributorList) {
        StringBuilder strBuilder = new StringBuilder();
        if (null != contributorList && 0 < contributorList.size()) {
            for (Contributor ctb : contributorList) {
                strBuilder.append(",").append(ctb.getContributor());
                //strBuilder.append(",").append(ctb.getClaimMsg());
            }
        }
        return strBuilder.toString();
    }
}
