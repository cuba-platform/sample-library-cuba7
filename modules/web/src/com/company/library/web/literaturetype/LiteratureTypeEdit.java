package com.company.library.web.literaturetype;

import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.LiteratureType;

@UiController("library$LiteratureType.edit")
@UiDescriptor("literature-type-edit.xml")
@EditedEntityContainer("literatureTypeDc")
@LoadDataBeforeShow
public class LiteratureTypeEdit extends StandardEditor<LiteratureType> {
}