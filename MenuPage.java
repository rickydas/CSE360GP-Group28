package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class MenuPage extends Application {
   // @Override
	
	private ArrayList<MenuItem> MenuItems = new ArrayList<MenuItem>();
	private ArrayList<Button> MenuButtons = new ArrayList<Button>();
	private ArrayList<MenuItem> CartItems = new ArrayList<MenuItem>();
	
    public void start(Stage primaryStage, User user) {
        //first MenuItem
        String name = "Hamburger";
        String desc = "Beef Patty, Lettuce, Tomato, Pickles, Cheese, Mayo\n";
        MenuItem MenuItem = new MenuItem(name , (float) 80, desc);
        MenuItems.add(MenuItem);
        //second MenuItem
        name = "Carnitas Taco";
        desc = "Pork, Onion, Cabbage, Tomato, Tortilla";
        MenuItem MenuItem1 = new MenuItem(name , (float) 80 , desc);
        MenuItems.add(MenuItem1);
        //third MenuItem
        name ="French Fries";
        desc = "Potato, Salt";
        MenuItem MenuItem2 = new MenuItem(name , (float)80 , desc);
        MenuItems.add(MenuItem2);
    	
        BorderPane root = new BorderPane();
        root.setBackground(new Background((new BackgroundFill(Color.rgb(174,198,240), CornerRadii.EMPTY, Insets.EMPTY))));
        Scene scene = new Scene(root);
        
        HBox menuBar = new HBox();
        Button homePageButton = new Button("Home");
        Button menuPageButton = new Button("Menu");
        Button cartPageButton = new Button("Cart");
        Button profilePageButton = new Button("Profile");
        menuBar.setPadding(new Insets(10, 10, 10, 10));
        menuBar.getChildren().addAll(homePageButton, menuPageButton, cartPageButton, profilePageButton);
        root.setTop(menuBar);
        
        VBox menuDisplay = new VBox();
        for(int i = 0; i < MenuItems.size(); i++) {
        	
        	HBox menuNode = new HBox();
        	Label mnName = new Label(MenuItems.get(i).getName());
        	mnName.setPadding(new Insets(0, 30, 0, 0));
        	String pricetoString = String.valueOf(MenuItems.get(i).getPrice());
        	Label mnPrice = new Label(pricetoString);
        	mnPrice.setPadding(new Insets(0, 30, 0, 0));
        	Label mnDesc = new Label(MenuItems.get(i).getDescription());
        	mnDesc.setPadding(new Insets(0, 30, 0, 0));
        	Button mnAdd = new Button("Add");
        	MenuButtons.add(mnAdd);
        	menuNode.getChildren().addAll(mnName, mnPrice, mnDesc, MenuButtons.get(i));
        	menuNode.setPadding(new Insets(20, 20, 20, 20));
        	
        	menuDisplay.getChildren().add(menuNode);
        	
        }


        menuDisplay.setPadding(new Insets(30, 30, 30, 30));
        root.setCenter(menuDisplay);
        
        cartPageButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				CartPage cart = new CartPage();
				Stage cartStage = new Stage();
				cart.start(cartStage, user, MenuItems);
				primaryStage.close();
			}
        	
        	
        	
        });

        homePageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Home homePage = new Home();
                Stage homeStage = new Stage();
                homePage.start(homeStage, user);
                primaryStage.close();
            }
        });

        profilePageButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if(user.getUserName().equals("Admin")){

                    Owner ownerPage = new Owner();
                    Stage ownerStage = new Stage();
                    ownerPage.start(ownerStage, user);
                    primaryStage.close();
                } else {

                    CustomerPage customerPage = new CustomerPage();
                    Stage customerStage = new Stage();
                    customerPage.start(customerStage, user);
                    primaryStage.close();

                }
            }
        });
        
        
        primaryStage.setScene(scene);
        primaryStage.setWidth(900);
        primaryStage.setHeight(480);
        primaryStage.show();

    }

    public void addItem(MenuItem item){

        MenuItems.add(item);
    }


	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}