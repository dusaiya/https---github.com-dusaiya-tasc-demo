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

package com.ict.nasc.tasc.app.module.screen.list;

import com.alibaba.citrus.turbine.Context;

/**
 * 
 * 
 * @author xueye.duanxy
 * @version $Id: Default.java, v 0.1 2015-10-23 上午10:48:27  Exp $
 */
public class Default {
    /**
     * 
     * 
     * @param context
     */
    public void execute(Context context) {
        context.put("list", new String[] {
                "Adobe Photoshop",
                "Adobe Acrobat",
                "Adobe Lightroom",
                "Apple Aperture",
                "Microsoft Office",
                "IntelliJ IDEA",
                "<<\"Objective-C\"指南>>"
        });
    }
}
