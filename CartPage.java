package application;

import java.util.ArrayList;

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
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class CartPage extends Application {
   // @Override
    public void start(Stage primaryStage, User user) {
        BorderPane root = new BorderPane();
        root.setBackground(new Background((new BackgroundFill(Color.rgb(174,198,240), CornerRadii.EMPTY, Insets.EMPTY))));
        Scene scene = new Scene(root);

        //this is for the menu bar at the top
        HBox menuBar = new HBox();
        Button home = new Button("Home");
        Button menu = new Button("Menu");
        Button cart = new Button("Cart");
        Button profile = new Button("Profile");
        menuBar.setPadding(new Insets(10, 10, 10, 10));
        menuBar.getChildren().addAll(home, menu, cart, profile);
        root.setTop(menuBar);
        
        VBox cartDisplay = new VBox();
        Label yourItems = new Label("Your Items:");
        cartDisplay.getChildren().add(yourItems);
	
	if(user.getCart() != null)
	{
		for(int i = 0; i < user.getCart().size(); i++) {
        	
        		HBox cartNode = new HBox();
        		Label cnName = new Label(user.getCart().get(i).getName());
        		cnName.setPadding(new Insets(0, 30, 0, 0));
        		String pricetoString = String.valueOf(user.getCart().get(i).getPrice());
        		Label cnPrice = new Label("$" + pricetoString);
        		cnPrice.setPadding(new Insets(0, 30, 0, 0));
        		Label cnDesc = new Label(user.getCart().get(i).getDescription());
        		cnDesc.setPadding(new Insets(0, 30, 0, 0));
        		cartNode.getChildren().addAll(cnName, cnPrice, cnDesc);
        		cartNode.setPadding(new Insets(20, 20, 20, 20));
        	
        		cartDisplay.getChildren().add(cartNode);
        	
        	}	
	} else {
		
		//Cart is empty
	}
    
        
        HBox deleteBox = new HBox();
        Button deleteItem = new Button ("Remove Item");
        TextField deleteField = new TextField("Name of Item to Remove");
        deleteBox.getChildren().addAll(deleteField, deleteItem);
        cartDisplay.getChildren().add(deleteBox);
        
        cartDisplay.setPadding(new Insets(30, 30, 30, 30));
        root.setCenter(cartDisplay);
        
        VBox totalDisplay = new VBox();
        
        VBox paymentDisplay = new VBox();
        Label paymentTitle = new Label("Payment");
        Label priceTitle = new Label("Your Total Is:");
        String pricetoString = "0.00";
	if(user.getCart() != null)
	{
		priceToString =	String.valueOf(getTotalPrice(user.getCart()));	
	}
	    
        Label priceDisplay = new Label("$" + pricetoString);
        priceDisplay.setFont(new Font("Arial", 40));
        paymentDisplay.getChildren().addAll(paymentTitle, priceTitle, priceDisplay);
        root.setRight(paymentDisplay);
        paymentDisplay.setPadding(new Insets(15, 100, 30, 30));
        
        VBox cardDisplay = new VBox();
        Label cardUsing = new Label("You are currently using the card ending in:");
        String lastDigits = user.getCardNumber().substring(user.getCardNumber().length() - 4);
        Label currentCard = new Label("x" + lastDigits);
        Button payWithCard = new Button("Pay with x" + lastDigits);
        cardDisplay.getChildren().addAll(cardUsing, currentCard, payWithCard);
        cardDisplay.setPadding(new Insets(0, 30, 30, 30));
        
        VBox cardOptional = new VBox();
        Label option = new Label("Or pay with a new card");
        HBox nameBox = new HBox();
        HBox cardNumberBox = new HBox();
        HBox monthBox = new HBox();
        HBox yearBox = new HBox();
        HBox digitsBox = new HBox();
        
        TextField nameField = new TextField();
        TextField cardNumberField = new TextField();
        TextField monthField = new TextField();
        TextField yearField = new TextField();
        TextField digitsField = new TextField();
        
        Text nameText = new Text("Name: ");
        Text cardNumberText = new Text("Card Number: ");
        Text monthText = new Text("Expiration Month: ");
        Text yearText = new Text("Expiration Year: ");
        Text digitsText = new Text("Digits on back: ");
        Button newCard = new Button("Pay with new card");
        
        nameBox.getChildren().addAll(nameText, nameField);
        cardNumberBox.getChildren().addAll(cardNumberText, cardNumberField);
        monthBox.getChildren().addAll(monthText, monthField);
        yearBox.getChildren().addAll(yearText, yearField);
        digitsBox.getChildren().addAll(digitsText, digitsField);
        
        cardOptional.getChildren().addAll(option, nameBox, cardNumberBox, monthBox, yearBox, digitsBox, newCard);
        
        totalDisplay.getChildren().addAll(paymentDisplay, cardDisplay, cardOptional);
        root.setRight(totalDisplay);
        
        EventHandler<ActionEvent> homeEvent = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e)
        	{
        		Home homePage = new Home();
        		Stage homeStage = new Stage();
        		homePage.start(homeStage, user);
        		primaryStage.close();
        	}
        };
        
        EventHandler<ActionEvent> profileEvent = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e)
        	{
			if(user.getUserName().equals("Admin){
				Owner ownerPage = new Owner();
				Stage ownerStage = new Stage();
				ownerPage.start(ownerStage, user);
				primaryStage.close();
			} else {
				CustomerPage customer = new CustomerPage();
        			Stage customerStage = new Stage();
        			customer.start(customerStage, user);
        			primaryStage.close();	
			}
        		
        	}
        };

        EventHandler<ActionEvent> menuEvent = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e)
        	{
        		MenuPage menu = new MenuPage();
        		Stage menuStage = new Stage();
        		menu.start(menuStage, user);
        		primaryStage.close();
        	}
        };
        
        EventHandler<ActionEvent> deleteEvent = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e)
        	{
        		String deletedName = deleteField.getText();
        		user.deleteFromCart(deletedName);
        		CartPage refreshCart = new CartPage();
        		Stage ncartStage = new Stage();
        		refreshCart.start(ncartStage, user);
        		primaryStage.close();
        		
        	}
        };
        
        EventHandler<ActionEvent> purchaseEvent = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e)
        	{
        		VBox purchaseNotif = new VBox();
        		Label completed = new Label("Purchase Confirmed!");
        		completed.setFont(new Font("Arial", 20));
        		Label summary = new Label("You paid " + String.valueOf(getTotalPrice(user.getCart())));
        		int position = (int) (Math.random() * 10);
        		Label yourPosition = new Label("You are number " + position + " in line!");
        		Label yourWaitTime = new Label("Your order will take " + getWaitTime(user.getCart()) + " minutes to complete");
        		purchaseNotif.getChildren().addAll(completed, summary, yourPosition, yourWaitTime);
        		purchaseNotif.setPadding(new Insets(0, 90, 0, 0));
        		root.setRight(purchaseNotif);
        		
        	}
        };
        
        profile.setOnAction(profileEvent);
        home.setOnAction(homeEvent);
        menu.setOnAction(menuEvent);
        payWithCard.setOnAction(purchaseEvent);
        newCard.setOnAction(purchaseEvent);
        deleteItem.setOnAction(deleteEvent);
        primaryStage.setScene(scene);
        primaryStage.setWidth(900);
        primaryStage.setHeight(480);
        primaryStage.show();

    }
    
    public int getWaitTime(ArrayList<MenuItem> list)
    {
 	   int waitTime = 0;
 	   for(int i = 0; i < list.size(); i++)
 	   {
 		   waitTime += list.get(i).getPrepTime();
 	   }
 	   return waitTime;
    }
    
    public double getTotalPrice(ArrayList<MenuItem> list) {
    	
    	double sum = 0;
    	
    	for(int i = 0; i < list.size(); i++) {
    		
    		sum += list.get(i).getPrice();
    		
    	}
    	
    	return sum;
    	
    }

    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
