package com.sample;


import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * Created by Yinka on 2016-08-21.
 */
public class Controller {
    public TextField heroName;

    public RadioButton DC_btn;
    public RadioButton marvel_btn;

    public RadioButton villainBtn;
    public RadioButton heroBtn;

    public RadioButton maleBtn;
    public RadioButton femaleBtn;
    public RadioButton antiHeroBtn;


    public void startBrowser(){
        String hero_status = null;
        if(villainBtn.isSelected()){hero_status = villainBtn.getText();}
        else if(heroBtn.isSelected()){hero_status = heroBtn.getText();}
        else {hero_status = antiHeroBtn.getText();}
        System.out.println(hero_status);

        String comic_universe = null;
        if(DC_btn.isSelected()){comic_universe = DC_btn.getText();}
        else{comic_universe = marvel_btn.getText();}
        System.out.println(comic_universe);

        String gender = null;
        if(maleBtn.isSelected()){gender = maleBtn.getText();}
        else {gender = femaleBtn.getText();}
        System.out.println(gender);


        String heroString = heroName.getText();
        System.out.println(heroString);
        Browser browser = new Browser();
        browser.startDriver(heroName.getText(), hero_status, comic_universe, gender);
    }
}
