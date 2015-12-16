/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.tasc.app.module.screen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ict.nasc.tasc.app.util.ResultFileHandler;
import com.ict.nasc.tasc.model.TrainResult;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: Test1.java, v 0.1 2015-9-22 下午4:04:41  Exp $
 */
public class Test1 {

    /**
     * 日志信息
     */
    private Log LOG = LogFactory.getLog(Test1.class);

    /**
     * 页面程序
     * @param topic
     * @param context
     */
    public void execute(@Param("topic") String topic, Context context) {
        if (StringUtils.isBlank(topic)) {
            //默认 topic
            topic = "NEVERSAYNEVER3D";
            return;
        }
        context.put("topic", topic);
        try {
            File testFile = new File("/bedroom/duanxueye/tascResult/result/");

            context.put("route", testFile.getAbsolutePath());
        } catch (Exception e) {
            context.put("route", "route error:" + e.getMessage());
        }
        //../webapps/tasc-1.0-SNAPSHOT/WEB-INF/data/accuracyresult/
        String filePath = "/bedroom/duanxueye/tascResult/result/";
        List<TrainResult> trainResultList = ResultFileHandler.getTrainResultWithPathAndTopic(
            filePath, topic);
        List<Object> pointList = new ArrayList<Object>();
        for (TrainResult train : trainResultList) {
            JSONObject ob = new JSONObject();
            ob.put("x", train.getNumTrain());
            ob.put("acc", train.getAccuracy());
            ob.put("precision", train.getAveragePrecision());
            ob.put("recall", train.getAverageRecall());
            ob.put("fscore", train.getFscore());
            pointList.add(ob);
        }
        JSONArray dataList = new JSONArray(pointList);
        context.put("data", dataList.toString());
        LOG.info(context.get("data"));
    }
}
