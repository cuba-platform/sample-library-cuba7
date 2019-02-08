package com.company.library.web.town;

import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.Town;

@UiController("library$Town.browse")
@UiDescriptor("town-browse.xml")
@LookupComponent("townsTable")
@LoadDataBeforeShow
public class TownBrowse extends StandardLookup<Town> {
}