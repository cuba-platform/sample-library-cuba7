package com.company.library.web.author;

import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.Author;

@UiController("library$Author.browse")
@UiDescriptor("author-browse.xml")
@LookupComponent("authorsTable")
@LoadDataBeforeShow
public class AuthorBrowse extends StandardLookup<Author> {
}