package com.haman.lessons.core.pageObject.page.panel;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public interface Pageable {

    SelenideElement getElementByName(String name);

    @Step("Delete Task: {name}")
    default void deleteTodoByName(String name) {
        getElementByName(name).hover();
        getElementByName(name).$("img").click();
    }
}
