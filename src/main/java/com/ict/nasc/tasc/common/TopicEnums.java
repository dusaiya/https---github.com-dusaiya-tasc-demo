/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.tasc.common;

/**
 * Topic 词语
 * @author xueye.duanxy
 * @version $Id: TopicEnums.java, v 0.1 2015-9-22 下午4:21:43  Exp $
 */
public enum TopicEnums {
    /***/
    Ff("Ff"),
    /***/
    nowplaying("nowplaying"),
    /***/
    MentionKe("MentionKe"),
    /***/
    Egypt("Egypt"),
    /***/
    Superbowl("Superbowl"),
    /***/
    fb("fb"),
    /***/
    TeamFollowBack("TeamFollowBack"),
    /***/
    icantdateyou("icantdateyou"),
    /***/
    shoutout("shoutout"),
    /***/
    nw("nw"),
    /***/
    improudtosay("improudtosay"),
    /***/
    cumanNANYA("cumanNANYA"),
    /***/
    jfb("jfb"),
    /***/
    DamnItsTrue("DamnItsTrue"),
    /***/
    NEVERSAYNEVER3D("NEVERSAYNEVER3D"),
    /***/
    februarywish("februarywish"),
    /***/
    Twitition("Twitition"),
    /***/
    BieberD3D("BieberD3D"),
    /***/
    pickone("pickone"),
    /***/
    purpleglasses("purpleglasses");

    /**
     * code
     */
    private String code;

    /**
     * 构造函数
     * @param code
     */
    private TopicEnums(String code) {
        this.code = code;
    }

    /**
     * 获取枚举类型
     * 
     * @param code
     * @return TopicEnums
     */
    public static TopicEnums getByCode(String code) {
        for (TopicEnums topic : values()) {
            if (topic.equals(code)) {
                return topic;
            }
        }
        return null;
    }

    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     * 
     * @param code value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

}
