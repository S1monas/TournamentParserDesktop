package view;

import controller.Validation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.User;
import model.UserDao;

public class Register {
	
		private BorderPane root;
		private Stage primaryStage; 
		
		
		Register(Stage primaryStage) {
			this.primaryStage = primaryStage;
			this.root = new BorderPane();
			
			Scene scene = new Scene(this.root,405,240);
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			
			this.primaryStage.setScene(scene);
			this.primaryStage.setTitle("pokemonai");
			this.primaryStage.setResizable(false);
			this.primaryStage.centerOnScreen();
			
			addElementsToScene();						
			primaryStage.show();
			
		}
		
		private void addElementsToScene (){
			Label lblUsername = new Label("Log in:");
			TextField tfUsername = new TextField();
			
			HBox hbRegisterText = new HBox();
			
			//drop shadow effect
			DropShadow dropshadow = new DropShadow();
			dropshadow.setOffsetX(5);
			dropshadow.setOffsetY(5);
			
			
			hbRegisterText.setPadding(new Insets(10,10,10,10));
			hbRegisterText.setAlignment(Pos.CENTER);
			Text txtRegister = new Text("New user registration");
			txtRegister.setFont(Font.font("Courier New", FontWeight.BOLD, 30));
			txtRegister.setEffect(dropshadow);
		
			hbRegisterText.getChildren().add(txtRegister);
			
			Label lblEmail = new Label("E-mail:");
			TextField tfEmail = new TextField();
			
			Label lblPassword = new Label("Password:");
			PasswordField pfPassword = new PasswordField();
			Label lblPassword1 = new Label("Repeat password:");
			PasswordField pfPassword1 = new PasswordField();
			Button btnRegister = new Button("Register");
			btnRegister.setMinWidth(100);
			btnRegister.setAlignment(Pos.CENTER);
			
			GridPane gpRegister = new GridPane();
			gpRegister.add(lblUsername,0,0);
			gpRegister.add(tfUsername,1,0);
			gpRegister.add(lblEmail,0,1);
			gpRegister.add(tfEmail,1,1);
			gpRegister.add(lblPassword,0,2);
			gpRegister.add(pfPassword,1,2);
			gpRegister.add(lblPassword1,0,3);
			gpRegister.add(pfPassword1,1,3);
			gpRegister.add(btnRegister, 1, 4);
			gpRegister.setPadding(new Insets(10,10,10,10));
			gpRegister.setVgap(10);
			gpRegister.setHgap(10);
					
			//susiejimas su style.css
			gpRegister.setId("gridPane");
			root.setId("background");
			btnRegister.setId("buttons");
			txtRegister.setId("textfield");		
			lblUsername.setId("text");
			lblEmail.setId("text");
			lblPassword.setId("text");
			lblPassword1.setId("text");
			lblPassword1.setId("text");
			
			this.root.setTop(hbRegisterText);
			this.root.setCenter(gpRegister);
			
			btnRegister.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				if(!Validation.isValidCredentials(tfUsername.getText().toString())){
					showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Form Error!", "Username Incorrect");
				return;
				}
			if(!Validation.isValidEmail(tfEmail.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Form Error!", "Incorrect e-mail");
				return;
				}
			if(!Validation.isValidCredentials(pfPassword.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Form Error!", "Incorrect password");
				return;
				}
			if(!pfPassword.getText().toString().equals(pfPassword1.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Form Error!", "Password mismatch");
				return;
				}
			showAlert(Alert.AlertType.INFORMATION, gpRegister.getScene().getWindow(), "Your registration is successful!", "Greetings " + tfUsername.getText().toString());
			//String username, String password, int userlevel, String email
			User useris = new User(tfUsername.getText().toString(),pfPassword.getText().toString(),1,tfEmail.getText().toString());
			UserDao userDao = new UserDao();
			userDao.addUser(useris);
			
			Login login = new Login(primaryStage);
			}
		});
		
		
		}
		private void showAlert(Alert.AlertType alerType, Window owner, String title, String message){
			Alert alert = new Alert(alerType);
			alert.setTitle(title);
			alert.setHeaderText(null);
			alert.setContentText(message);
			alert.initOwner(owner);
			alert.show();
			}

}
