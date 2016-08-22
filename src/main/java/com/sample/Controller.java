package com.sample;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by Yinka on 2016-08-21.
 */
public class Controller {
    public TextField heroName;
    public TextField heroUniverse;


    public void startBrowser(){
        String heroString = heroName.getText() + " " +heroUniverse.getText();
        System.out.println(heroString);
        Browser browser = new Browser();
        browser.startDriver(heroString);
    }
}
