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

package com.ict.nasc.tasc.app.module.action;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.ict.nasc.tasc.app.Visitor;

/**
 * 
 * 
 * @author xueye.duanxy
 * @version $Id: RegisterAction.java, v 0.1 2015-9-29 下午2:25:38  Exp $
 */
public class RegisterAction {
    /**
     * 
     * 
     * @param visitor
     * @param nav
     */
    public void doRegister(@FormGroup("register") Visitor visitor, Navigator nav) {
        String name = visitor.getName();

        List<Integer> lists = new ArrayList<Integer>();
        for(int i=1;i<20;i++){
            lists.add(i);
        }

        nav.redirectTo("appLink").withTarget("form/welcome").withParameter("name", name);
    }
}
