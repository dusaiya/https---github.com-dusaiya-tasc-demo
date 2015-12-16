/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.tasc.app.util;

import java.util.List;

import com.ict.nasc.tasc.model.ClassifierResult;
import com.ict.nasc.tasc.model.TrainResult;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: TrainResultCompareHandler.java, v 0.1 2015-10-8 下午5:36:15  Exp $
 */
public class TrainResultCompareHandler {

    /**
     * 根据trainResultList 获取情感分类结果
     * 
     * @param trainResultList
     * @return ClassifierResult
     */
    public static ClassifierResult getClassifierResult(List<TrainResult> trainResultList) {
        ClassifierResult classifierResult = new ClassifierResult();
        classifierResult.setMsvmResult(trainResultList.get(0));
        for (TrainResult trainResult : trainResultList) {
            if (trainResult.getPeriod() == 1 && trainResult.getIteration().startsWith("final")) {
                classifierResult.setCoMsvmResult(trainResult);
            }
        }
        classifierResult.setPtascResult(trainResultList.get(trainResultList.size()-1));
        return classifierResult;

    }

}
