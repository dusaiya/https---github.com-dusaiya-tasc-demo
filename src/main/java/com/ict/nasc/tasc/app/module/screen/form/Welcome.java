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

package com.ict.nasc.tasc.app.module.screen.form;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;

/**
 * 
 * 
 * @author xueye.duanxy
 * @version $Id: Welcome.java, v 0.1 2015-10-23 上午10:48:14  Exp $
 */
public class Welcome {
    /**
     * 
     * 
     * @param name
     * @param context
     */
    public void execute(@Param("name") String name, Context context) {
        context.put("name", name);
    }
}