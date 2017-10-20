package com.haman.lessons.core.pageObject.panel.taskPanel;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.page;

public class FuturePanel implements ToDoListPanelable {

    @FindBy(xpath = "//h3[@id='tomorrowtitle']")
    private SelenideElement tomorrowDropField;
    @FindBy(xpath = "//div[@id='tomorrowitemspanel']/ul/li/span")
    private ElementsCollection tomorrowTasks;
    @FindBy(xpath = "//img[@id='tomorrowarrow']")
    private SelenideElement show;
    @FindBy(xpath = "//h3[@id='latertitle']")
    private SelenideElement laterDropField;
    @FindBy(xpath = "//span[@class='ui-datepicker-year']")
    private SelenideElement year;
    @FindBy(xpath = "//span[@class='ui-datepicker-month']")
    private SelenideElement month;
    @FindBy(xpath = "//td[@data-handler='selectDay']/a[@class='ui-state-default']")
    private ElementsCollection day;
    @FindBy(xpath = "//a[@title='Next']")
    private SelenideElement nextBttn;

    @Override
    public SelenideElement getToDoByName(String name) {
        return tomorrowTasks.filter(text(name)).shouldHaveSize(1).first();
    }

    public void showMore() {
        show.click();
    }

    @Step("Moving Task: {taskName} to Tomorrow Tasks")
    public void moveTaskToTomorrow(String taskName) {
        page(ToDoPanel.class).getToDoByName(taskName).dragAndDropTo(tomorrowDropField);
    }

    @Step("Moving Task: {taskName} to Later Tasks for {date}")
    public void moveTaskToLater(String taskName, String date) {
        String[] dates = date.split(" ");
        page(ToDoPanel.class).getToDoByName(taskName).dragAndDropTo(laterDropField);
        while (!month.getText().equals(dates[1]) || (!year.getText().equals(dates[2]))) {
            nextBttn.click();
        }
        System.out.println("this is the End... My dear friend.. The End..");
        day.filter(text(dates[0])).shouldHaveSize(1).first().click();
    }

    @Step("Completing Task in Tomorrow Tasks: {taskName}")
    public void completeTask(String taskName) {
        getToDoByName(taskName).$x("preceding-sibling::input").click();
    }

    public String tomorrowDateOf(String taskName) {
        String date = getToDoByName(taskName).$x("../../../h4").getText();
        String[] dateArr = date.split(" ");
        dateArr[1] = dateArr[1].substring(0, 2);
        List<String> stringList = new LinkedList<>(Arrays.asList(dateArr));
        stringList.remove(0);
        return String.join(" ", stringList);
    }
}
