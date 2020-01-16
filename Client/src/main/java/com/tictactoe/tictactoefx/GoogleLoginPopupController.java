package com.tictactoe.tictactoefx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class GoogleLoginPopupController implements Initializable 
{
    @FXML private AnchorPane anchorPane;
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        webEngine.loadContent("https://calendar.google.com");
        anchorPane.getChildren().add(browser);
    }
}
