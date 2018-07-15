/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merlin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
public class AppController implements Initializable {    

    @FXML
    private ImageView button_console, button_devices, button_settings, button_close;
    
    @FXML
    private Pane indicator, console_ind, devices_ind, settings_ind, close_ind;
    
    @FXML
    private Pane button_close_cancel, button_close_confirm, button_log_out;
    
    @FXML
    private AnchorPane console, devices, settings, close;
    
    @FXML
    private Label console_area;
    
    private String open = "";
    private boolean value = false;
    private boolean console_first = true;
    
    private String command = "";
    private int cursor = 0;
    
    @FXML
    private void handleButtonAction(MouseEvent event) {
        //MENU
        if(event.getTarget() == button_console) {
            //CONSOLE KEYEVENT
            if(console_first) {
                Scene scene = (Scene) console.getScene();
        
                scene.addEventFilter(KeyEvent.ANY, keyEvent -> {
                    handleKeyPress(keyEvent);
                });
                
                console_first = false;
            }
            
            if(open == "Console") {
                value = false;
                open = "none";
            }
            
            else {
                value = true;
                open = "Console";
            }
            
            indicator.setVisible(value);
            console.setVisible(value);
            console_ind.setVisible(value);
            devices.setVisible(false);
            devices_ind.setVisible(false);
            settings.setVisible(false);
            settings_ind.setVisible(false);
            close.setVisible(false);
            close_ind.setVisible(false);
        }
        
        else if(event.getTarget() == button_devices) {
            if(open == "Devices") {
                value = false;
                open = "none";
            }
            
            else {
                value = true;
                open = "Devices";
            }
            
            indicator.setVisible(value);
            console.setVisible(false);
            console_ind.setVisible(false);
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
            console.setVisible(false);
            console_ind.setVisible(false);
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
            console.setVisible(false);
            console_ind.setVisible(false);
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
            System.out.println("Logging out...");
        }
    }
    
    @FXML
    private void handleKeyPress(KeyEvent event) {
        char c = event.getCharacter().charAt(0);
        
        if(c != 0) {
            if(c == 13 || c >= 32)
                consoleWrite(c);
            
            if(c >= 32)
                command += c;
            
            else if(c == 13) {
                executeCommand(command);
                command = "";
                
                consoleWrite("> ");
            }
        }
    }
    
    private void consoleWrite(char c) {
        String curr = console_area.getText();
        console_area.setText(curr.substring(0, curr.length() - 1) + c + "█");
    }
    
    private void consoleWrite(String s) {
        String curr = console_area.getText();
        console_area.setText(curr.substring(0, curr.length() - 1) + s + "█");
    }
    
    /*private void consoleBackspace(int cursor) {
        String curr = console_area.getText();
        console_area.setText(curr.substring(0, curr.length() - 1) + s + "█");
    }*/
    
    private void executeCommand(String command) {
        System.out.println("Execute: " + command);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}