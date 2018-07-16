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
import java.security.MessageDigest;
import java.util.ResourceBundle;
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
            
    public static String sha256(String base) {
    try{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(base.getBytes("UTF-8"));
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    } catch(Exception ex){
       throw new RuntimeException(ex);
    }
    }
    
    @FXML
    private void handleButtonAction(MouseEvent event) throws IOException {
        if(event.getTarget() == button_log_in) {
            System.out.println("Login: " + input_login.getText());
            //System.out.println("Password: " + input_password.getText());
            System.out.println("Hash: " + sha256(input_password.getText()));
            
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
