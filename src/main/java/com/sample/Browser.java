package com.sample;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yinka on 2016-08-21.
 */
public class Browser {
    WebDriver driver;
    private String url = "https://en.wikipedia.org/wiki/Main_Page";

    public Browser(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void startDriver(String heroName) {

        searchHero(heroName);

        System.out.println("Starting Infobox");

        try{
            WebElement heroTable = driver.findElement(By.className("infobox"));
            System.out.println("Start Dividing Rows");
            List<WebElement> tr_collection = heroTable.findElements(By.xpath("id('infobox')/tbody/tr"));
            for(int i = 0; i < tr_collection.size(); i++){
                System.out.println("Test");
            }
        }catch (java.lang.RuntimeException re){
            System.out.println("No Table");
        }
    }

    private void searchHero(String heroName) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(url);

        WebElement searchElement = driver.findElement(By.tagName("input"));
        searchElement.sendKeys(heroName);
        searchElement.sendKeys(Keys.ENTER);
    }
}
