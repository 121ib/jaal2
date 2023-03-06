package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {
    public static final String
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']",
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']";

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String atricle_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{FOLDER_NAME}", atricle_title);
    }

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementPresentBy(
                By.xpath(folder_name_xpath),
                "Cannot find created folder",
                5
        );
        this.waitForElementByAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find created folder",
                5
        );
    }

    public void swipeArticleToDelete(String artice_title) {
        this.waitForArticleToAppearByTitle(artice_title);
        String article_xpath = getFolderXpathByName(artice_title);
        this.waitForElementPresentBy(
                By.xpath(article_xpath),
                "Cannot find saved article"
        );
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTitle(artice_title);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresentBy(
                By.xpath(article_xpath),
                "Cannot find saved article by title  " + article_title,
                15
        );
    }

}
