package com.haman.lessons.core.pageObject.page.panel.complete;

import com.codeborne.selenide.SelenideElement;
import com.haman.lessons.core.pageObject.page.panel.Pageable;

interface CompletePanelable extends Pageable {

    SelenideElement getElementByName(String name);
}
