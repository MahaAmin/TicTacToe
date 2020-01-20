package popups;

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
//        try {
//            HttpResponse response = (HttpResponse) Unirest.post("https://dev-af949o4u.auth0.com/oauth/token")
//                    .header("content-type", "application/json")
//                    .body("{\"client_id\":\"uCMsW9TT9SrG4yfLXsTjvyKZPyADUtFh\",\"client_secret\":\"iO4RVR40LwEYcmKWREjGQ7_kKASL8PW8BA-Beb3PF5rNnfLpOuM6quGOjcbt1z_G\",\"audience\":\"https://dev-af949o4u.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
//                    .asString();
//            HttpResponse resp = (HttpResponse) Unirest.get("https://dev-af949o4u.auth0.com/api/v2/users/USER_ID")
//                    .header("authorization", "Bearer YOUR_ACCESS_TOKEN")
//                    .asString();
//            System.out.println(resp.getRequest());
//        } catch (UnirestException e) {
//            e.printStackTrace();
//        }
        webEngine.loadContent("https://calendar.google.com");
        anchorPane.getChildren().add(browser);
    }
}
