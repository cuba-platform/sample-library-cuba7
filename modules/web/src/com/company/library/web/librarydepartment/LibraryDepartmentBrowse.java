package com.company.library.web.librarydepartment;

import com.haulmont.cuba.gui.screen.*;
import com.company.library.entity.LibraryDepartment;

@UiController("library$LibraryDepartment.browse")
@UiDescriptor("library-department-browse.xml")
@LookupComponent("libraryDepartmentsTable")
@LoadDataBeforeShow
public class LibraryDepartmentBrowse extends StandardLookup<LibraryDepartment> {
}