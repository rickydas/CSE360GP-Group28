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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuPage extends Application {
	// @Override

	// private ArrayList<MenuItem> MenuItems = new ArrayList<MenuItem>();
	private ArrayList<Button> MenuButtons = new ArrayList<Button>();
	private ArrayList<MenuItem> CartItems = new ArrayList<MenuItem>();
	File menuFile = new File("menuFile.txt");

	public void start(Stage primaryStage, User user) throws FileNotFoundException {

		Scanner sc = new Scanner(menuFile);
		String itemName;
		String description;
		float price;
		int time;
		String urlPhoto;
		String newLine;

		String[] menuLine;
		MenuItem newItem = null;
		user.setMenu();
		while ((sc.hasNextLine())) {
			newLine = sc.nextLine();
			menuLine = newLine.split("/");
			itemName = menuLine[0];
			description = menuLine[1];
			time = Integer.parseInt(menuLine[2]);
			urlPhoto = menuLine[3];
			price = Float.parseFloat(menuLine[4]);
			newItem = new MenuItem(itemName, price, description, time, urlPhoto);
			user.getMenu().add(newItem);
		}

		BorderPane root = new BorderPane();
		root.setBackground(
				new Background((new BackgroundFill(Color.rgb(174, 198, 240), CornerRadii.EMPTY, Insets.EMPTY))));
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
		//user.getMenu().get(0).setImageURL("burger.png");
		//user.getMenu().get(1).setImageURL("carnitastaco.jpg");
		//user.getMenu().get(2).setImageURL("frenchfries.jpg");
		//user.getMenu().get(3).setImageURL("quesadilla.jpg");
		//user.getMenu().get(4).setImageURL("chickenfinger.jpg");
		for (int i = 0; i < user.getMenu().size(); i++) {

			HBox menuNode = new HBox();
			Label mnName = new Label(user.getMenu().get(i).getName());
			mnName.setPadding(new Insets(0, 30, 0, 0));
			String pricetoString = String.valueOf(user.getMenu().get(i).getPrice());
			Label mnPrice = new Label(pricetoString);
			mnPrice.setPadding(new Insets(0, 30, 0, 0));
			Label mnDesc = new Label(user.getMenu().get(i).getDescription());
			mnDesc.setPadding(new Insets(0, 30, 0, 0));
			
			menuNode.setPadding(new Insets(20, 20, 20, 20));
			menuDisplay.getChildren().add(menuNode);
			Image img = new Image(user.getMenu().get(i).getImageURL());
			ImageView imageView = new ImageView(img);
			imageView.setFitHeight(25);
			imageView.setFitWidth(25);
			menuNode.getChildren().addAll(mnName, mnPrice, mnDesc, imageView);

		}
		menuDisplay.setPadding(new Insets(30, 30, 30, 30));
		root.setCenter(menuDisplay);
		
		HBox addBox = new HBox();
        Button addItem = new Button ("Add Item");
        TextField addField = new TextField("Name of Item to Add");
        addBox.getChildren().addAll(addField, addItem);
        root.setRight(addBox);

		cartPageButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				CartPage cart = new CartPage();
				Stage cartStage = new Stage();
				user.setCart(user.getCart());
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
				for(int i = 0; i < user.getMenu().size(); i++) {
					
					if(user.getMenu().get(i).getName().equals(itemToAdd)) {
						
						user.addToCart(user.getMenu().get(i));
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
