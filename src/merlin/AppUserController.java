/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merlin;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author adampisula
 */
public class AppUserController implements Initializable {   
    @FXML
    private ImageView button_devices, button_settings, button_close;
    
    @FXML
    private Pane indicator, devices_ind, settings_ind, close_ind;
    
    @FXML
    private Pane button_close_cancel, button_close_confirm, button_log_out;
    
    @FXML
    private AnchorPane devices, settings, close;
    
    private String open = "";
    private boolean value = false;
    
    @FXML
    private void handleButtonAction(MouseEvent event) throws MalformedURLException, IOException {
        //MENU
        if(event.getTarget() == button_devices) {
            if(open == "Devices") {
                value = false;
                open = "none";
            }
            
            else {
                value = true;
                open = "Devices";
            }
            
            indicator.setVisible(value);
            devices.setVisible(value);
            devices_ind.setVisible(value);
            settings.setVisible(false);
            settings_ind.setVisible(false);
            close.setVisible(false);
            close_ind.setVisible(false);
        }
        
        else if(event.getTarget() == button_settings) {      
            if(open == "Settings") {
                value = false;
                open = "none";
            }
            
            else {
                value = true;
                open = "Settings";
            }
            
            indicator.setVisible(value);
            devices.setVisible(false);
            devices_ind.setVisible(false);
            settings.setVisible(value);
            settings_ind.setVisible(value);
            close.setVisible(false);
            close_ind.setVisible(false);
        }
        
        else if(event.getTarget() == button_close) {
            if(open == "Close") {
                value = false;
                open = "none";
            }
            
            else {
                value = true;
                open = "Close";
            }
            
            indicator.setVisible(value);
            devices.setVisible(false);
            devices_ind.setVisible(false);
            settings.setVisible(false);
            settings_ind.setVisible(false);
            close.setVisible(value);
            close_ind.setVisible(value);
        }
        
        //CLOSE PANE
        else if(event.getTarget() == button_close_cancel) {
            open = "none";
            
            indicator.setVisible(false);
            close.setVisible(false);
            close_ind.setVisible(false);
        }
        
        else if(event.getTarget() == button_close_confirm) {
            System.out.println("Goodbye!");
            
            Stage stage = (Stage) button_close.getScene().getWindow();
            stage.close();
        }
        
        //LOG OUT
        else if(event.getTarget() == button_log_out) {
            String request = "http://merlin.ct8.pl/log_out.php";
            URL  url = new URL( request );
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            
            System.out.println("Logging out...");
            
            ((Stage) button_log_out.getScene().getWindow()).close();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
