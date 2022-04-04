package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	
    public void start(Stage primaryStage, User user) {
        //first MenuItem
        String name = "Hamburger";
        String desc = "Beef Patty, Lettuce, Tomato, Pickles, Cheese, Mayo\n";
	int time = 10;
        MenuItem MenuItem = new MenuItem(name , (float) 15, desc, time);
        MenuItems.add(MenuItem);
        //second MenuItem
        name = "Carnitas Taco";
        desc = "Pork, Onion, Cabbage, Tomato, Tortilla";
	time = 5;
        MenuItem MenuItem1 = new MenuItem(name , (float) 8 , desc, time);
        MenuItems.add(MenuItem1);
        //third MenuItem
        name ="French Fries";
        desc = "Potato, Salt";
	time = 2;
        MenuItem MenuItem2 = new MenuItem(name , (float) 5 , desc, time);
        MenuItems.add(MenuItem2);
	//fourth menuItem
	name = "Chicken Quesadilla";
	desc = "Flour tortilla, Cheese, Chicken Breast";
	time = 10;
	MenuItem MenuItem3 = new MenuItem(name , (float) 13 , desc, time);
	MenuItems.add(MenuItem3);	    
	//fifth menuItem
	name = "Chicken Fingers";
	desc = "Chicken Breast, Flour, Eggs, Buttermilk, Seasoning, Oil";
	time = 15;
	MenuItem MenuItem4 = new MenuItem(name , (float) 13 , desc, time);
	MenuItems.add(MenuItem4);
    	
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
        	menuNode.getChildren().addAll(mnName, mnPrice, mnDesc);
        	menuNode.setPadding(new Insets(20, 20, 20, 20));
        	
        	menuDisplay.getChildren().add(menuNode);
        	
        }
        menuDisplay.setPadding(new Insets(30, 30, 30, 30));
        root.setCenter(menuDisplay);
        
        VBox rightBox = new VBox();
        
        HBox addBox = new HBox();
        Button addItem = new Button ("Add Item");
        TextField addField = new TextField("Name of Item to Add");
        addBox.getChildren().addAll(addField, addItem);
        
        HBox searchBox = new HBox();
        Button searchItem = new Button("Search Item");
        TextField searchField = new TextField("Name of Item to Search");
        searchBox.getChildren().addAll(searchField, searchItem);
        
        Label searchResult = new Label();
        searchResult.setVisible(false);
        rightBox.getChildren().addAll(addBox, searchBox, searchResult);
        root.setRight(rightBox);
        
        searchItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				String searchWord = searchField.getText();
				for(int i = 0; i < MenuItems.size(); i++){
        	
					if(MenuItems.get(i).getName().equals(searchWord)) {
						
						searchResult.setText("Item is in the Menu!");
						break;
					}
					else {
						
						searchResult.setText("Item is not in the Menu!");
						
					}
        	
				}
				
				searchResult.setVisible(true);
				
			}
        	
        	
        	
        });
        
        cartPageButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				CartPage cart = new CartPage();
				Stage cartStage = new Stage();
				cart.start(cartStage, user);
				primaryStage.close();
			}
        	
        	
        	
        });
        
        homePageButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				Home home = new Home();
				Stage homeStage = new Stage();
				home.start(homeStage, user);
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
        
        addItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				String itemToAdd = addField.getText();
				for(int i = 0; i < MenuItems.size(); i++) {
					
					if(MenuItems.get(i).getName().equals(itemToAdd)) {
						
						user.addToCart(MenuItems.get(i));
						break;
						
					}
					
				}
				
			}
        	
        	
        	
        });
        
        
        primaryStage.setScene(scene);
        primaryStage.setWidth(900);
        primaryStage.setHeight(480);
        primaryStage.show();

    }
    

    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
