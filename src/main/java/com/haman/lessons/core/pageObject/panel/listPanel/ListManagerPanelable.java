package com.haman.lessons.core.pageObject.panel.listPanel;

import com.codeborne.selenide.SelenideElement;

public interface ListManagerPanelable {

    SelenideElement getNodeByName(String name);

    void moveListToCategory(String listName, String categoryName);
}
