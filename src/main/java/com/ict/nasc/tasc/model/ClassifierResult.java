/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.tasc.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: ClassfierResult.java, v 0.1 2015-10-8 下午5:37:37  Exp $
 */
public class ClassifierResult {

    /**msvmResult*/
    private TrainResult msvmResult;

    /** coMsvmResult **/
    private TrainResult coMsvmResult;

    /** ptascResult **/
    private TrainResult ptascResult;

    /**
     * Getter method for property <tt>msvmResult</tt>.
     * 
     * @return property value of msvmResult
     */
    public TrainResult getMsvmResult() {
        return msvmResult;
    }

    /**
     * Getter method for property <tt>coMsvmResult</tt>.
     * 
     * @return property value of coMsvmResult
     */
    public TrainResult getCoMsvmResult() {
        return coMsvmResult;
    }

    /**
     * Getter method for property <tt>ptascResult</tt>.
     * 
     * @return property value of ptascResult
     */
    public TrainResult getPtascResult() {
        return ptascResult;
    }

    /**
     * Setter method for property <tt>msvmResult</tt>.
     * 
     * @param msvmResult value to be assigned to property msvmResult
     */
    public void setMsvmResult(TrainResult msvmResult) {
        this.msvmResult = msvmResult;
    }

    /**
     * Setter method for property <tt>coMsvmResult</tt>.
     * 
     * @param coMsvmResult value to be assigned to property coMsvmResult
     */
    public void setCoMsvmResult(TrainResult coMsvmResult) {
        this.coMsvmResult = coMsvmResult;
    }

    /**
     * Setter method for property <tt>ptascResult</tt>.
     * 
     * @param ptascResult value to be assigned to property ptascResult
     */
    public void setPtascResult(TrainResult ptascResult) {
        this.ptascResult = ptascResult;
    }

    /**
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
