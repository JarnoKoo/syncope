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
package org.apache.syncope.client.cli.commands.domain;

import org.apache.syncope.common.lib.SyncopeClientException;
import org.apache.syncope.common.lib.to.DomainTO;

public class DomainList extends AbstractDomainCommand {

    public DomainList() {
    }

    public void list() {
        try {
            for (final DomainTO domainTO : domainSyncopeOperations.list()) {
                domainResultManager.generic(domainTO.getKey());
            }
        } catch (final SyncopeClientException ex) {
            domainResultManager.generic("Error: " + ex.getMessage());
        }
    }
}
