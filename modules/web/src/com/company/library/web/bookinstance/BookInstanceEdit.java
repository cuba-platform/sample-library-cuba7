package com.company.library.web.bookinstance;

import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.BookInstance;

@UiController("library$BookInstance.edit")
@UiDescriptor("book-instance-edit.xml")
@EditedEntityContainer("bookInstanceDc")
@LoadDataBeforeShow
public class BookInstanceEdit extends StandardEditor<BookInstance> {
}