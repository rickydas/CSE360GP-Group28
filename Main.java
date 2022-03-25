package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application implements EventHandler<ActionEvent> {

    Stage window;

    Button homePageButton = new Button("Home Page");
    Button menuPageButton = new Button("Menu");
    Button cartPageButton = new Button("Cart");
    Button profilePageButton = new Button("Profile");

    Button loginButton = new Button("Login");
    Button registerButton = new Button("Register");

    PasswordField passwordInputField = new PasswordField();
    TextField emailInputField = new TextField("Email");

    Scene homePage, menuPage, cartPage, profilePage;

    ArrayList<User> userList = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Home Page");
        window = primaryStage;

        //Setting up buttons that appear on the top of the screen
        HBox topButtons = new HBox();
        topButtons.setPadding(new Insets(10, 10, 10, 10));
        topButtons.setSpacing(20);
        topButtons.getChildren().addAll(homePageButton, menuPageButton, cartPageButton, profilePageButton);


        //Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));

        //Setting up login/register area
        GridPane loginPane = new GridPane();


        loginPane.setPadding(new Insets(10, 10, 10 , 10));
        loginPane.setHgap(10);
        loginPane.setVgap(10);

        Label emailLabel = new Label("Enter email:");
        GridPane.setConstraints(emailLabel, 1, 1);

        Label passwordLabel = new Label("Enter password:");
        GridPane.setConstraints(passwordLabel, 1, 2);


        //Text/Password Fields for user set up
        GridPane.setConstraints(emailInputField, 2, 1);
        GridPane.setConstraints(passwordInputField, 2, 2);

        GridPane.setConstraints(registerButton, 1, 3);

        GridPane.setConstraints(loginButton, 2, 3);

        loginPane.getChildren().addAll(emailLabel, passwordLabel, emailInputField, passwordInputField, registerButton, loginButton);

        //Setting up overall login screen
        BorderPane windowPane = new BorderPane();
        windowPane.setPadding(new Insets(10, 10, 10, 10));
        windowPane.setRight(loginPane);
        windowPane.setTop(topButtons);

        Scene scene = new Scene(windowPane, 720, 480);




        window.setScene(scene);
        window.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event){

        if(event.getSource() == registerButton){

            boolean registerAttempt = registerAccount();

            if(registerAttempt == false){
                System.out.println("Already exists");
                //emailInputField.setText("A user with the email: " + emailInputField.getPromptText() + "already exists");

            } else {
                //Change scene to home page
            }
        }
    }

    public boolean registerAccount(){

        User newUser = new User(emailInputField.getText(), passwordInputField.getText());
        for(int i = 0; i < userList.size(); i++){

            if(userList.get(i).getUserName().equals(newUser.getUserName())){
                return false;
            }
        }

        userList.add(newUser);
        return true;
    }
}
