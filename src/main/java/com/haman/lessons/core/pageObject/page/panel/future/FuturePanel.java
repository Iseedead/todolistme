package com.haman.lessons.core.pageObject.page.panel.future;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.haman.lessons.core.pageObject.page.MainPage;
import com.haman.lessons.core.pageObject.page.panel.AbstractPanel;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;

public class FuturePanel extends AbstractPanel implements FuturePanelable {

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

    public FuturePanel(MainPage mainPage) {
        super(mainPage);
    }

    public void showMore() {
        show.click();
    }

    @Step("Moving Task: {taskName} to Tomorrow Tasks")
    public void moveTaskToTomorrow(String taskName) {
        mainPage.getToDoPanel().getElementByName(taskName).dragAndDropTo(tomorrowDropField);
    }

    @Step("Moving Task: {taskName} to Later Tasks for {date}")
    public void moveTaskToLater(String taskName, String date) {
        String[] dates = date.split(" ");
        mainPage.getToDoPanel().getElementByName(taskName).dragAndDropTo(laterDropField);
        while (!month.getText().equals(dates[1]) || (!year.getText().equals(dates[2]))) {
            nextBttn.click();
        }
        System.out.println("This is the End... My dear friend.. The End..");
        day.filter(text(dates[0])).shouldHaveSize(1).first().click();
    }

    @Step("Completing Task in Tomorrow Tasks: {taskName}")
    public void completeTask(String taskName) {
        getElementByName(taskName).$x("preceding-sibling::input").click();
    }

    public String tomorrowDateOf(String taskName) {
        String[] date = getElementByName(taskName).$x("../../../h4").getText().split(" ");
        date[1] = date[1].substring(0, 2);
        List<String> stringList = new LinkedList<>(Arrays.asList(date));
        stringList.remove(0);
        return String.join(" ", stringList);
    }

    @Override
    public SelenideElement getElementByName(String name) {
        return tomorrowTasks.filter(text(name)).shouldHaveSize(1).first();
    }
}
