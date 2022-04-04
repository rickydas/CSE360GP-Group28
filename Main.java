package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

	Stage window;

	Button loginButton = new Button("Login");
	Button registerButton = new Button("Register");

	PasswordField passwordInputField = new PasswordField();
	TextField userNameField = new TextField("Username");

	ArrayList<User> userList = new ArrayList<User>();

	User user;
	ArrayList<MenuItem> MenuItems = new ArrayList<MenuItem>();
	File userInput = new File("list.txt");
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Application");
		window = primaryStage;
		//this section is used to get users from a file input

		Scanner sc = new Scanner(userInput);
		String userName;
		String password;
		String name;
		String email;
		String phoneNumber;
		String cardNumber;
		String cardMonth;
		String cardYear;
		String backOfCard;
		int couponCounter;
		String newLine;
		
		String[] userInformation;
		while ((sc.hasNextLine())) {
			newLine = sc.nextLine();
			userInformation = newLine.split(",");
			userName = userInformation[0];
			password = userInformation[1];
			User newUser = new User(userName, password);
			if(userInformation.length > 2)
			{
				couponCounter = Integer.parseInt(userInformation[2]);
				name = userInformation[3];
				email = userInformation[4];
				phoneNumber = userInformation[5];
				cardNumber = userInformation[6];
				cardMonth = userInformation[7];
				cardYear = userInformation[8];
				backOfCard = userInformation[9];
				newUser.setName(name);
				newUser.setEmail(email);
				newUser.setPhoneNumber(phoneNumber);
				newUser.setCardNumber(cardNumber);
				newUser.setExperationDateMonth(cardMonth);
				newUser.setExperationDateYear(cardYear);
				newUser.setNumberOnBackOfCard(backOfCard);
			}
			userList.add(newUser);
		}

		// Setting up login/register area
		GridPane loginPane = new GridPane();
		
		loginPane.setPadding(new Insets(10, 10, 10, 10));
		loginPane.setHgap(10);
		loginPane.setVgap(10);

		Label userNameLabel = new Label("Enter username:");
		GridPane.setConstraints(userNameLabel, 1, 1);

		Label passwordLabel = new Label("Enter password:");
		GridPane.setConstraints(passwordLabel, 1, 2);

		// Text/Password Fields for user set up
		GridPane.setConstraints(userNameField, 2, 1);
		GridPane.setConstraints(passwordInputField, 2, 2);

		GridPane.setConstraints(registerButton, 1, 3);

		GridPane.setConstraints(loginButton, 2, 3);
		Label errorMessage = new Label("");
		errorMessage.setTextFill(Color.RED);

		loginPane.getChildren().addAll(userNameLabel, passwordLabel, userNameField, passwordInputField, registerButton,
				loginButton);

		// Setting up overall login screen
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10, 10, 10, 10));
		borderPane.setRight(loginPane);
		borderPane.setCenter(errorMessage);
		borderPane.setBackground(new Background((new BackgroundFill(Color.rgb(174,198,240), CornerRadii.EMPTY, Insets.EMPTY))));

		Scene scene = new Scene(borderPane, 900, 480);

		registerButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (userNameField.getText() == "" || passwordInputField.getText() == "") {
					System.out.println("Username and password cannot be empty");
				} else {
					boolean validUser = true;
					user = new User(userNameField.getText(), passwordInputField.getText());
					for (int i = 0; i < userList.size(); i++) {

						if (userList.get(i).getUserName().equals(user.getUserName())) {
							validUser = false;
							break;
						}
					}
					if (!validUser) {
						errorMessage.setText("Already exists");
						System.out.println("Already exists");
					} else {
						try {
							//this creates a new user when register button is clicked if that username is not taken already
							FileWriter fWriter = new FileWriter("list.txt", true);
							BufferedWriter bWriter = new BufferedWriter(fWriter);

							bWriter.write(userNameField.getText() + "," + passwordInputField.getText() + "," + 0);
							bWriter.write("," + "name" + "," + "email" + "," + "phoneNumber"+ "," + "cardNumber"+ "," + "00"+ "," + "00" + "," + "000");
							
							bWriter.newLine();
							bWriter.close();
						} catch (IOException e) {
							System.out.println("An error occurred.");
							e.printStackTrace();
						}
						Home homePage = new Home();
						Stage homeStage = new Stage();
						user.setCouponCounter();
						user.setCart(MenuItems);
						homePage.start(homeStage, user);
						window.close();

					}
				}

			}

		});

		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				boolean validUser = false;

				for (int i = 0; i < userList.size(); i++) {

					if (userList.get(i).getUserName().equals(userNameField.getText())
							&& userList.get(i).getPassword().equals(passwordInputField.getText())) {
						validUser = true;
						user = userList.get(i);
						user.setCouponCounter();
						try {
							editLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
				}

				if (validUser) {
					//creates homepage and passes in user information
					Home homePage = new Home();
					Stage homeStage = new Stage();
					user.setCart(MenuItems);
					homePage.start(homeStage, user);
					window.close();

				} else {
					errorMessage.setText("The username or password entered for this user is incorrect");
					System.out.println("The username or password entered for this user is incorrect");
				}

			}
		});

		
		window.setScene(scene);
		window.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
	public void editLine() throws IOException
	{
		//File inputFile = new File("list.txt");

		File tempFile = new File("myTempFile.txt");

		BufferedReader reader = new BufferedReader(new FileReader(userInput));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		Scanner sc = new Scanner(userInput);

		String lineToRemove = user.getUserName();
		String currentLine;

		while((sc.hasNextLine())) {
		    // trim newline when comparing with lineToRemove
		    currentLine = sc.nextLine();
		    String trimmedLine = currentLine.trim();
		    String[] lineBeingRead;
		    lineBeingRead = currentLine.split(",");

		    if(lineBeingRead[0].equals(lineToRemove))
		    	{
		    		continue;
		    	}
		    writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer.write(user.getUserName() + "," + user.getPassword() + "," + user.getCouponCounter() + "," + user.getName() + ","  + user.getEmail() + "," + user.getPhoneNumber() + "," + user.getCardNumber() + "," + user.getExperationMonth() + ","+ user.getExperationYear() + ","+ user.getNumberOnBack());
		writer.newLine();
		writer.close(); 
		sc.close();
		reader.close(); 
		System.gc();
		try {
			  Thread.sleep(2000);
			} catch (InterruptedException e) {
			  e.printStackTrace();
			}
		userInput.delete();
		tempFile.renameTo(userInput);

	}

}
