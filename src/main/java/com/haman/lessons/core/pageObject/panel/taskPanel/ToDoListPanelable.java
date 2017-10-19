package com.haman.lessons.core.pageObject.panel.taskPanel;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

interface ToDoListPanelable {

    SelenideElement getToDoByName(String name);

    @Step("Delete Task: {name}")
    default void deleteTodoByName(String name) {
        getToDoByName(name).hover();
        getToDoByName(name).$("img").click();
    }

}
