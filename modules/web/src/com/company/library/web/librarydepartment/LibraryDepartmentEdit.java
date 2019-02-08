package com.company.library.web.librarydepartment;

import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.LibraryDepartment;

@UiController("library$LibraryDepartment.edit")
@UiDescriptor("library-department-edit.xml")
@EditedEntityContainer("libraryDepartmentDc")
@LoadDataBeforeShow
public class LibraryDepartmentEdit extends StandardEditor<LibraryDepartment> {
}