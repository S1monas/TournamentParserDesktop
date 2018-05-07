package view;

import controller.Validation;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;
import model.UserDao;

public class Login {
	private BorderPane bpRoot;
	private Stage primaryStage;
	
	public Login(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		addElementsToScene();		
		
		primaryStage.show();
	}
	
	private void addElementsToScene (){
		
		primaryStage.setTitle("Tournament Parser");
		this.bpRoot = new BorderPane();
		Scene scene = new Scene(this.bpRoot,300,250);
		//butinai reikia sitos eilutes kad veiktu css
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		Label lblUsername = new Label("Username:");
		TextField tfUsername = new TextField();
		Label lblPassword = new Label("Password:");
		PasswordField pfPassword = new PasswordField();
		
		Button btnLogin = new Button("Log in");
		btnLogin.setMinWidth(100);
		btnLogin.setAlignment(Pos.CENTER);
		
		Button btnRegister = new Button("Register");
		btnRegister.setMinWidth(100);
		btnRegister.setAlignment(Pos.CENTER);
		
		Label lblMessage = new Label ();
		btnRegister.setOnAction((ActionEvent e)->{
			Register register = new Register(this.primaryStage);
		});
		
		btnLogin.setOnAction((ActionEvent e)->{
			UserDao userDao = new UserDao();
			// String passwordencoded = encodePassword();
			User user = userDao.getUser(tfUsername.getText().toString(), 
						pfPassword.getText().toString()); 
			 if (user.getUsername() !=null) {
				Dashboard dashoard = new Dashboard(this.primaryStage, user);
			} if(!Validation.isValidCredentials(tfUsername.getText().toString())){
				lblMessage.setText("Username Incorrect");
				lblMessage.setTextFill(Color.RED);
			}else if(!Validation.isValidCredentials(pfPassword.getText().toString())){
				lblMessage.setText("Incorrect password");
				lblMessage.setTextFill(Color.RED);
			}else{
				lblMessage.setText("Incorrect log in");
				lblMessage.setTextFill(Color.RED);
			}
			
		});

		HBox hbLoginText = new HBox();
		
		//drop shadow effect
		DropShadow dropshadow = new DropShadow();
		dropshadow.setOffsetX(5);
		dropshadow.setOffsetY(5);
		
		
		hbLoginText.setPadding(new Insets(10,10,10,10));
		hbLoginText.setAlignment(Pos.CENTER);
		Text txtLogin = new Text("Log in");
		txtLogin.setFont(Font.font("Courier New", FontWeight.BOLD, 30));
		txtLogin.setEffect(dropshadow);
	
		hbLoginText.getChildren().add(txtLogin);
		
		GridPane gpLogin = new GridPane();
		gpLogin.add(lblUsername,0,0);
		gpLogin.add(tfUsername,1,0);
		gpLogin.add(lblPassword,0,1);
		gpLogin.add(pfPassword,1,1);
		gpLogin.add(lblMessage, 1, 2);
		gpLogin.add(btnLogin, 1, 3);
		gpLogin.add(btnRegister, 1, 4);
		gpLogin.setPadding(new Insets(10,10,10,10));
		gpLogin.setVgap(10);
		gpLogin.setHgap(10);
		
		//reflection for GridPane
		Reflection reflection = new Reflection();
		reflection.setFraction(1f);
		gpLogin.setEffect(reflection);
				
		bpRoot.setTop(hbLoginText);
		bpRoot.setCenter(gpLogin);
		
		//susiejimas su style.css
				
		bpRoot.setId("background");
		btnRegister.setId("buttons");
		btnLogin.setId("buttons");
		lblUsername.setId("text");
		lblPassword.setId("text");
		txtLogin.setId("textfield");
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
}