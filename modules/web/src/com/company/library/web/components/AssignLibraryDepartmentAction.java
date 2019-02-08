/*
 * Copyright (c) 2017 Haulmont
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.company.library.web.components;

import com.company.library.entity.BookInstance;
import com.company.library.web.department_assigning.DepartmentAssigning;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.ComponentsHelper;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Frame;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.components.data.TableItems;
import com.haulmont.cuba.gui.components.data.meta.ContainerDataUnit;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.DataLoader;
import com.haulmont.cuba.gui.model.HasLoader;
import com.haulmont.cuba.gui.screen.OpenMode;
import com.haulmont.cuba.gui.screen.ScreenContext;

import java.util.Set;

import static com.haulmont.bali.util.Preconditions.checkNotNullArgument;

/**
 * Action that allows to assign library department to book instances, selected in a table.
 * <p>
 * This action is used by BookInstanceBrowse and AccessionRegisterWindow.
 */
public class AssignLibraryDepartmentAction extends BaseAction {

    private Table<BookInstance> booksInstancesTable;

    public AssignLibraryDepartmentAction(Table<BookInstance> booksInstancesTable) {
        super("assignLibraryDepartment");

        Messages messages = AppBeans.get(Messages.class);

        this.caption = messages.getMessage(AssignLibraryDepartmentAction.class, "assignLibraryDepartment");
        this.booksInstancesTable = booksInstancesTable;
    }

    @Override
    public void actionPerform(Component component) {
        Frame frame = booksInstancesTable.getFrame();
        ScreenContext screenContext = ComponentsHelper.getScreenContext(booksInstancesTable);
        Set<BookInstance> bookInstances = booksInstancesTable.getSelected();

        if (!bookInstances.isEmpty()) {

            ScreenBuilders screenBuilders = AppBeans.get(ScreenBuilders.class);

            DepartmentAssigning screen = screenBuilders.screen(frame.getFrameOwner())
                    .withScreenClass(DepartmentAssigning.class)
                    .withOpenMode(OpenMode.DIALOG)
                    .withAfterCloseListener(closeEvent -> {
                        if (closeEvent.getCloseAction() == DepartmentAssigning.SUCCESS_ACTION) {

                            TableItems<BookInstance> tableItems = booksInstancesTable.getItems();

                            CollectionContainer<BookInstance> container = ((ContainerDataUnit) tableItems).getContainer();
                            DataLoader dataLoader = ((HasLoader) container).getLoader();
                            checkNotNullArgument(dataLoader);
                            dataLoader.load();
                        }
                    })
                    .build();
            screen.setBookInstances(bookInstances);
            screen.setBookInstanceView(((ContainerDataUnit) booksInstancesTable.getItems()).getContainer().getView());
            screen.show();
        } else {
            Messages messages = AppBeans.get(Messages.NAME);
            screenContext.getNotifications().create()
                    .withCaption(messages.getMainMessage("selectBookInstancesMessage.text"))
                    .withType(Notifications.NotificationType.HUMANIZED)
                    .show();
        }
    }
}