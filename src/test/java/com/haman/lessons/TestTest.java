package com.haman.lessons;

import com.haman.lessons.core.pageObject.page.MainPage;
import com.haman.lessons.core.pageObject.panel.listPanel.ListManagerPanel;
import com.haman.lessons.core.pageObject.panel.taskPanel.CompletePanel;
import com.haman.lessons.core.pageObject.panel.taskPanel.FuturePanel;
import com.haman.lessons.core.pageObject.panel.taskPanel.ToDoPanel;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.page;
import static util.Util.getTomorrow;
import static util.Util.isAlphabetical;

@SuppressWarnings("ALL")
public class TestTest extends BaseTest {

    private final String taskName = "New Task #1";
    private final String listName = "New List #1";
    private final String categoryName = "New Category #1";
    private final String date = "25 March 2021";

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

    @Test(description = "Sort Test")
    public void sortToDosTest() throws InterruptedException {
        MainPage mPage = page(MainPage.class);
        Assert.assertTrue(isAlphabetical(mPage.alphabeticalSort()));
        Assert.assertFalse(mPage.normalSort().equals(mPage.randomSort()));
        Assert.assertFalse(mPage.randomSort().equals(mPage.randomSort()));
        mPage.firstThree().stream()
                .skip(3)
                .forEach((i) -> Assert.assertEquals((i.getAttribute("class")), "moveabletodo itemdimmed"));

        // This is awkward. But I need to check if Normal Sort doesn't change everything
        // Or I can just check false equality of every Sort with Normal Sort
        Assert.assertTrue(mPage.normalSort().equals(mPage.normalSort()));
    }

    @Test(description = "Tomorrow Field Functionality Test")
    public void tomorrowTaskTest() throws InterruptedException {
        MainPage mPage = page(MainPage.class);
        FuturePanel fPanel = page(FuturePanel.class);
        CompletePanel cPanel = page(CompletePanel.class);
        fPanel.showMore();
        mPage.createNewTask(taskName);
        fPanel.moveTaskToTomorrow(taskName);
        Assert.assertEquals(fPanel.tomorrowDateOf(taskName), getTomorrow());
        fPanel.completeTask(taskName);
        Assert.assertTrue(cPanel.getToDoByName(taskName).isDisplayed());
    }

    @Test(description = "Later Field Functionality Test")
    public void laterTaskTest() throws InterruptedException {
        MainPage mPage = page(MainPage.class);
        FuturePanel fPanel = page(FuturePanel.class);
        CompletePanel cPanel = page(CompletePanel.class);
        mPage.createNewTask(taskName);
        fPanel.moveTaskToLater(taskName, date);
        fPanel.showMore();
        Assert.assertEquals(fPanel.tomorrowDateOf(taskName), date);
        fPanel.completeTask(taskName);
        Assert.assertTrue(cPanel.getToDoByName(taskName).isDisplayed());
    }
}
