package com.haman.lessons;

import com.haman.lessons.core.pageObject.page.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static util.Util.getTomorrow;
import static util.Util.isAlphabetical;

public class TestTest extends BaseTest {

    private final String taskName = "New Task #1";
    private final String listName = "New List #1";
    private final String categoryName = "New Category #1";
    private final String date = "25 March 2021";

    @Test(description = "Create Task")
    public void taskCreationTest() throws InterruptedException {
        MainPage page = page(MainPage.class);
        page.getToDoPanel().createTask(taskName);
        Assert.assertTrue(page.getToDoPanel().getElementByName(taskName).isDisplayed());
    }

    @Test(description = "Complete Task")
    public void taskCompletionTest() throws InterruptedException {
        MainPage page = page(MainPage.class);
        page.getToDoPanel().createTask(taskName);
        page.getToDoPanel().completeTask(taskName);
        Assert.assertTrue(page.getCompletePanel().getElementByName(taskName).isDisplayed());
    }

    @Test(description = "Create Category")
    public void categoryCreationTest() throws InterruptedException {
        MainPage page = page(MainPage.class);
        page.getListPanel().createNewCategory(categoryName);
        Assert.assertTrue(page.getListPanel().getElementByName(categoryName).isDisplayed());
    }

    @Test(description = "Create List")
    public void listCreationTest() throws InterruptedException {
        MainPage page = page(MainPage.class);
        page.getListPanel().createNewList(listName);
        Assert.assertTrue(page.getListPanel().getElementByName(listName).isDisplayed());
    }

    @Test(description = "Move List to Category")
    public void listMovingTest() throws InterruptedException {
        MainPage page = page(MainPage.class);
        page.getListPanel().createNewCategory(categoryName);
        page.getListPanel().createNewList(listName);
        page.getListPanel().moveListToCategory(listName, categoryName);
        Assert.assertEquals(page.getListPanel().containingCategory(listName), categoryName);
    }

    @Test(description = "Base functionality test")
    public void toDoListIntegrationTest() throws InterruptedException {
        MainPage page = page(MainPage.class);
        page.getListPanel().createNewCategory(categoryName);
        page.getListPanel().createNewList(listName);
        page.getListPanel().moveListToCategory(listName, categoryName);
        page.getListPanel().goToList("Today's Tasks");
        page.getToDoPanel().createTask(taskName);
        page.getListPanel().moveToList(taskName, listName);
        page.getListPanel().goToList(listName);
        Assert.assertTrue(page.getToDoPanel().getElementByName(taskName).isDisplayed());
        page.getToDoPanel().completeTask(taskName);
        Assert.assertTrue(page.getCompletePanel().getElementByName(taskName).isDisplayed());
        page.getCompletePanel().deleteTodoByName(taskName);
        Assert.assertTrue(page.getCompletePanel().getCompletes().isEmpty());
    }

    @Test(description = "Sort Test")
    public void sortToDosTest() throws InterruptedException {
        MainPage page = page(MainPage.class);
        Assert.assertTrue(isAlphabetical(page.getToDoPanel().alphabeticalSort()));
        Assert.assertFalse(page.getToDoPanel().randomSort().equals(page.getToDoPanel().normalSort()));
        Assert.assertFalse(page.getToDoPanel().randomSort().equals(page.getToDoPanel().randomSort()));
        page.getToDoPanel().firstThree().stream()
                .skip(3)
                .forEach((i) -> Assert.assertEquals((i.getAttribute("class")), "moveabletodo itemdimmed"));

        // This is awkward. But I need to check if Normal Sort doesn't change everything
        // Or I can just check false equality of every Sort with Normal Sort
        Assert.assertTrue(page.getToDoPanel().normalSort().equals(page.getToDoPanel().normalSort()));
    }

    @Test(description = "Tomorrow Field Functionality Test")
    public void tomorrowTaskTest() throws InterruptedException {
        MainPage page = page(MainPage.class);
        page.getFuturePanel().showMore();
        page.getToDoPanel().createTask(taskName);
        page.getFuturePanel().moveTaskToTomorrow(taskName);
        sleep(3000);
        Assert.assertEquals(page.getFuturePanel().tomorrowDateOf(taskName), getTomorrow());
        page.getFuturePanel().completeTask(taskName);
        Assert.assertTrue(page.getCompletePanel().getElementByName(taskName).isDisplayed());
    }

    @Test(description = "Later Field Functionality Test")
    public void laterTaskTest() throws InterruptedException {
        MainPage page = page(MainPage.class);
        page.getToDoPanel().createTask(taskName);
        page.getFuturePanel().moveTaskToLater(taskName, date);
        page.getFuturePanel().showMore();
        Assert.assertEquals(page.getFuturePanel().tomorrowDateOf(taskName), date);
        page.getFuturePanel().completeTask(taskName);
        Assert.assertTrue(page.getCompletePanel().getElementByName(taskName).isDisplayed());
    }
}
