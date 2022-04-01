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
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class CustomerPage extends Application {
   // @Override
    public void start(Stage primaryStage, User user) {
        BorderPane root = new BorderPane();
        VBox leftSide = new VBox();
        VBox middle = new VBox();
        root.setBackground(new Background((new BackgroundFill(Color.rgb(174,198,240), CornerRadii.EMPTY, Insets.EMPTY))));
        VBox rightSide = new VBox();
        Scene scene = new Scene(root);

        //this is for the menu bar at the top
        HBox menuBar = new HBox();
        Button home = new Button("Home");
        Button menu = new Button("Menu");
        Button cart = new Button("Cart");
        Button profile = new Button("Profile");
        Button editProfile = new Button("Edit Profile");
        menuBar.setPadding(new Insets(10, 10, 10, 10));
        menuBar.getChildren().addAll(home, menu, cart, profile, editProfile);
        root.setTop(menuBar);

        //this is for the left side display
        Text name = new Text(user.getName());
        Text email = new Text(user.getEmail());
        Text paymentInformation = new Text(user.getCardNumber());
        Text paymentMonth = new Text(user.getExperationMonth());
        Text paymentYear = new Text(user.getExperationYear());
        Text phoneNumber = new Text(user.getPhoneNumber());
        leftSide.getChildren().addAll(name, email, phoneNumber, paymentInformation, paymentMonth, paymentYear);
        root.setLeft(leftSide);

        //this is for the edit profile button on the right

     //   rightSide.getChildren().addAll(editProfile);
        root.setRight(rightSide);

        //this is for the middle section of the page
        Text userNameLabel = new Text("Welcome " + user.getUserName());
        userNameLabel.setFont(new Font("Arial", 40));
        userNameLabel.setLayoutX(600);
        userNameLabel.setLayoutY(500);
        middle.getChildren().addAll(userNameLabel);
        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextField phoneNumberField = new TextField();
        TextField cardNumberField = new TextField();
        TextField monthField = new TextField();
        TextField yearField = new TextField();
        TextField digitsField = new TextField();
        VBox middlePortion = new VBox();
        HBox nameBox = new HBox();
        HBox emailBox = new HBox();
        HBox phoneBox = new HBox();
        HBox cardNumberBox = new HBox();
        HBox monthAndYearBox = new HBox();
        HBox digitsBox = new HBox();
        Text nameText = new Text("Name: ");
        Text emailText = new Text("Email: ");
        Text phoneText = new Text("Phone Number: ");
        Text cardNumberText = new Text("Card Number: ");
        Text monthText = new Text("Experation Month: ");
        Text yearText = new Text("Experation Year: ");
        Text digitsText = new Text("Digits on back: ");
        Button submitButton = new Button("Submit");
        nameBox.getChildren().addAll(nameText, nameField);
        emailBox.getChildren().addAll(emailText, emailField);
        phoneBox.getChildren().addAll(phoneText, phoneNumberField);
        cardNumberBox.getChildren().addAll(cardNumberText, cardNumberField);
        monthAndYearBox.getChildren().addAll(monthText, monthField, yearText, yearField);
        digitsBox.getChildren().addAll(digitsText, digitsField);
        middlePortion.getChildren().addAll(middle, nameBox, emailBox, phoneBox, cardNumberBox, monthAndYearBox, digitsBox, submitButton);
        root.setCenter(middlePortion);
        nameBox.setVisible(false);
        emailBox.setVisible(false);
        phoneBox.setVisible(false);
        cardNumberBox.setVisible(false);
        monthAndYearBox.setVisible(false);
        digitsBox.setVisible(false);
        submitButton.setVisible(false);
        EventHandler<ActionEvent> editProfileEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                nameBox.setVisible(true);
                emailBox.setVisible(true);
                phoneBox.setVisible(true);
                cardNumberBox.setVisible(true);
                monthAndYearBox.setVisible(true);
                digitsBox.setVisible(true);
                submitButton.setVisible(true);
                nameField.setText(user.getName());
                emailField.setText(user.getEmail());
                phoneNumberField.setText(user.getPhoneNumber());
                cardNumberField.setText(user.getCardNumber());
                monthField.setText(user.getExperationMonth());
                yearField.setText(user.getExperationYear());
                digitsField.setText(user.getNumberOnBack());


            }

        };
        EventHandler<ActionEvent> submitEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                nameBox.setVisible(false);
                emailBox.setVisible(false);
                phoneBox.setVisible(false);
                cardNumberBox.setVisible(false);
                monthAndYearBox.setVisible(false);
                digitsBox.setVisible(false);
                submitButton.setVisible(false);

                user.setName(nameField.getText());
                user.setEmail(emailField.getText());
                user.setPhoneNumber(phoneNumberField.getText());
                user.setExperationDateMonth(monthField.getText());
                user.setExperationDateYear(yearField.getText());
                user.setCardNumber(cardNumberField.getText());
                user.setNumberOnBackOfCard(digitsField.getText());
                name.setText(user.getName());
                email.setText(user.getEmail());
                phoneNumber.setText(user.getPhoneNumber());
                paymentInformation.setText(user.getCardNumber());
                paymentMonth.setText(user.getExperationMonth());
                paymentYear.setText(user.getExperationYear());
            }
        };
        EventHandler<ActionEvent> homeEvent = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e)
        	{
        		Home homePage = new Home();
        		Stage homeStage = new Stage();
        		homePage.start(homeStage, user);
        		primaryStage.close();
        	}
        };
        editProfile.setOnAction(editProfileEvent);
        submitButton.setOnAction(submitEvent);
        home.setOnAction(homeEvent);
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
