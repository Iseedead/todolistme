package com.haman.lessons.core.pageObject.page.panel.complete;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.haman.lessons.core.pageObject.page.MainPage;
import com.haman.lessons.core.pageObject.page.panel.AbstractPanel;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;

public class CompletePanel extends AbstractPanel implements CompletePanelable {

    @FindBy(xpath = "//ul[@id='mydonetodos']/li")
    private ElementsCollection completes;

    public CompletePanel(MainPage mainPage) {
        super(mainPage);
    }

    public ElementsCollection getCompletes() {
        return completes;
    }

    @Override
    public SelenideElement getElementByName(String name) {
        return completes.filter(text(name)).shouldHaveSize(1).first();
    }
}
