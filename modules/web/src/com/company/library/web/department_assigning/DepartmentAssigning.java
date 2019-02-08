package com.company.library.web.department_assigning;

import com.company.library.entity.BookInstance;
import com.company.library.entity.LibraryDepartment;
import com.company.library.service.BookInstanceService;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Collection;

@UiController("library_DepartmentAssigning")
@UiDescriptor("department-assigning.xml")
public class DepartmentAssigning extends Screen {

    public static CloseAction SUCCESS_ACTION = new StandardCloseAction("SUCCESS_ACTION");

    @Inject
    private LookupField<LibraryDepartment> lookupField;

    public Collection<BookInstance> assignedInstances;

    @Inject
    private BookInstanceService bookInstanceService;

    private Collection<BookInstance> bookInstances;

    @Inject
    private CollectionLoader<LibraryDepartment> libraryDepartmentsDl;

    private View bookInstanceView;

    @Inject
    private Notifications notifications;

    @Inject
    private MessageBundle messageBundle;

    @Subscribe("assignAction")
    protected void onAssignAction(Action.ActionPerformedEvent event) {
        LibraryDepartment libraryDepartment = lookupField.getValue();
        if (libraryDepartment != null) {
            assignedInstances = bookInstanceService.assignLibraryDepartment(
                    bookInstances, libraryDepartment, bookInstanceView);
            close(SUCCESS_ACTION);
        } else {
            notifications.create()
                    .withCaption(messageBundle.getMessage("selectLibraryDepartmentMessage.text"))
                    .withType(Notifications.NotificationType.HUMANIZED)
                    .show();
        }
    }

    @Subscribe("clearAction")
    protected void onClearAction(Action.ActionPerformedEvent event) {
        close(WINDOW_CLOSE_ACTION);
    }

    public Collection<BookInstance> getAssignedInstances() {
        return assignedInstances;
    }

    public void setBookInstances(Collection<BookInstance> bookInstances) {
        this.bookInstances = bookInstances;
    }

    public void setBookInstanceView(View bookInstanceView) {
        this.bookInstanceView = bookInstanceView;
    }

    @Subscribe
    protected void onBeforeShow(BeforeShowEvent event) {
        libraryDepartmentsDl.load();
    }
}