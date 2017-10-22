package com.haman.lessons.core.pageObject.page;

import com.haman.lessons.core.pageObject.page.panel.complete.CompletePanel;
import com.haman.lessons.core.pageObject.page.panel.future.FuturePanel;
import com.haman.lessons.core.pageObject.page.panel.list.ListPanel;
import com.haman.lessons.core.pageObject.page.panel.todo.ToDoPanel;

public class MainPage {

    public ToDoPanel getToDoPanel() {
        return new ToDoPanel(this);
    }

    public CompletePanel getCompletePanel() {
        return new CompletePanel(this);
    }

    public ListPanel getListPanel() {
        return new ListPanel(this);
    }

    public FuturePanel getFuturePanel() {
        return new FuturePanel(this);
    }
}
