package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Home extends Application{


    public void start(Stage primaryStage, User user, ArrayList<MenuItem> MenuItems){

        BorderPane homePage = new BorderPane();
        homePage.setPadding(new Insets(10, 10, 10, 10));

        Button homePageButton = new Button("Home Page");
        Button menuPageButton = new Button("Menu");
        Button cartPageButton = new Button("Cart");
        Button profilePageButton = new Button("Profile");
        
        Owner owner = new Owner();
    	CustomerPage customer = new CustomerPage();
    	CartPage cart = new CartPage();
    	MenuPage menu = new MenuPage();

        HBox topButtons = new HBox(homePageButton, menuPageButton, cartPageButton, profilePageButton);
        topButtons.setPadding(new Insets(10, 10, 10, 10));


        Text welcomeText = new Text("Welcome " + user.getUserName() +"!");
        welcomeText.setStyle("-fx-font: 24 arial;");


        //homePage.getChildren().addAll(topButtons, welcomeText);

        homePage.setTop(topButtons);
        homePage.setCenter(welcomeText);

        Scene scene = new Scene(homePage, 900, 480);
        primaryStage.setScene(scene);
        primaryStage.show();

        homePage.setBackground(new Background((new BackgroundFill(Color.rgb(174,198,240), CornerRadii.EMPTY, Insets.EMPTY))));
        //this is used to go to the profile page
        profilePageButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage profileStage = new Stage();
				
				if (user.getUserName().equals("Admin")) {
					owner.start(profileStage, user, MenuItems); // Owner access page is only accessible if the user is "Admin"
					primaryStage.close();
				} else {

					// Clear the borderPane
					homePage.setRight(null);
					homePage.setLeft(null);
					homePage.setCenter(null);
					homePage.setTop(null);
					homePage.setBottom(null);

					// Display customer profile.
					customer.start(profileStage, user, MenuItems);
					primaryStage.close();
				}
			}
		});
        
        //this is used to go the menu page
        menuPageButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Stage menuStage = new Stage();
				
				menu.start(menuStage, user, MenuItems);
				primaryStage.close();
			}
        	
        	
        	
        });
        
        cartPageButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Stage cartStage = new Stage();
				
				cart.start(cartStage, user, MenuItems);
				primaryStage.close();
			}
        	
        	
        	
        });
        
    }

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}





}
