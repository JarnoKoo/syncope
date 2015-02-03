/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.client.console.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

/**
 * Take care of all initializations needed by Syncope Console to run up and safe.
 */
@Component
@Configurable
public class SpringContextInitializer implements InitializingBean {

    @Autowired
    private PreviewPanelClassInitializer previewPanelClassInitializer;

    @Autowired
    private MIMETypesInitializer mimeTypesInitializer;

    @Override
    public void afterPropertiesSet() {
        previewPanelClassInitializer.load();
        mimeTypesInitializer.load();
    }

}
