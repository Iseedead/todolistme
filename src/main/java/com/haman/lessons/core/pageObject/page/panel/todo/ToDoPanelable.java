package com.haman.lessons.core.pageObject.page.panel.todo;

import com.haman.lessons.core.pageObject.page.panel.Pageable;

interface ToDoPanelable extends Pageable {

    void createTask(String taskName);

    void completeTask(String taskName);
}
