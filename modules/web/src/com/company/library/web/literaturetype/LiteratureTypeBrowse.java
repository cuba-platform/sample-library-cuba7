package com.company.library.web.literaturetype;

import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.LiteratureType;

import javax.inject.Inject;

@UiController("library$LiteratureType.browse")
@UiDescriptor("literature-type-browse.xml")
@LookupComponent("literatureTypesTable")
@LoadDataBeforeShow
public class LiteratureTypeBrowse extends StandardLookup<LiteratureType> {

    @Inject
    private CollectionLoader<LiteratureType> literatureTypesDl;

    @Inject
    private TextField<String> filterField;

    @Subscribe("applyBtn")
    protected void onApplyBtnClick(Button.ClickEvent event) {
        loadLiteratureTypes();
    }

    @Subscribe("filterField")
    protected void onValueChange(HasValue.ValueChangeEvent event) {
        loadLiteratureTypes();
    }

    private void loadLiteratureTypes(){
        String value = filterField.getValue();

        if (value != null) {
            literatureTypesDl.setParameter("property", "(?i)%" + value + "%");
        } else {
            literatureTypesDl.setParameter("property", value);
        }

        literatureTypesDl.load();
    }
}