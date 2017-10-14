package com.haman.lessons.core.pageObject.panel.taskPanel;

import com.codeborne.selenide.SelenideElement;

interface ToDoListPanelable {

    SelenideElement getToDoByName(String name);

    default void deleteTodoByName(String name) {
        getToDoByName(name).hover();
        getToDoByName(name).$("img").click();
    }

}
