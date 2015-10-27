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
package org.apache.syncope.client.cli.commands.user;

import java.util.List;
import javax.xml.ws.WebServiceException;
import org.apache.syncope.client.cli.Input;
import org.apache.syncope.client.cli.commands.realm.RealmSyncopeOperations;
import org.apache.syncope.common.lib.SyncopeClientException;
import org.apache.syncope.common.lib.to.UserTO;

public class UserSearchByRole extends AbstractUserCommand {

    private static final String SEARCH_HELP_MESSAGE = "user --search {REALM} {ROLE-ID}";

    private final Input input;

    public UserSearchByRole(final Input input) {
        this.input = input;
    }

    public void search() {
        if (input.parameterNumber() == 2) {
            final String realm = input.firstParameter();
            final String role = input.secondParameter();
            final RealmSyncopeOperations realmSyncopeOperations = new RealmSyncopeOperations();
            try {
                List<UserTO> userTOs;
                if (!realmSyncopeOperations.exists(realm)) {
                    userResultManager.generic("Operation performed on root realm because " + realm + "does not exists");
                }
                userTOs = userSyncopeOperations.searchByRole(realm, input.secondParameter());
                if (userTOs == null || userTOs.isEmpty()) {
                    userResultManager.generic("No users has " + role + " assigned");
                } else {
                    userResultManager.toView(userTOs);
                }
            } catch (final WebServiceException | SyncopeClientException ex) {
                userResultManager.generic(ex.getMessage(), SEARCH_HELP_MESSAGE);
            }
        } else {
            userResultManager.commandOptionError(SEARCH_HELP_MESSAGE);
        }
    }

}
