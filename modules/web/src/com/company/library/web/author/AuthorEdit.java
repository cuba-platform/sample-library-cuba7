package com.company.library.web.author;

import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.Author;

@UiController("library$Author.edit")
@UiDescriptor("author-edit.xml")
@EditedEntityContainer("authorDc")
@LoadDataBeforeShow
public class AuthorEdit extends StandardEditor<Author> {
}