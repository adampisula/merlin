/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merlin;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author adampisula
 */
public class LoginController implements Initializable {
    @FXML
    private Label error_label;
    
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
    private void handleKeyEvent(KeyEvent event) {
        error_label.setVisible(false);
    }
    
    @FXML
    private void handleButtonAction(MouseEvent event) throws IOException, ParseException {
        if(event.getTarget() == button_log_in) {
            error_label.setVisible(false);
            
            BufferedReader br;
            String urlParameters  = "login=" + input_login.getText() + "&hash=" + sha256(input_password.getText());
            byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            String request = "http://merlin.ct8.pl/log_in.php";
            URL    url        = new URL( request );
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false ); //True or false?
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
               wr.write( postData );
            }
            
            if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 299) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            
            JSONParser parser = new JSONParser();
            
            String result = "";
            result = br.lines().collect(Collectors.joining());
            
            Object obj = parser.parse(result);
            JSONObject user = (JSONObject) obj;
            
            if(user.get("error") == null) {
                Parent root;
                
                root = FXMLLoader.load(getClass().getResource("AppUser.fxml"));
                
                if("ADMIN".equals((String) user.get("rank")))
                    root = FXMLLoader.load(getClass().getResource("AppAdmin.fxml"));
        
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
                
                ((Stage) button_log_in.getScene().getWindow()).close();
            }
            
            else {
                error_label.setText((String) user.get("error"));
                error_label.setVisible(true);
            }
        }
        
        else if(event.getTarget() == button_log_in_close) {
            System.out.println("Goodbye!");
            
            Stage stage = (Stage) button_log_in_close.getScene().getWindow();
            stage.close();
            
            System.exit(0);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
}
