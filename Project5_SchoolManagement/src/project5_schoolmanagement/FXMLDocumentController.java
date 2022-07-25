/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project5_schoolmanagement;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author MarcoMan
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login_btn;

    @FXML
    private Hyperlink create_acc;

    @FXML
    private Label m;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private Label m1;

    @FXML
    private TextField su_email;

    @FXML
    private TextField su_username;

    @FXML
    private PasswordField su_password;

    @FXML
    private Button signup_btn;

    @FXML
    private Hyperlink login_acc;
    
//    TOOLS FOR DATABASE
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private double x = 0;
    private double y = 0;
    
    public void exit(){
        
        System.exit(0);
        
    }
    
    public void textfieldDesign(){
        
        if(username.isFocused()){
            
            username.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
            
            password.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            
        }else if(password.isFocused()){
            
            username.setStyle("-fx-background-color:transparent;"
                    + "-f-border-width:1px");
            
            password.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
            
        }
        
    }
    
    public void textfieldDesign1(){
        
        if(su_username.isFocused()){
            
            su_username.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
            
            su_password.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            
            su_email.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            
        }else if(su_email.isFocused()){
            
            su_username.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            
            su_password.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            
            su_email.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
            
        }else if(su_password.isFocused()){
            
            su_username.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            
            su_password.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
            
            su_email.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            
        }
        
    }
    
    public void dropShadowEffect(){
        
        DropShadow original = new DropShadow(30, Color.valueOf("#ae44a5"));
        
        original.setRadius(30);
        
        m.setEffect(original);
        
        m1.setEffect(original);
        
        m.setOnMouseEntered((MouseEvent event) ->{
            
            DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));
            
            m.setCursor(Cursor.HAND);
            m.setStyle("-fx-text-fill:#ee5fe4");
            m.setEffect(shadow);
            
        });
        
        m.setOnMouseExited((MouseEvent event) ->{
            
            DropShadow shadow = new DropShadow(20, Color.valueOf("#ae44a5"));
            
            shadow.setRadius(30);
            
            m.setStyle("-fx-text-fill:#000");
            m.setEffect(shadow);
            
        });
        
        m1.setOnMouseEntered((MouseEvent event) ->{
            
            DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));
            
            m1.setCursor(Cursor.HAND);
            m1.setStyle("-fx-text-fill:#ee5fe4");
            m1.setEffect(shadow);
            
        });
        
        m1.setOnMouseExited((MouseEvent event) ->{
            
            DropShadow shadow = new DropShadow(20, Color.valueOf("#ae44a5"));
            
            shadow.setRadius(30);
            
            m1.setStyle("-fx-text-fill:#000");
            m1.setEffect(shadow);
            
        });
        
    }
    
    public void changeForm(ActionEvent event){
        
        if(event.getSource() == create_acc){
            
            signup_form.setVisible(true);
            login_form.setVisible(false);
            
            su_username.setText("");
            su_password.setText("");
            su_email.setText("");
            
        }else if(event.getSource() == login_acc){
            
            login_form.setVisible(true);
            signup_form.setVisible(false);
            
            username.setText("");
            password.setText("");
            
        }
        
    }
    
    public boolean validationEmail(){
//        EXAMPLE: admin_123@marcoman.com [FIRST LETTER] [2ND LETTER TO NUMBER] @ [marcoman] . [com]
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        
        Matcher match = pattern.matcher(su_email.getText());
        
        if(match.find() && match.group().equals(su_email.getText())){
            
            return true;
            
        }else{
            
            Alert alert = new Alert(AlertType.ERROR);
            
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email");
            alert.showAndWait();
            
            return false;
            
        }
        
    }
    
    public void login(){
        
        connect = database.connectDb();
        
        String sql = "SELECT * FROM account WHERE username = ? and password = ?";
        
        try{
            
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            
            result = prepare.executeQuery();
            
            if(result.next()){
                
                User.username = result.getString("username");

                User.path = result.getString("image");
                
                Alert alert = new Alert(AlertType.INFORMATION);
                
                alert.setTitle("MarcoMan Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Login!");
                alert.showAndWait();
                
                login_btn.getScene().getWindow().hide();
                
                Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                
                Scene scene = new Scene(root);
                
                Stage stage = new Stage();
                
                root.setOnMousePressed((MouseEvent event) ->{
                    
                    x = event.getSceneX();
                    y = event.getSceneY();
                    
                    
                    
                });
                
                root.setOnMouseDragged((MouseEvent event) ->{
                    
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                    
                    stage.setOpacity(0.8);
                    
                });
                
                root.setOnMouseReleased((MouseEvent event) ->{
                   
                    stage.setOpacity(1);
                    
                });
                
                stage.initStyle(StageStyle.TRANSPARENT);
                
                stage.setScene(scene);
                stage.show();
                
            }else{
                
                Alert alert = new Alert(AlertType.ERROR);
                
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Username/Password!");
                alert.showAndWait();
                
            }
            
        }catch(Exception e){}
        
    }
    
    public void countRow(){
        
        connect = database.connectDb();
        
//        TO COUNT THE DATA EXCLUDING NULL ON THE DATABASE
        String sql = "SELECT count(image) FROM account WHERE image = ''";
        
//        TO COUNT THE DATA THAT NULL ON THE DATABASE
//        String sql = "SELECT count(image) FROM account WHERE image != ''";
        
//        String sql = "SELECT count(username) FROM account";

        try{
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if(result.next()){
                
                String count = result.getString("count(image)");
             
                System.out.println("COUNT: "+count);
                
            }
            
        }catch(Exception e){}
        
    }
    
    public void signup(){
        
        connect = database.connectDb();
        
        String sql = "INSERT INTO account (username,password,email,image) VALUES(?,?,?,?)";
        
        try{
            
            if(su_username.getText().isEmpty() 
                    | su_password.getText().isEmpty()
                    | su_email.getText().isEmpty()){
                
                Alert alert = new Alert(AlertType.ERROR);
                
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all field blanks!");
                alert.showAndWait();
                
            }else if(su_password.getText().length() < 8){
                
                Alert alert = new Alert(AlertType.ERROR);
                
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Password");
                alert.showAndWait();
                
            }
            else{
            
                if(validationEmail()){
                
                    String uri = "E:\\ajava\\5 NUMBER\\Project5_SchoolManagement\\src\\project5_schoolmanagement\\logo.jpg";
                    
                    User.path = uri;

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, su_username.getText()); 
                    prepare.setString(2, su_password.getText());
                    prepare.setString(3, su_email.getText());
                    prepare.setString(4, User.path);
                    
                    prepare.execute();

                    su_email.setText("");
                    su_username.setText("");
                    su_password.setText("");
                    
                    Alert alert = new Alert(AlertType.INFORMATION);

                    alert.setTitle("MarcoMan Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully create a new account!");
                    alert.showAndWait();
                    
                }
                
            }
        }catch(Exception e){e.printStackTrace();}
            
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dropShadowEffect();
        
        countRow();
        
    }    
    
}
