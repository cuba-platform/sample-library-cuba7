package com.company.library.web.bookinstance;

import com.company.library.entity.BookPublication;
import com.company.library.web.components.AssignLibraryDepartmentAction;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.BookInstance;

import javax.inject.Inject;

@UiController("library$BookInstance.browse")
@UiDescriptor("book-instance-browse.xml")
@LookupComponent("bookInstancesTable")
@LoadDataBeforeShow
public class BookInstanceBrowse extends StandardLookup<BookInstance> {

    @Inject
    private GroupTable<BookInstance> bookInstancesTable;

    @Inject
    private Label<String> bookTitleLabel;

    @Inject
    private MessageBundle messageBundle;

    private BookPublication publication;

    @Inject
    private CollectionLoader<BookInstance> bookInstancesDl;

    @Inject
    private Button assignLibraryDepartmentBtn;

    @Subscribe
    protected void onBeforeShow(BeforeShowEvent event) {
        bookTitleLabel.setValue(String.format(messageBundle.getMessage("book"),
                publication.getBook().getName(), publication.getPublisher().getName(), publication.getYear()));
        bookInstancesDl.setParameter("property", publication);
        assignLibraryDepartmentBtn.setAction(new AssignLibraryDepartmentAction(bookInstancesTable));
    }

    public void setPublication(BookPublication bookPublication) {
        publication = bookPublication;
    }
}