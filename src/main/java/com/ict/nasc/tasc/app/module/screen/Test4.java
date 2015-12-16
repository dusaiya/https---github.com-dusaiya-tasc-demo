/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.tasc.app.module.screen;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.ict.nasc.tasc.app.util.ResultFileHandler;
import com.ict.nasc.tasc.app.util.TrainResultCompareHandler;
import com.ict.nasc.tasc.model.ClassifierResult;
import com.ict.nasc.tasc.model.TrainResult;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: Test1.java, v 0.1 2015-9-22 下午4:04:41  Exp $
 */
public class Test4 {

    /**
     * 日志信息
     */
    private Log LOG = LogFactory.getLog(Test4.class);

    /**
     * 页面程序
     * @param topic
     * @param context
     */
    public void execute(@Param("topic") String topic, Context context) {
        try {
            if (StringUtils.isBlank(topic)) {
                //默认 topic
                topic = "Ff";
                return;
            }
            context.put("topic", topic);
            //../webapps/tasc-1.0-SNAPSHOT/WEB-INF/data/accuracyresult/
            String filePath = "/bedroom/duanxueye/tascResult/result/";
            List<TrainResult> trainResultList = ResultFileHandler.getTrainResultWithPathAndTopic(
                filePath, topic);
            ClassifierResult classifierResult = TrainResultCompareHandler
                .getClassifierResult(trainResultList);

            context.put("msvmAcc", classifierResult.getMsvmResult().getAccuracy());
            context.put("coSvmAcc", classifierResult.getCoMsvmResult().getAccuracy());
            context.put("ptascAcc", classifierResult.getPtascResult().getAccuracy());

            context.put("msvmPre", classifierResult.getMsvmResult().getAveragePrecision());
            context.put("coSvmPre", classifierResult.getCoMsvmResult().getAveragePrecision());
            context.put("ptascPre", classifierResult.getPtascResult().getAveragePrecision());

            context.put("msvmFscore", classifierResult.getMsvmResult().getFscore());
            context.put("coSvmFscore", classifierResult.getCoMsvmResult().getFscore());
            context.put("ptascFscore", classifierResult.getPtascResult().getFscore());
        } catch (Exception e) {
            LOG.warn("展示结果数据异常" + e.getMessage(), e);
        }
    }
}
