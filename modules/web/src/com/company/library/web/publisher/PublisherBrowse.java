package com.company.library.web.publisher;

import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.Publisher;

@UiController("library$Publisher.browse")
@UiDescriptor("publisher-browse.xml")
@LookupComponent("publishersTable")
@LoadDataBeforeShow
public class PublisherBrowse extends StandardLookup<Publisher> {
}