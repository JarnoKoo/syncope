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
package org.apache.syncope.client.cli.commands.task;

import java.util.LinkedList;
import org.apache.syncope.client.cli.Input;
import org.apache.syncope.client.cli.util.CommandUtils;
import org.apache.syncope.common.lib.SyncopeClientException;
import org.apache.syncope.common.lib.to.AbstractTaskTO;
import org.apache.syncope.common.lib.types.TaskType;
import org.apache.syncope.common.rest.api.beans.TaskQuery;

public class TaskList extends AbstractTaskCommand {

    private static final String LIST_HELP_MESSAGE = "task --list-task {TASK-TYPE}\n"
            + "   Task type: NOTIFICATION / PROPAGATION / PUSH / SCHEDULED / SYNCHRONIZATION";

    private final Input input;

    public TaskList(final Input input) {
        this.input = input;
    }

    public void list() {
        if (input.parameterNumber() == 1) {
            try {
                final TaskType taskType = TaskType.valueOf(input.firstParameter());
                final LinkedList<AbstractTaskTO> taskTOs = new LinkedList<>();
                for (final AbstractTaskTO taskTO : taskSyncopeOperations.list(taskType, new TaskQuery()).getResult()) {
                    taskTOs.add(taskTO);
                }
                taskResultManager.fromList(taskType, taskTOs);
            } catch (final SyncopeClientException ex) {
                taskResultManager.generic(ex.getMessage());
            } catch (final IllegalArgumentException ex) {
                taskResultManager.typeNotValidError(
                        "task", input.firstParameter(), CommandUtils.fromEnumToArray(TaskType.class));
            }
        } else {
            taskResultManager.commandOptionError(LIST_HELP_MESSAGE);
        }
    }
}
