package com.haman.lessons;

import com.haman.lessons.core.pageObject.page.MainPage;
import com.haman.lessons.core.pageObject.panel.listPanel.ListManagerPanel;
import com.haman.lessons.core.pageObject.panel.taskPanel.CompletePanel;
import com.haman.lessons.core.pageObject.panel.taskPanel.ToDoPanel;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.page;

public class TestTest extends BaseTest {

    private final String taskName = "New Task #1";
    private final String listName = "New List #1";
    private final String categoryName = "New Category #1";

    @Test(description = "Create Task")
    public void taskCreationTest() throws InterruptedException {
        page(MainPage.class).createNewTask(taskName);
        Assert.assertTrue(page(ToDoPanel.class).getToDoByName(taskName).isDisplayed());
    }

    @Test(description = "Complete Task")
    public void taskCompletionTest() throws InterruptedException {
        page(MainPage.class).createNewTask(taskName);
        page(ToDoPanel.class).completeTask(taskName);
        Assert.assertTrue(page(CompletePanel.class).getToDoByName(taskName).isDisplayed());
    }

    @Test(description = "Create Category")
    public void categoryCreationTest() throws InterruptedException {
        page(MainPage.class).createNewCategory(categoryName);
        Assert.assertTrue(page(ListManagerPanel.class).getNodeByName(categoryName).isDisplayed());
    }

    @Test(description = "Create List")
    public void listCreationTest() throws InterruptedException {
        page(MainPage.class).createNewList(listName);
        Assert.assertTrue(page(ListManagerPanel.class).getNodeByName(listName).isDisplayed());
    }

    @Test(description = "Move List to Category")
    public void listMovingTest() throws InterruptedException {
        MainPage mPage = page(MainPage.class);
        ListManagerPanel lmPanel = page(ListManagerPanel.class);
        mPage.createNewCategory(categoryName);
        mPage.createNewList(listName);
        lmPanel.moveListToCategory(listName, categoryName);
        Assert.assertEquals(lmPanel.containingCategory(listName), categoryName);
    }

    @Test(description = "Base functionality test")
    public void toDoListIntegrationTest() throws InterruptedException {
        CompletePanel cPanel = page(CompletePanel.class);
        MainPage mPage = page(MainPage.class);
        ListManagerPanel lmPanel = page(ListManagerPanel.class);
        ToDoPanel tdPanel = page(ToDoPanel.class);
        mPage.createNewCategory(categoryName);
        mPage.createNewList(listName);
        lmPanel.moveListToCategory(listName, categoryName);
        lmPanel.goToList("Today's Tasks");
        mPage.createNewTask(taskName);
        tdPanel.moveToList(taskName, listName);
        lmPanel.goToList(listName);
        Assert.assertTrue(tdPanel.getToDoByName(taskName).isDisplayed());
        tdPanel.completeTask(taskName);
        Assert.assertTrue(cPanel.getToDoByName(taskName).isDisplayed());
        cPanel.deleteTodoByName(taskName);
        Assert.assertTrue(cPanel.getCompletes().isEmpty());
    }

}
