/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merlin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author adampisula
 */
//public class FXMLDocumentController implements Initializable {
public class FXMLDocumentController implements Initializable {    

    @FXML
    private ImageView button_console, button_devices, button_settings, button_close;
    
    @FXML
    private Pane console_ind, devices_ind, settings_ind, close_ind;
    
    @FXML
    private Pane button_close_cancel, button_close_confirm;
    
    @FXML
    private AnchorPane console, devices, settings, close;
    
    private boolean minimized = true;
    
    @FXML
    private void handleButtonAction(MouseEvent event) {
        if(event.getTarget() == button_console) {
            minimized = false;
            
            close.setVisible(false);
            close_ind.setVisible(false);
            devices.setVisible(false);
            devices_ind.setVisible(false);
            settings.setVisible(false);
            settings_ind.setVisible(false);
            console.setVisible(true);
            console_ind.setVisible(true);
        }
        
        else if(event.getTarget() == button_devices) {
            minimized = false;
            
            close.setVisible(false);
            close_ind.setVisible(false);
            console.setVisible(false);
            console_ind.setVisible(false);
            settings.setVisible(false);
            settings_ind.setVisible(false);
            devices.setVisible(true);
            devices_ind.setVisible(true);
        }
        
        else if(event.getTarget() == button_settings) {
            minimized = false;
            
            close.setVisible(false);
            close_ind.setVisible(false);
            console.setVisible(false);
            console_ind.setVisible(false);
            devices.setVisible(false);
            devices_ind.setVisible(false);
            settings.setVisible(true);
            settings_ind.setVisible(true);
        }
        
        else if(event.getTarget() == button_close) {
            console.setVisible(false);
            console_ind.setVisible(false);
            devices.setVisible(false);
            devices_ind.setVisible(false);
            settings.setVisible(false);
            settings_ind.setVisible(false);
            
            if(minimized) {
                close.setVisible(true);
                close_ind.setVisible(true);
                
                minimized = false;
            }
            
            minimized = true;
        }
        
        //CLOSE PANE
        else if(event.getTarget() == button_close_cancel) {
            close.setVisible(false);
            close_ind.setVisible(false);
            
            minimized = true;
        }
        
        else if(event.getTarget() == button_close_confirm) {
            System.out.println("Goodbye!");
            
            Stage stage = (Stage) button_close.getScene().getWindow();
            stage.close();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
