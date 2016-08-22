package com.sample;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void startDriver(String heroName, String heroUniverse) {
        searchHero(heroName+" "+heroUniverse);

        System.out.println("Starting Infobox");
        HashMap<String, String> hero = new HashMap<String, String>();
        try{
            WebElement heroTable = driver.findElement(By.className("infobox"));
            getTableElements(heroTable, hero);
            hero = getHeroSummary(hero);
            for(Map.Entry<String, String> element: hero.entrySet()){
                System.out.println(element.getKey() +"\n"+element.getValue()+"\n\n");
            }

        }catch (java.lang.RuntimeException re){
            System.out.println("No Table Found");
            driver.close();
        }
    }

    private HashMap<String, String> getHeroSummary(HashMap<String, String> hero) {
        String playerSummary = "";
        int paragraphCount = 0;
        try{
            List<WebElement> paragraphs = driver.findElements(By.tagName("p"));
            for(WebElement paragraph : paragraphs){
                if(!paragraph.getText().equals("") && paragraphCount < 5){
                    playerSummary += paragraph.getText()+"\n";
                    paragraphCount++;
                }
                else{
                    System.out.println("Hit a break");
                    System.out.println(playerSummary);
                    hero.put("summary", playerSummary);
                    return hero;
                }
            }
            System.out.println("No breaks");
            System.out.println(playerSummary);
            hero.put("summary", playerSummary);
            return hero;
        }catch (java.lang.RuntimeException re){
            System.out.println("No Paragraphs found");
            driver.close();
        }
        hero.put("summary", playerSummary);
        return hero;
    }

    private void getTableElements(WebElement heroTable, HashMap<String, String> hero) {
//        System.out.println("Dividing table into rows");
        List<WebElement> row_collection = heroTable.findElements(By.tagName("tr"));
        System.out.println("Divided table into rows");
//        System.out.println("NUMBER OF ROWS IN THIS TABLE = "+row_collection.size());
        int row_num,col_num;
        row_num=1;
//        System.out.println("Find Table Headers and Elements");

        for(int i = 0; i < row_collection.size();i++){
            String key = null;
            String value = null;
//            System.out.println("Row "+ i);
//            System.out.println("----------");

            //Find Header if exists
            try{
                WebElement header = row_collection.get(i).findElement(By.tagName("th"));
                WebElement link = header.findElement(By.tagName("a"));
//                System.out.println("*"+link.getText()+"*");
                key = link.getText().replaceAll(" ", "_").toLowerCase();
            }catch (java.lang.RuntimeException re){
                try{
                    WebElement header = row_collection.get(i).findElement(By.tagName("th"));
//                    System.out.println("*"+header.getText()+"*");
                    key = header.getText().replaceAll(" ", "_").toLowerCase();
                }catch (java.lang.RuntimeException ire){
                    System.out.println("* NO HEADER *");
                }
            }

            List<WebElement> cell_collection = row_collection.get(i).findElements(By.tagName("td"));
            for(WebElement cell : cell_collection){
//                System.out.println("\n"+ cell.getText());
                value = cell.getText();
            }
//            System.out.println("----------");
            hero.put(key, value);
        }
    }

    private void searchHero(String heroName) {
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(url);

        WebElement searchElement = driver.findElement(By.tagName("input"));
        searchElement.sendKeys(heroName);
        searchElement.sendKeys(Keys.ENTER);
    }
}
