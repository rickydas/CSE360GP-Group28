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

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Application");
		window = primaryStage;
		//this section is used to get users from a file input
		File userInput = new File("accountLog.txt");
		Scanner sc = new Scanner(userInput);
		String userName;
		String password;
		String newLine;
		String[] userNameAndPassword;
		while ((sc.hasNextLine())) {
			newLine = sc.nextLine();
			userNameAndPassword = newLine.split(",");
			userName = userNameAndPassword[0];
			password = userNameAndPassword[1];
			User newUser = new User(userName, password);
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

		loginPane.getChildren().addAll(userNameLabel, passwordLabel, userNameField, passwordInputField, registerButton,
				loginButton);

		// Setting up overall login screen
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10, 10, 10, 10));
		borderPane.setRight(loginPane);
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
						System.out.println("Already exists");
					} else {
						try {
							//this creates a new user when register button is clicked if that username is not taken already
							FileWriter fWriter = new FileWriter(
									"accountLog.txt", true);
							BufferedWriter bWriter = new BufferedWriter(fWriter);

							bWriter.write(userNameField.getText() + "," + passwordInputField.getText());
							bWriter.newLine();
							bWriter.close();
						} catch (IOException e) {
							System.out.println("An error occurred.");
							e.printStackTrace();
						}
						Home homePage = new Home();
						Stage homeStage = new Stage();
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
						break;
					}
				}

				if (validUser) {
					//creates homepage and passes in user information
					Home homePage = new Home();
					Stage homeStage = new Stage();
					homePage.start(homeStage, user);
					window.close();

				} else {
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

}
