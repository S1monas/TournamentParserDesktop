package view;

import controller.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Tournament;
import model.TournamentDao;
import model.User;


public class Dashboard {
	
	private BorderPane bpRoot;
	private Scene scene;
	private Stage primaryStage;
	private TextField tfgame;
	private TextField tfGameId;
	private TextField tfResult;
	private ToggleGroup tgTBuyin;
	private RadioButton rb11;
	private RadioButton rb55;
	private RadioButton rb109;
	private RadioButton rb22;
	private RadioButton rb215;	
	private GridPane gpTournamentInfo;
	private GridPane MainWindow;
	private ChoiceBox cbCurrency;
	private CheckBox cbKnockOut;
	private CheckBox cbRebuy;
	private CheckBox cbFreezeOut;	
	private String tournamentFormat = "";
	private RadioButton selectedRadioButton;	
	private User user;
	
	public static final int ADMIN_LEVEL = 9;

public Dashboard(Stage primaryStage, User user) {	
	this.bpRoot = new BorderPane();
	scene = new Scene(this.bpRoot,900,450);
	scene.getStylesheets().add(getClass().getResource("../view/style.css").toExternalForm());
	
	this.primaryStage=primaryStage;
	this.user =user;
	
	this.primaryStage.setScene(scene);
	this.primaryStage.setTitle("Tournament Parser");
	this.primaryStage.setResizable(false);
	this.primaryStage.centerOnScreen();
	
	addElementsToScene();	
	this.primaryStage.show();
 }	
	void addElementsToScene() {
		
		//Langas vartotojo
		Label lblUsername = new Label("Logged in user: ");
		Label lblUsernameActive = new Label();
		lblUsernameActive.setText(user.getUsername());
		
		//Mygtukai
		Button btnAdd = new Button("Add");
		btnAdd.setMinWidth(80);
		Button btnDelete = new Button("Remove");
		btnDelete.setMinWidth(80);
		Button btnUpdate = new Button("Update");
		btnUpdate.setMinWidth(80);
		Button btnSearch = new Button("Search");
		btnSearch.setMinWidth(80);
		Button btnLogout = new Button ("Log out");
		btnLogout.setMinWidth(80);
		
		GridPane gpAdminDashboard = new GridPane();
		gpAdminDashboard.add(lblUsernameActive, 0, 1);
		gpAdminDashboard.add(btnLogout, 1, 1);
		gpAdminDashboard.setHgap(50);
		
		GridPane gpMygtukai = new GridPane();
		gpMygtukai.add(btnAdd,0,0);
		gpMygtukai.add(btnDelete,1,0);
		gpMygtukai.add(btnUpdate,2,0);
		gpMygtukai.add(btnSearch,3,0);
		
		gpMygtukai.setPadding(new Insets(10,10,10,10));
		gpMygtukai.setAlignment(Pos.CENTER);
		gpMygtukai.setHgap(10);
		
		//Topas
		HBox hbDashboardText = new HBox();
		hbDashboardText.setPadding(new Insets(10,10,10,10));		
		
		DropShadow dropshadow = new DropShadow();
		dropshadow.setOffsetX(5);
		dropshadow.setOffsetY(5);
			
		hbDashboardText.setPadding(new Insets(10,10,10,10));
		hbDashboardText.setAlignment(Pos.CENTER);
		Text txtDashboard = new Text("Tournament Parser");
		txtDashboard.setFont(Font.font("Courier New", FontWeight.BOLD, 30));
		txtDashboard.setEffect(dropshadow);
	
		hbDashboardText.getChildren().add(txtDashboard);
		
		
		//Tournament options		
		Label lblGame = new Label("Tournament: ");
		this.tfgame  = new TextField();
		Label lblGameId = new Label("Tournament ID: ");
		tfGameId = new TextField();
		Label lblFormat = new Label("Format: ");
		Label lblCurrency = new Label("Currency: ");
		Label lblBuyin = new Label("Buy-in: ");
		Label lblResult = new Label("Result: ");
		tfResult = new TextField();
			
		tgTBuyin = new ToggleGroup();
		rb11 = new RadioButton();
		rb22 = new RadioButton();
		rb55 = new RadioButton();
		rb109 = new RadioButton();
		rb215 = new RadioButton();		
		rb11.setText("11");
		rb22.setText("22");
		rb55.setText("55");
		rb109.setText("109");
		rb215.setText("215");	
		rb55.setSelected(true);
		rb11.setToggleGroup(tgTBuyin);
		rb22.setToggleGroup(tgTBuyin);
		rb55.setToggleGroup(tgTBuyin);
		rb109.setToggleGroup(tgTBuyin);
		rb215.setToggleGroup(tgTBuyin);
		GridPane gpTrb = new GridPane();
		gpTrb.add(rb11, 2, 1);
		gpTrb.add(rb22, 3, 1);
		gpTrb.add(rb55, 4, 1);
		gpTrb.add(rb109, 5, 1);
		gpTrb.add(rb215, 6, 1);
		
		cbCurrency = new ChoiceBox();
		cbCurrency.setItems(FXCollections.observableArrayList(
				 "USD", "Euro","GBP"));
		cbCurrency.getSelectionModel().selectFirst();
		
		cbKnockOut = new CheckBox();
		cbRebuy = new CheckBox();
		cbFreezeOut = new CheckBox();		
		cbKnockOut.setSelected(true);
		cbKnockOut.setText("Knockout");
		cbRebuy.setText("Re-Buy");
		cbFreezeOut.setText("Freezeout");	
		GridPane gpTcb = new GridPane();
		gpTcb.add(cbKnockOut, 0, 1);
		gpTcb.add(cbRebuy, 1, 1);
		gpTcb.add(cbFreezeOut, 2, 1);
		
		GridPane gpTournamentInfo = new GridPane();
		gpTournamentInfo.add(lblGameId,0,1);
		gpTournamentInfo.add(tfGameId, 1, 1);	
		gpTournamentInfo.add(lblGame,0,2);
		gpTournamentInfo.add(tfgame, 1, 2);	
		gpTournamentInfo.add(lblFormat, 0, 3);
		gpTournamentInfo.add(gpTcb, 1, 3);	
		gpTournamentInfo.add(lblCurrency,0,4);
		gpTournamentInfo.add(cbCurrency, 1, 4);	
		gpTournamentInfo.add(lblBuyin, 0, 5);
		gpTournamentInfo.add(gpTrb, 1, 5);		
		gpTournamentInfo.add(lblResult,0,6);
		gpTournamentInfo.add(tfResult, 1, 6);
		gpTournamentInfo.add(lblUsername, 0, 8);
		gpTournamentInfo.add(gpAdminDashboard, 1, 8);
		
		gpTournamentInfo.setPadding(new Insets(10,10,10,10));
		gpTournamentInfo.setVgap(10);
		gpTournamentInfo.setHgap(10);	
		gpTrb.setHgap(5);
		gpTcb.setHgap(5);
		
		
		//Table	
		TableView table = new TableView();	
		
		 table.setMaxHeight(350);
		 table.setEditable(true);
		 
		 TableColumn gameIdCol = new TableColumn("gameid");
		 gameIdCol.setMinWidth(20);
		 gameIdCol.setCellValueFactory(new PropertyValueFactory<Tournament,Integer>("gameid"));
		 
		 TableColumn gameCol = new TableColumn("game");
		 gameCol.setMinWidth(20);
		 gameCol.setCellValueFactory(new PropertyValueFactory<Tournament,String>("game"));
		// gameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		 
		 TableColumn userCol = new TableColumn("user");
		 userCol.setMinWidth(20);
		 userCol.setCellValueFactory(new PropertyValueFactory<Tournament,String>("user"));
		 //userCol.setCellFactory(TextFieldTableCell.forTableColumn());
		 
		 TableColumn formatCol = new TableColumn("format");
		 formatCol.setMinWidth(20);
		 formatCol.setCellValueFactory(new PropertyValueFactory<Tournament,String>("format"));
		// formatCol.setCellFactory(TextFieldTableCell.forTableColumn());
		 
		 TableColumn currencyCol = new TableColumn("currency");
		 currencyCol.setMinWidth(100);
		 currencyCol.setCellValueFactory(new PropertyValueFactory<Tournament,String>("currency"));	
		// currencyCol.setCellFactory(TextFieldTableCell.forTableColumn());
		 
		 TableColumn buyinCol = new TableColumn("buyin");
		 buyinCol.setMinWidth(50);
		 buyinCol.setCellValueFactory( new PropertyValueFactory<Tournament,String>("buyin"));
		// buyinCol.setCellFactory(TextFieldTableCell.forTableColumn());
		 
		 TableColumn resulttCol = new TableColumn("result");
		 resulttCol.setMinWidth(100) ;
		 resulttCol.setCellValueFactory( new PropertyValueFactory<Tournament,Double>("result"));
		 
		 ObservableList<Tournament> tournamentList = FXCollections.observableArrayList();
		 	TournamentDao tournamentDao = new TournamentDao();
		 	tournamentDao.showTournaments(tournamentList, user);
		 	table.setItems(tournamentList);
		 		 
		 
		 if (user.getUserlevel() == view.Dashboard.ADMIN_LEVEL) { //Adminas mato vartotojus
			 table.getColumns().addAll(gameCol,gameIdCol,userCol,formatCol,currencyCol,buyinCol,resulttCol); 
		 }else {//Kiti nemato
			 table.getColumns().addAll(gameCol,gameIdCol,formatCol,currencyCol,buyinCol,resulttCol); 
		 }
		 
		 
		//Iðdëstymas lange
			
		GridPane MainWindow = new GridPane();
		MainWindow.add(gpTournamentInfo, 1, 1);
		MainWindow.add(table, 2, 1);
		
		bpRoot.setTop(hbDashboardText);
		bpRoot.setCenter(MainWindow);
		bpRoot.setLeft(gpTournamentInfo);
		bpRoot.setBottom(gpMygtukai);
		
		//susiejimas su style.css
		lblGame.setId("text");
		lblGameId.setId("text");		
		lblFormat.setId("text");
		lblCurrency.setId("text");
		lblBuyin.setId("text");
		lblResult.setId("text");
		lblUsername.setId("text");
		lblUsernameActive.setId("text");		
		gpTournamentInfo.setId("text");
		bpRoot.setId("background");
		btnLogout.setId("buttons");
		btnAdd.setId("buttons");
		btnSearch.setId("buttons");
		btnUpdate.setId("buttons");
		btnDelete.setId("buttons");
		txtDashboard.setId("text");
		rb11.setId("text");
		rb22.setId("text");
		rb55.setId("text");
		rb109.setId("text");
		rb215.setId("text");
		cbKnockOut.setId("text");
		cbRebuy.setId("text");
		cbFreezeOut.setId("text");
		txtDashboard.setId("textfield");
		
		btnLogout.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				Login login = new Login(primaryStage);
			}
		});
			
		
		btnAdd.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				
				if(isTournamentValid("add")){
				//public Tournament (String game, String user, String format, String currency, String buyin, double result) {
				Tournament tournamentToAdd = new Tournament(
				tfgame.getText().toString(),
				user.getUsername(),
				tournamentFormat,
				cbCurrency.getValue().toString(),
				Double.parseDouble(tfResult.getText().toString()));
				
				
				System.out.println(tfGameId.getText().toString());
				System.out.println(tfgame.getText().toString());
				System.out.println(user.getUsername());
				System.out.println(tournamentFormat);
				System.out.println(cbCurrency.getValue().toString());
				System.out.println(selectedRadioButton.getText().toString());
				System.out.println(tfResult.getText().toString());
				System.out.println(tournamentToAdd.toString());
				
				tournamentDao.addTournament(tournamentToAdd);
				
			
				
				table.getItems().clear();			
				tournamentDao.showTournaments(tournamentList, user);
				
				}
			}
			
	});
		
		btnUpdate.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				if(isTournamentValid("update")) {	
				Tournament updateTournament = new Tournament(
					Integer.parseInt(tfGameId.getText().toString()),
					tfgame.getText().toString(),
					user.getUsername(),
					tournamentFormat,
					cbCurrency.getValue().toString(),
					selectedRadioButton.getText().toString(),
					Double.parseDouble(tfResult.getText().toString())
				);
				for(int i = 0 ;i<tournamentList.size();i++)
				{
					if(tournamentList.get(i).getGameid() == Integer.parseInt(tfGameId.getText()) )
					{
						
						tournamentDao.updateTournament(updateTournament);
						table.getItems().clear();
						tournamentDao.showTournaments(tournamentList,user);
					}
				}
	
			}
		}
	});
	
		btnSearch.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){	
				
				//String output = tournamentList.toString();
				//System.out.println(output);
				
						table.getItems().clear();
						table.setItems(tournamentDao.searchTournamentByGame(tfgame.getText().toString(), user));											
		}
	});
		
		btnDelete.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				isTournamentValid("delete");
				
				for(int i = 0 ;i<tournamentList.size();i++)
				{
					if(tournamentList.get(i).getGameid() == Integer.parseInt(tfGameId.getText()) )
					{
						tournamentDao.deleteTournament(Integer.parseInt(tfGameId.getText()));
						table.getItems().clear();
						tournamentDao.showTournaments(tournamentList,user);
					}
				}
			}
	});	
		
  }
	
		private boolean isTournamentValid(String action) {
			tournamentFormat = "";
			switch(action) {
			case "delete":
				if(!Validation.isValidID(tfGameId.getText().toString())){
					showAlert(Alert.AlertType.ERROR, bpRoot.getScene().getWindow(), "Form Error!", "Wrong tournament ID format");	
					return false;
				}else{
					return true;
				}
			case "search":
				if(!Validation.isValidTournamentForAdd(tfgame.getText().toString())){
					showAlert(Alert.AlertType.ERROR, bpRoot.getScene().getWindow(), "Form Error!", "Wrong tournament name");
					return false;
				}else{
					return true;
				}
			
			default: //update and add
				/*if(!Validation.isValidID(tfGameId.getText().toString())){
					showAlert(Alert.AlertType.ERROR, bpRoot.getScene().getWindow(), "Form Error!", "Wrong tournament ID format");
					return false;*/
				 if(!Validation.isValidTournamentForAdd(tfgame.getText().toString())){
					showAlert(Alert.AlertType.ERROR, bpRoot.getScene().getWindow(), "Form Error!", "Wrong tournament name");
					return false;
				}else if(!Validation.isValidResult(tfResult.getText().toString())){
					showAlert(Alert.AlertType.ERROR, bpRoot.getScene().getWindow(), "Form Error!", "Wrong results format");
					return false;
				}else if(!(cbKnockOut.isSelected()||cbRebuy.isSelected()||cbFreezeOut.isSelected()))
				{
					showAlert(Alert.AlertType.ERROR, bpRoot.getScene().getWindow(), "Form Error!", "Choose atleast one");		
					return false;
				}else {			
					if(cbKnockOut.isSelected())
					{
						tournamentFormat = cbKnockOut.getText();
					}
					 if (cbRebuy.isSelected())
					{
						tournamentFormat = tournamentFormat + "\n"  + cbRebuy.getText();
					}
					 if (cbFreezeOut.isSelected()) {
						tournamentFormat =  tournamentFormat + "\n" + cbFreezeOut.getText();
					}
					selectedRadioButton = (RadioButton)tgTBuyin.getSelectedToggle();		 
				}	
			}
			return true;	
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
		
		