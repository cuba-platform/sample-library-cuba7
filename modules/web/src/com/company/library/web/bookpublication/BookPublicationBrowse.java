package com.company.library.web.bookpublication;

import com.company.library.web.bookinstance.BookInstanceBrowse;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.BookPublication;

import javax.inject.Inject;

@UiController("library$BookPublication.browse")
@UiDescriptor("book-publication-browse.xml")
@LookupComponent("bookPublicationsTable")
@LoadDataBeforeShow
public class BookPublicationBrowse extends StandardLookup<BookPublication> {

    @Inject
    private Notifications notifications;

    @Inject
    private GroupTable<BookPublication> bookPublicationsTable;

    @Inject
    private MessageBundle messageBundle;

    @Inject
    private Screens screens;


    @Subscribe("bookPublicationsTable.browseInstances")
    protected void onBookPublicationsTableBrowseInstances(Action.ActionPerformedEvent event) {
        BookPublication bookPublication = bookPublicationsTable.getSingleSelected();
        if (bookPublication != null) {
            BookInstanceBrowse screen = screens.create(BookInstanceBrowse.class, OpenMode.THIS_TAB);
            screen.setPublication(bookPublication);
            screens.show(screen);
        } else {
            notifications.create()
                    .withCaption(messageBundle.getMessage("selectBookPublicationMessage.text"))
                    .withType(Notifications.NotificationType.HUMANIZED)
                    .show();
        }
    }
}