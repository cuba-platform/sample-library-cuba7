package com.company.library.web.bookpublication;

import com.company.library.entity.Book;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.BookPublication;

import javax.inject.Inject;

@UiController("library$BookPublication.edit")
@UiDescriptor("book-publication-edit.xml")
@EditedEntityContainer("bookPublicationDc")
@LoadDataBeforeShow
public class BookPublicationEdit extends StandardEditor<BookPublication> {

    @Inject
    private LookupField<Book> bookField;

    @Subscribe
    protected void onInitEntity(InitEntityEvent<BookPublication> event) {
        bookField.setEditable(true);
    }
}