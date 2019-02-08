package com.company.library.web.publisher;

import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.Publisher;

@UiController("library$Publisher.edit")
@UiDescriptor("publisher-edit.xml")
@EditedEntityContainer("publisherDc")
@LoadDataBeforeShow
public class PublisherEdit extends StandardEditor<Publisher> {
}