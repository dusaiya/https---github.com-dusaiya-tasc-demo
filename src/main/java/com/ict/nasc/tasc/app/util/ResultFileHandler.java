/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.tasc.app.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ict.nasc.tasc.model.TrainResult;

/**
 * 结果文件处理工具类
 * @author xueye.duanxy
 * @version $Id: ResultFileHandler.java, v 0.1 2015-10-8 上午1:58:25  Exp $
 */
public class ResultFileHandler {
    /**
     * 日志信息
     */
    private static Log LOG = LogFactory.getLog(ResultFileHandler.class);

    /**
     * 根据topic 和 path 获取训练结果集
     * 
     * @param path
     * @param topic
     * @return List<TrainResult>
     */
    public static List<TrainResult> getTrainResultWithPathAndTopic(String path, String topic) {

        //获取最近时间的结果文件
        File handleFile = getLatestFile(path, topic);
        //从文件中获取结果集
        List<TrainResult> trainResultList = getResultDataFromFile(handleFile);
        LOG.info("数据获取完成" + topic);
        return trainResultList;
    }

    /**
     * 
     * @param handleFile
     * @return List<TrainResult>
     */
    public static List<TrainResult> getResultDataFromFile(File handleFile) {
        List<TrainResult> trainResultList = new ArrayList<TrainResult>();
        BufferedReader reader = null;
        int lineCount = 0;
        String tempString = null;
        try {
            reader = new BufferedReader(new FileReader(handleFile));
            while ((tempString = reader.readLine()) != null) {
                try {
                    if (StringUtils.isNotBlank(tempString) && tempString.startsWith("period")) {
                        String[] strlist = tempString.split("\t");
                        TrainResult trainResult = new TrainResult();
                        trainResult.setPeriod(Integer.parseInt(strlist[0].substring(strlist[0]
                            .indexOf(" ") + 1)));
                        //结果集含有final
                        trainResult.setIteration(strlist[1]);
                        trainResult.setNumTrain(Integer.parseInt(strlist[2].substring(strlist[2]
                            .indexOf(" ", 25) + 1)));
                        trainResult.setClassifyRight(Integer.parseInt(strlist[3]
                            .substring(strlist[3].indexOf(" ", 14) + 1)));
                        trainResult.setLabeledNumTest(Integer.parseInt(strlist[4]
                            .substring(strlist[4].indexOf(" ", 15) + 1)));
                        trainResult.setNumUnknownInTest(Integer.parseInt(strlist[5]
                            .substring(strlist[5].indexOf(" ") + 1)));
                        trainResult.setAccuracy(Double.parseDouble(strlist[6].substring(strlist[6]
                            .indexOf(" ") + 1)));
                        trainResult.setAveragePrecision(Double.parseDouble(strlist[7]
                            .substring(strlist[7].indexOf(" ", 17) + 1)));
                        trainResult.setAverageRecall(Double.parseDouble(strlist[8]
                            .substring(strlist[8].indexOf(" ", 14) + 1)));
                        trainResultList.add(trainResult);
                        lineCount = lineCount + 1;
                    }
                } catch (Exception e) {
                    LOG.warn("huandling file error : " + e + ";lineCount:" + lineCount
                             + ";tempString:" + tempString, e);
                    continue;
                }
            }
        } catch (Exception e) {
            LOG.warn("文件读取异常 : " + e + ";lineCount:" + lineCount + ";tempString:" + tempString, e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    LOG.warn("关闭文件流异常:" + e.getMessage(), e);
                }
            }

        }
        return trainResultList;
    }

    /**
     * 根据文件路径与topic获取最新生成的结果文件
     * @param path
     * @param topic
     * @return File
     */
    public static File getLatestFile(String path, String topic) {
        String finalFile = null;
        Map<String, File> fileContent = new HashMap<String, File>();
        try {
            File file = new File(path + topic);
            LOG.info(file.getAbsolutePath());

            long maxFileName = 0;
            for (File sigleFile : file.listFiles()) {

                String sigleFileName = sigleFile.getName();
                LOG.info(sigleFileName);
                if (StringUtils.isNumeric(sigleFileName)) {
                    fileContent.put(sigleFileName, sigleFile);
                    LOG.info(sigleFileName + " is one of the suitble files.");
                    if (maxFileName < Long.parseLong(sigleFileName)) {
                        maxFileName = Long.parseLong(sigleFileName);
                        finalFile = sigleFileName;
                    }
                    LOG.info("intData:" + Long.parseLong(sigleFileName));
                } else {
                    LOG.info(sigleFileName + " is a weight file");
                }
            }
            LOG.info("FinalFile name is " + finalFile);
        } catch (Exception e) {
            LOG.warn("fetching File error : " + e.getMessage(), e);
        }
        File handleFile = fileContent.get(finalFile);
        return handleFile;
    }

}
