package com.company.library.web.accession;

import com.company.library.entity.Book;
import com.company.library.entity.BookInstance;
import com.company.library.entity.BookPublication;
import com.company.library.service.BookInstanceService;
import com.company.library.web.bookpublication.BookPublicationEdit;
import com.company.library.web.components.AssignLibraryDepartmentAction;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Collection;

@UiController("library_AccessionRegisterWindow")
@UiDescriptor("accession-register.xml")
public class AccessionRegisterWindow extends Screen {

    @Inject
    private LookupPickerField<Book> bookField;

    @Inject
    private CollectionLoader<BookPublication> bookPublicationsDl;

    @Inject
    private Notifications notifications;

    @Inject
    private MessageBundle messageBundle;

    @Inject
    private TextField<Integer> bookInstancesAmountField;

    @Inject
    private Table<BookPublication> bookPublicationsTable;

    @Inject
    private CollectionContainer<BookInstance> bookInstancesDc;

    @Inject
    private BookInstanceService bookInstanceService;

    @Inject
    private Button assignLibraryDepartmentBtn;

    @Inject
    private Table<BookInstance> bookInstancesTable;

    @Inject
    private CollectionLoader<Book> booksDl;

    @Inject
    private ScreenBuilders screenBuilders;

    @Subscribe("bookField")
    protected void onBookFieldValueChange(HasValue.ValueChangeEvent<Book> event) {
        bookPublicationsDl.setParameter("property", bookField.getValue());
        bookPublicationsDl.load();
        assignLibraryDepartmentBtn.setAction(new AssignLibraryDepartmentAction(bookInstancesTable));
    }

    @Subscribe("createBookInstances")
    protected void onCreateBookInstances(Action.ActionPerformedEvent event) {
        BookPublication bookPublication = bookPublicationsTable.getSingleSelected();
        if (bookPublication == null) {
            notifications.create()
                    .withCaption(messageBundle.getMessage("selectBookPublicationMessage.text"))
                    .withType(Notifications.NotificationType.HUMANIZED)
                    .show();
            return;
        }

        Integer bookInstancesAmount =  bookInstancesAmountField.getValue();

        if (bookInstancesAmount == null || bookInstancesAmount == 0) {
            notifications.create()
                    .withCaption(messageBundle.getMessage("setBookInstancesAmountMessage.text"))
                    .withType(Notifications.NotificationType.HUMANIZED)
                    .show();
            return;
        }

        if (bookInstancesAmount > 100) {
            notifications.create()
                    .withCaption(messageBundle.getMessage("bigBookInstancesAmountMessage.text"))
                    .withType(Notifications.NotificationType.HUMANIZED)
                    .show();
            return;
        }

        // Create book instances in the middleware service
        Collection<BookInstance> bookInstances = bookInstanceService.createBookInstances(
                bookPublication, bookInstancesAmount);

        // Add created book instances to the datasource

        bookInstancesDc.setItems(bookInstances);
    }

    @Subscribe
    protected void onBeforeShow(BeforeShowEvent event) {
        booksDl.load();
    }

    @Subscribe("bookPublicationsTable.createBookPublication")
    protected void onBookPublicationsTableCreateBookPublication(Action.ActionPerformedEvent event) {
        Book book = bookField.getValue();
        if (book == null) {
            notifications.create()
                    .withCaption(messageBundle.getMessage("selectBookMessage.text"))
                    .withType(Notifications.NotificationType.HUMANIZED)
                    .show();
            return;
        }

        screenBuilders.editor(bookPublicationsTable)
                .newEntity()
                .withInitializer(bookPublication -> {          // lambda to initialize new instance
                    bookPublication.setBook(book);
                })
                .withScreenClass(BookPublicationEdit.class)    // specific editor screen
                .withLaunchMode(OpenMode.THIS_TAB)
                .build()
                .show();
    }
}