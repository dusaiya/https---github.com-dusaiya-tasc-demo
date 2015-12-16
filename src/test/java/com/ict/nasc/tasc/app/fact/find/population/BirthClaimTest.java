/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.tasc.app.fact.find.population;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
public class BirthClaimTest extends TestCase {

    /**生成population训练文本核心程序**/
    public void testGenCsv() {
        BufferedWriter output = null;
        try {
            String totalString = "/Users/alibaba/Documents/schoolwork/fact_finding_dataset/birthResult.txt";

            File f = new File(totalString);
            System.out.println(totalString);
            if (f.exists()) {
                System.out.println("文件存在");
            } else {
                System.out.println("文件不存在，正在创建...");
                if (f.createNewFile()) {
                    System.out.println("文件创建成功！");
                } else {
                    System.out.println("文件创建失败！");
                }
            }
            output = new BufferedWriter(new FileWriter(f));
            extractMsg(output);
        } catch (Exception e) {
            System.out.println("文件输出");
        }

    }

    /**
     * @param output
     */
    private void extractMsg(BufferedWriter output) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            String url = "jdbc:mysql://10.60.1.92:3306/factFind?"
                         + "user=root&password=ictsoft&useUnicode=true&characterEncoding=UTF8";
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sql = "select id, contributor from death_contributor order by id asc";
            ResultSet rs = stmt.executeQuery(sql);

            List<String> contributorList = new ArrayList<String>();
            //output.write("name,claim,realdate");
            while (rs.next()) {
                //String contributor = rs.getString("contributor");
                //output.write("," + contributor);
                contributorList.add("");
            }
            //output.write("\r\n");
            System.out.println("查询matrix开始");
            sql = "select name, claim, realdate,  claimdate , matrix_id from death_final "
                  + "order by name,claim , matrix_id asc ";
            rs = stmt.executeQuery(sql);
            String name = "";
            String realdate = null;
            System.out.println("查询结束");
            while (rs.next()) {
                
                String newName = rs.getString("name");
                String birthType = rs.getString("claim");
                String prefix = newName + "," + birthType;
                if ("".equals(name)) {
                    name = prefix;
                } else if (!name.equals(prefix)) {
                    output.write(name + "," + realdate);
                    listToStr(contributorList, output);
                    cleanClaimMsg(contributorList);
                    name = prefix;
                }
                realdate = rs.getString("realdate");
                String claimdate = rs.getString("claimdate").replace(" ", "");
                int id = rs.getInt("matrix_id");
                contributorList.set(id-1, claimdate);
            }
            output.write(name + "," + realdate);
            listToStr(contributorList, output);
            System.out.println("处理完成");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException;" + e);
        } catch (SQLException e) {
            System.out.println("SQLException:" + e);
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }

    /**
     * 清除 claimMsg 里面的数据
     * 
     * @param contributorList
     */
    private void cleanClaimMsg(List<String> contributorList) {
        if (null == contributorList || contributorList.size() < 1) {
            return;
        }
        for (int i = 0; i < contributorList.size(); i++) {
            contributorList.set(i, "");
        }
    }

    /**
     * 前拼接 , 
     * 格式为   ,{claimMsg1},{claimMsg4},{claimMsg3} ... ,,{claimMsgN}""
     * @param output
     * @param contributorList
     * @throws IOException 
     */
    private void listToStr(List<String> contributorList, BufferedWriter output) throws IOException {
        if (null != contributorList && 0 < contributorList.size()) {
            for (String ctb : contributorList) {
                output.write("," + ctb);
            }
        }
        output.write("\r\n");
    }

}
