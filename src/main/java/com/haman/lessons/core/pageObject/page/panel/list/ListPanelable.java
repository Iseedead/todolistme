package com.haman.lessons.core.pageObject.page.panel.list;

import com.codeborne.selenide.SelenideElement;
import com.haman.lessons.core.pageObject.page.panel.Pageable;

interface ListPanelable extends Pageable {

    void createNewList(String listName);

    void createNewCategory(String categoryName);

    SelenideElement getElementByName(String name);

}
