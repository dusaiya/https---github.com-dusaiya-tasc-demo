/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.tasc.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: TrainResult.java, v 0.1 2015-10-7 下午12:42:58  Exp $
 */
public class TrainResult {

    /** 实验阶段 **/
    private int    period;

    /** 迭代次数相关 **/
    private String    iteration;

    /** 训练样本数量 **/
    private int    numTrain;

    /** 分类正确**/
    private int    classifyRight;

    /** 标签数 **/
    private int    labeledNumTest;

    /**未知数**/
    private int    numUnknownInTest;

    /** 准确率 **/
    private double accuracy;

    /** 平均精度 **/
    private double averagePrecision;

    /** 召回率 **/
    private double averageRecall;

    /**
     * Getter method for property <tt>period</tt>.
     * 
     * @return property value of period
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Getter method for property <tt>numTrain</tt>.
     * 
     * @return property value of numTrain
     */
    public int getNumTrain() {
        return numTrain;
    }

    /**
     * Getter method for property <tt>classifyRight</tt>.
     * 
     * @return property value of classifyRight
     */
    public int getClassifyRight() {
        return classifyRight;
    }

    /**
     * Getter method for property <tt>labeledNumTest</tt>.
     * 
     * @return property value of labeledNumTest
     */
    public int getLabeledNumTest() {
        return labeledNumTest;
    }

    /**
     * Getter method for property <tt>numUnknownInTest</tt>.
     * 
     * @return property value of numUnknownInTest
     */
    public int getNumUnknownInTest() {
        return numUnknownInTest;
    }

    /**
     * Getter method for property <tt>accuracy</tt>.
     * 
     * @return property value of accuracy
     */
    public double getAccuracy() {
        return accuracy;
    }

    /**
     * Getter method for property <tt>averagePrecision</tt>.
     * 
     * @return property value of averagePrecision
     */
    public double getAveragePrecision() {
        return averagePrecision;
    }

    /**
     * Getter method for property <tt>averageRecall</tt>.
     * 
     * @return property value of averageRecall
     */
    public double getAverageRecall() {
        return averageRecall;
    }

    /**
     * Setter method for property <tt>period</tt>.
     * 
     * @param period value to be assigned to property period
     */
    public void setPeriod(int period) {
        this.period = period;
    }

    /**
     * Setter method for property <tt>iteration</tt>.
     * 
     * @param iteration value to be assigned to property iteration
     */
    public void setIteration(String iteration) {
        this.iteration = iteration;
    }

    /**
     * Setter method for property <tt>numTrain</tt>.
     * 
     * @param numTrain value to be assigned to property numTrain
     */
    public void setNumTrain(int numTrain) {
        this.numTrain = numTrain;
    }

    /**
     * Setter method for property <tt>classifyRight</tt>.
     * 
     * @param classifyRight value to be assigned to property classifyRight
     */
    public void setClassifyRight(int classifyRight) {
        this.classifyRight = classifyRight;
    }

    /**
     * Setter method for property <tt>labeledNumTest</tt>.
     * 
     * @param labeledNumTest value to be assigned to property labeledNumTest
     */
    public void setLabeledNumTest(int labeledNumTest) {
        this.labeledNumTest = labeledNumTest;
    }

    /**
     * Setter method for property <tt>numUnknownInTest</tt>.
     * 
     * @param numUnknownInTest value to be assigned to property numUnknownInTest
     */
    public void setNumUnknownInTest(int numUnknownInTest) {
        this.numUnknownInTest = numUnknownInTest;
    }

    /**
     * Setter method for property <tt>accuracy</tt>.
     * 
     * @param accuracy value to be assigned to property accuracy
     */
    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * Setter method for property <tt>averagePrecision</tt>.
     * 
     * @param averagePrecision value to be assigned to property averagePrecision
     */
    public void setAveragePrecision(double averagePrecision) {
        this.averagePrecision = averagePrecision;
    }

    /**
     * Setter method for property <tt>averageRecall</tt>.
     * 
     * @param averageRecall value to be assigned to property averageRecall
     */
    public void setAverageRecall(double averageRecall) {
        this.averageRecall = averageRecall;
    }

    /**
     * F-score
     * 
     * @return double
     */
    public double getFscore() {
        return (this.averagePrecision + this.averageRecall) / 2.0;
    }
    

    /**
     * Getter method for property <tt>iteration</tt>.
     * 
     * @return property value of iteration
     */
    public String getIteration() {
        return iteration;
    }

    /**
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
