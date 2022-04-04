package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class Owner extends Application
{
	private String newItemName;
	private String newIngr;
	private String itemRemove;
	private String URL;
	float priceOfItem;


	public void start(Stage primaryStage, User user, ArrayList<MenuItem> MenuItems)
	{
		Button home = new Button("Home Page");
		Button cart = new Button("Cart");
		Button menu = new Button("Menu");
		Button profile = new Button("Profile");

		int height = 50;

		Text addLabel = new Text();
		addLabel.setFont(new Font(25));
		addLabel.setText("Add New Menu Item");

		Text remove = new Text();
		remove.setFont(new Font(25));
		remove.setText("Remove Item");

		Text ingr = new Text();
		ingr.setFont(new Font(20));
		ingr.setText("Item Ingredients:");

		Text name = new Text();
		name.setFont(new Font(20));
		name.setText("Item Name: ");

		Text name2 = new Text();
		name2.setFont(new Font(20));
		name2.setText("Item Name: ");

		Text attPic = new Text();
		attPic.setFont(new Font(20));
		attPic.setText("Item Image URL: ");

		Text price = new Text();
		price.setFont(new Font(20));
		price.setText("Item Price: ");

		Text added = new Text();
		added.setFont(new Font(20));
		added.setText("Item Added to Menu");
		added.setFill(Color.RED);

		Text removed = new Text();
		removed.setFont(new Font(20));
		removed.setFill(Color.RED);

		TextArea addName = new TextArea();
		TextArea removeItem = new TextArea();
		TextArea addIngr = new TextArea();
		TextArea imageURL = new TextArea();
		TextArea price2 = new TextArea();
		price2.setPrefHeight(height);
		imageURL.setPrefHeight(height);
		addName.setPrefHeight(height);
		removeItem.setPrefHeight(height);

		Button a = new Button("Add");
		Button r = new Button("Remove");


		VBox add = new VBox(addLabel, name, addName, ingr, addIngr, attPic, imageURL, price, price2, a);
		VBox removeArea = new VBox(remove, name2, removeItem, r);
		HBox navigation = new HBox(home, menu, cart, profile);

		GridPane gridPane = new GridPane();
		gridPane.setBackground(new Background((new BackgroundFill(Color.rgb(174,198,240), CornerRadii.EMPTY, Insets.EMPTY))));
		gridPane.add(navigation, 0, 0);
		gridPane.add(add, 0, 1);
		gridPane.add(removeArea, 1, 1);

		a.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				newItemName = addName.getText();
				newIngr = addIngr.getText();
				URL = imageURL.getText();
				priceOfItem = Float.parseFloat(price2.getText());

				MenuPage menuPage = new MenuPage();
				MenuItem menuItem = new MenuItem(newItemName, priceOfItem, newIngr,25, URL);
				MenuItems.add(menuItem);

				added.setText(newItemName + " Added to Menu");
				addName.setText("");
				addIngr.setText("");
				imageURL.setText("");
				price2.setText("");
				gridPane.add(added,0, 2);

												
			}
			
		});
		
		r.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				itemRemove = removeItem.getText();
				removeItem.setText("");
				removed.setText(itemRemove + " Removed From Menu");
				gridPane.add(removed, 1, 1);
				//function call to remove item from menu page
			}
			
		});
		
		home.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				Home homePage = new Home();
				Stage homeStage = new Stage();
				homePage.start(homeStage, user, MenuItems);
				primaryStage.close();

			}
			
		});
		
		cart.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				CartPage cartPage = new CartPage();
				Stage cartStage = new Stage();
				cartPage.start(cartStage, user, MenuItems);

				primaryStage.close();
			}

		});
		
		menu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				MenuPage menuPage = new MenuPage();
				Stage menuStage = new Stage();
				menuPage.start(menuStage, user, MenuItems);

				primaryStage.close();
			}
			
		});
		
		profile.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				gridPane.getChildren().clear();
				added.setText("");
				removed.setText("");
				addName.setText("");
				addIngr.setText("");
				price2.setText("");
				imageURL.setText("");
				removeItem.setText("");
				gridPane.add(navigation, 0, 0);
				gridPane.add(add, 0, 1);
				gridPane.add(removeArea, 1, 1);

			}
			
		});
		Scene scene = new Scene(gridPane, 900, 480);
		primaryStage.setScene(scene);
		primaryStage.show();





		
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	public String getItemName()
	{
		return newItemName;
	}
	public String getItemIngr()
	{
		return newIngr;
	}
	public double getPrice()
	{
		return priceOfItem;
	}
	public String picURL()
	{
		return URL;
	}
	public String itemRemove()
	{
		return itemRemove;
	}

	public void setUpOwnerPage(){

	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}