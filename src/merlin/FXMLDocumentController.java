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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author adampisula
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ImageView button_console, button_devices, button_settings, button_close;
    
    @FXML
    private Pane console_ind, devices_ind, settings_ind;
    
    @FXML
    private AnchorPane console, devices, settings;
    
    @FXML
    private void handleButtonAction(MouseEvent event) {
        if(event.getTarget() == button_console) {
            devices.setVisible(false);
            devices_ind.setVisible(false);
            settings.setVisible(false);
            settings_ind.setVisible(false);
            console.setVisible(true);
            console_ind.setVisible(true);
            
            System.out.print("Console");
        }
        
        else if(event.getTarget() == button_devices) {
            console.setVisible(false);
            console_ind.setVisible(false);
            settings.setVisible(false);
            settings_ind.setVisible(false);
            devices.setVisible(true);
            devices_ind.setVisible(true);
            
            System.out.print("Devices");
        }
        
        else if(event.getTarget() == button_settings) {
            console.setVisible(false);
            console_ind.setVisible(false);
            devices.setVisible(false);
            devices_ind.setVisible(false);
            settings.setVisible(true);
            settings_ind.setVisible(true);
            
            System.out.print("Settings");
        }
        
        else if(event.getTarget() == button_close) {
            console.setVisible(false);
            console_ind.setVisible(false);
            devices.setVisible(false);
            devices_ind.setVisible(false);
            settings.setVisible(false);
            settings_ind.setVisible(false);
            
            System.out.print("Close");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
