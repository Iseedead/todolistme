package com.haman.lessons;

import com.haman.lessons.core.pageObject.page.MainPage;
import com.haman.lessons.core.pageObject.panel.listPanel.ListManagerPanel;
import com.haman.lessons.core.pageObject.panel.taskPanel.CompletePanel;
import com.haman.lessons.core.pageObject.panel.taskPanel.ToDoPanel;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;


public class TestTest extends BaseTest {

    private final String taskName = "New Task #1";
    private final String listName = "New List #1";
    private final String categoryName = "New Category #1";

    @Test
    public void taskCreationTest() throws InterruptedException {
        page(MainPage.class).createNewTask(taskName);
        page(ToDoPanel.class).getToDoByName(taskName).should(be(visible));
    }

    @Test
    public void taskCompletionTest() throws InterruptedException {
        page(MainPage.class).createNewTask(taskName);
        page(ToDoPanel.class).completeTask(taskName);
        page(CompletePanel.class).getToDoByName(taskName).should(be(visible));
    }

    @Test
    public void CategoryCreationTest() throws InterruptedException {
        page(MainPage.class).createNewCategory(categoryName);
        page(ListManagerPanel.class).getNodeByName(categoryName).should(be(visible));
    }

    @Test
    public void ListCreationTest() throws InterruptedException {
        page(MainPage.class).createNewList(listName);
        page(ListManagerPanel.class).getNodeByName(listName).should(be(visible));
    }

    @Test
    public void listMovingTest() throws InterruptedException {
        page(MainPage.class).createNewCategory(categoryName);
        page(MainPage.class).createNewList(listName);
        page(ListManagerPanel.class).moveListToCategory(listName, categoryName);
        page(ListManagerPanel.class).containingCategory(listName).should(have(textCaseSensitive(categoryName)));
    }

    /*
    * Don't really know if I should divide this Test ↓ into separate once.
    * Feel like I should, but at the same time it will be A LOT OF repeated code.
    * for example, I already have Test for task completion. Maybe I just don't have to CHECK it.
    * but again, BUT, this is Test after moving task, so this isn't the same shit as before
    * all in all, i'm just another brick in the wall
     */

    @Test
    public void toDoListIntegrationTest() throws InterruptedException {
        page(MainPage.class).createNewCategory(categoryName);
        page(MainPage.class).createNewList(listName);
        page(ListManagerPanel.class).moveListToCategory(listName, categoryName);

        page(ListManagerPanel.class).goToList("Today's Tasks");
        page(MainPage.class).createNewTask(taskName);
        page(ToDoPanel.class).moveToList(taskName, listName);
        page(ListManagerPanel.class).goToList(listName);

        page(ToDoPanel.class).getToDoByName(taskName).should(be(visible));

        page(ToDoPanel.class).completeTask(taskName);
        page(CompletePanel.class).getToDoByName(taskName).should(be(visible));

        page(CompletePanel.class).deleteTodoByName(taskName);
        page(CompletePanel.class).getCompletes().shouldHaveSize(0);
    }

}
