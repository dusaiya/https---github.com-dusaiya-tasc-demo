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
 * 
 * @author xueye.duanxy
 * @version $Id: ResultDataTest.java, v 0.1 2015-10-20 上午9:30:20  Exp $
 * <li>集合总数 </li>
 * double topicNum = classifierResult.getPtascResult().getLabeledNumTest() + classifierResult.getPtascResult().getNumUnknownInTest();
 * <b>结果:</b></br>
 * ptascAveragePrecision:0.5456280407413869;ptascAccuracy:0.5670650877486078;ptascFscore:0.43362748268537327;ptascRecall:0.32162692462935966
 * msvmAveragePrecision:0.38541773167721183;msvmAccuracy:0.39995381572279687;msvmFscore:0.4054473367876458;msvmRecall:0.42547694189807983
 * coMsvmAveragePrecision:0.44144245075288613;coMsvmAccuracy:0.4111967423447049;coMsvmFscore:0.37234750229238045;coMsvmRecall:0.30325255383187477
 * 
 *  <li>labeledNum</li>
 *  double topicNum = classifierResult.getPtascResult().getLabeledNumTest() ;
 *  <b>结果:</b></br>
 *  ptascAveragePrecision:0.5784095586825234;ptascAccuracy:0.612169181301014;ptascFscore:0.4533850317852355;ptascRecall:0.3283605048879473
 *  msvmAveragePrecision:0.3625067374922881;msvmAccuracy:0.3503174210569709;msvmFscore:0.3760069790766949;msvmRecall:0.38950722066110166
 *  coMsvmAveragePrecision:0.4913703111854369;coMsvmAccuracy:0.480418830901146;coMsvmFscore:0.4031981910825939;coMsvmRecall:0.31502607097975116
 */
public class ResultData1Test extends TestCase {
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
            double totalTestNum = 0;

            for (TopicEnums topic : TopicEnums.values()) {
                List<TrainResult> trainResultList = ResultFileHandler
                    .getTrainResultWithPathAndTopic(filePath, topic.getCode());
                ClassifierResult classifierResult = TrainResultCompareHandler
                    .getClassifierResult(trainResultList);
                double topicNum = classifierResult.getMsvmResult().getLabeledNumTest()
                + classifierResult.getMsvmResult().getNumUnknownInTest();
                System.out.println(topic + ":" + topicNum);
                totalTestNum = totalTestNum + topicNum;
                //ptasc
                ptascAveragePrecision = ptascAveragePrecision
                                        + classifierResult.getPtascResult().getAveragePrecision()
                                        * topicNum;
                ptascAccuracy = ptascAccuracy + classifierResult.getPtascResult().getAccuracy()
                                * topicNum;
                ptascFscore = ptascFscore + classifierResult.getPtascResult().getFscore()
                              * topicNum;
                ptascRecall = ptascRecall + classifierResult.getPtascResult().getAverageRecall()
                              * topicNum;
                //CoMsvm
                coMsvmAveragePrecision = coMsvmAveragePrecision
                                         + classifierResult.getCoMsvmResult().getAveragePrecision()
                                         * topicNum;
                coMsvmAccuracy = coMsvmAccuracy + classifierResult.getCoMsvmResult().getAccuracy()
                                 * topicNum;
                coMsvmFscore = coMsvmFscore + classifierResult.getCoMsvmResult().getFscore()
                               * topicNum;
                coMsvmRecall = coMsvmRecall + classifierResult.getCoMsvmResult().getAverageRecall()
                               * topicNum;
                //msvm
                msvmAveragePrecision = msvmAveragePrecision
                                       + classifierResult.getMsvmResult().getAveragePrecision()
                                       * topicNum;
                msvmAccuracy = msvmAccuracy + classifierResult.getMsvmResult().getAccuracy()
                               * topicNum;
                msvmFscore = msvmFscore + classifierResult.getMsvmResult().getFscore() * topicNum;
                msvmRecall = msvmRecall + classifierResult.getMsvmResult().getAverageRecall()
                             * topicNum;
                classifierResultList.add(classifierResult);
            }
            System.out.println("msvmAveragePrecision:" + msvmAveragePrecision / totalTestNum
                               + ";msvmAccuracy:" + msvmAccuracy / totalTestNum + ";msvmFscore:"
                               + msvmFscore / totalTestNum + ";msvmRecall:" + msvmRecall
                               / totalTestNum);
            System.out.println("coMsvmAveragePrecision:" + coMsvmAveragePrecision / totalTestNum
                               + ";coMsvmAccuracy:" + coMsvmAccuracy / totalTestNum
                               + ";coMsvmFscore:" + coMsvmFscore / totalTestNum + ";coMsvmRecall:"
                               + coMsvmRecall / totalTestNum);
            System.out.println("ptascAveragePrecision:" + ptascAveragePrecision / totalTestNum
                + ";ptascAccuracy:" + ptascAccuracy / totalTestNum + ";ptascFscore:"
                + ptascFscore / totalTestNum + ";ptascRecall:" + ptascRecall
                / totalTestNum);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
