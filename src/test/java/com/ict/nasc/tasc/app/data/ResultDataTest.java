/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.tasc.app.data;

import java.util.ArrayList;
import java.util.List;

import com.ict.nasc.tasc.app.util.ResultFileHandler;
import com.ict.nasc.tasc.app.util.TrainResultCompareHandler;
import com.ict.nasc.tasc.common.TopicEnums;
import com.ict.nasc.tasc.model.ClassifierResult;
import com.ict.nasc.tasc.model.TrainResult;

import junit.framework.TestCase;

/**
 * ptascAveragePrecision:0.5675436642126588;ptascAccuracy:0.6108807279491921;ptascFscore:0.45557639818572493;ptascRecall:0.3436091321587912
 * msvmAveragePrecision:0.3778558417393935;msvmAccuracy:0.3645057602338655;msvmFscore:0.38520737660574966;msvmRecall:0.3925589114721057
 * coMsvmAveragePrecision:0.4764816571094402;coMsvmAccuracy:0.4792876634083937;coMsvmFscore:0.39701373110277655;coMsvmRecall:0.31754580509611297
 * 
 * @author xueye.duanxy
 * @version $Id: ResultDataTest.java, v 0.1 2015-10-20 上午9:30:20  Exp $
 */
public class ResultDataTest extends TestCase {
    /**
     * 
     */
    public void testReData() {
        try {
            String filePath = "/Users/alibaba/Documents/schoolwork/workspace/tasc/target/tasc-1.0-SNAPSHOT/WEB-INF/data/accuracyresult/";
            List<ClassifierResult> classifierResultList = new ArrayList<ClassifierResult>();
            
            //ptasc
            double ptascAveragePrecision = 0;
            double ptascAccuracy = 0;
            double ptascFscore = 0;
            double ptascRecall = 0;
            //CoMsvm
            double coMsvmAveragePrecision = 0;
            double coMsvmAccuracy = 0;
            double coMsvmFscore = 0;
            double coMsvmRecall = 0;

            //msvm
            double msvmAveragePrecision = 0;
            double msvmAccuracy = 0;
            double msvmFscore = 0;
            double msvmRecall = 0;

            for (TopicEnums topic : TopicEnums.values()) {
                List<TrainResult> trainResultList = ResultFileHandler
                    .getTrainResultWithPathAndTopic(filePath, topic.getCode());
                ClassifierResult classifierResult = TrainResultCompareHandler
                    .getClassifierResult(trainResultList);
                //ptasc
                ptascAveragePrecision = ptascAveragePrecision
                                        + classifierResult.getPtascResult().getAveragePrecision();
                ptascAccuracy = ptascAccuracy + classifierResult.getPtascResult().getAccuracy();
                ptascFscore = ptascFscore + classifierResult.getPtascResult().getFscore();
                ptascRecall = ptascRecall + classifierResult.getPtascResult().getAverageRecall();
                //CoMsvm
                coMsvmAveragePrecision = coMsvmAveragePrecision
                                         + classifierResult.getCoMsvmResult().getAveragePrecision();
                coMsvmAccuracy = coMsvmAccuracy + classifierResult.getCoMsvmResult().getAccuracy();
                coMsvmFscore = coMsvmFscore + classifierResult.getCoMsvmResult().getFscore();
                coMsvmRecall = coMsvmRecall + classifierResult.getCoMsvmResult().getAverageRecall();
                //msvm
                msvmAveragePrecision = msvmAveragePrecision
                                       + classifierResult.getMsvmResult().getAveragePrecision();
                msvmAccuracy = msvmAccuracy + classifierResult.getMsvmResult().getAccuracy();
                msvmFscore = msvmFscore + classifierResult.getMsvmResult().getFscore();
                msvmRecall = msvmRecall + classifierResult.getMsvmResult().getAverageRecall();
                classifierResultList.add(classifierResult);
            }
            
            System.out.println("msvmAveragePrecision:" + msvmAveragePrecision / 20.0
                               + ";msvmAccuracy:" + msvmAccuracy / 20.0 + ";msvmFscore:" + msvmFscore
                               / 20.0 + ";msvmRecall:" + msvmRecall / 20.0);
            System.out.println("coMsvmAveragePrecision:" + coMsvmAveragePrecision / 20.0
                               + ";coMsvmAccuracy:" + coMsvmAccuracy / 20.0 + ";coMsvmFscore:"
                               + coMsvmFscore / 20.0 + ";coMsvmRecall:" + coMsvmRecall / 20.0);
            System.out.println("ptascAveragePrecision:" + ptascAveragePrecision / 20.0
                + ";ptascAccuracy:" + ptascAccuracy / 20.0 + ";ptascFscore:"
                + ptascFscore / 20.0 + ";ptascRecall:" + ptascRecall / 20.0);
            

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
