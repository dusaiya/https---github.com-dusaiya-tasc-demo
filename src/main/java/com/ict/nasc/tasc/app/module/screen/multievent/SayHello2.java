/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ict.nasc.tasc.app.module.screen.multievent;

/**
 * Screen方法可带有返回 
 * <p/>
 * 这个screen 返回的对象将被转换成json格式，并输出到用户浏览器 
 *
 * @author Michael Zhou
 */
public class SayHello2 {
    /** 英文 
     * @return Hello
     * */
    public Hello doEnglish() {
        return new Hello("English", "Hello");
    }

    /** 中文
     * @return Hello
     * */
    public Hello doChinese() {
        return new Hello("Chinese", "你好");
    }

    /** 法语 
     * @return Hello
     **/
    public Hello doFrench() {
        return new Hello("French", "Bonjour");
    }

    /** 西班牙语 
     * @return Hello
     **/
    public Hello doSpanish() {
        return new Hello("Spanish", "Hola");
    }

    /**
     * 
     * 
     * @author xueye.duanxy
     * @version $Id: SayHello2.java, v 0.1 2015-10-23 上午10:51:08  Exp $
     */
    public static class Hello {
        /**
         * 
         */
        private String language;
        /**
         * 
         */
        private String howToSay;

        /**
         * 
         * @param language
         * @param howToSay
         */
        public Hello(String language, String howToSay) {
            this.language = language;
            this.howToSay = howToSay;
        }

        /**
         * Getter method for property <tt>language</tt>.
         * 
         * @return property value of language
         */
        public String getLanguage() {
            return language;
        }

        /**
         * Getter method for property <tt>howToSay</tt>.
         * 
         * @return property value of howToSay
         */
        public String getHowToSay() {
            return howToSay;
        }

        /**
         * Setter method for property <tt>language</tt>.
         * 
         * @param language value to be assigned to property language
         */
        public void setLanguage(String language) {
            this.language = language;
        }

        /**
         * Setter method for property <tt>howToSay</tt>.
         * 
         * @param howToSay value to be assigned to property howToSay
         */
        public void setHowToSay(String howToSay) {
            this.howToSay = howToSay;
        }

    }
}
