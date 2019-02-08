package com.company.library.web.town;

import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.Town;

@UiController("library$Town.edit")
@UiDescriptor("town-edit.xml")
@EditedEntityContainer("townDc")
@LoadDataBeforeShow
public class TownEdit extends StandardEditor<Town> {
}