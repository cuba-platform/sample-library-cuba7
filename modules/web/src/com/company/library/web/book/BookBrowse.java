package com.company.library.web.book;

import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.Book;

@UiController("library$Book.browse")
@UiDescriptor("book-browse.xml")
@LookupComponent("booksTable")
@LoadDataBeforeShow
public class BookBrowse extends StandardLookup<Book> {
}