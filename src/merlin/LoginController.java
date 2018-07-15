/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merlin;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Preloader;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author adampisula
 */
public class LoginController implements Initializable {
    @FXML
    private JFXTextField input_login;
    
    @FXML
    private JFXPasswordField input_password;
    
    @FXML
    private Pane button_log_in;
    
    @FXML
    private ImageView button_log_in_close;
    
    private double xOffset, yOffset;
            
    @FXML
    private void handleButtonAction(MouseEvent event) throws IOException {
        if(event.getTarget() == button_log_in) {
            //CONNECT TO SERVER
            System.out.println("Login: " + input_login.getText());
            System.out.println("Password: " + input_password.getText());
            
            Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
        
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Merlin");
            stage.setAlwaysOnTop(true);

            Image applicationIcon = new Image(getClass().getResourceAsStream("images/icon.png"));
            stage.getIcons().add(applicationIcon);

            Scene scene = new Scene(root);
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);

            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
        
            stage.setScene(scene);
            stage.show();
            
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        
        else if(event.getTarget() == button_log_in_close) {
            System.out.println("Goodbye!");
            
            Stage stage = (Stage) button_log_in_close.getScene().getWindow();
            stage.close();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
}
