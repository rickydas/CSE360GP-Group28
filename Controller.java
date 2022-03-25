//Pablo's controller
package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;


import java.util.ArrayList;
import java.io.IOException;


public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private ArrayList<User> userList = new ArrayList<>();
    public int userNumber;


    //After logging in, the portion of the page involving logging in will not be displayed
    @FXML
    public void switchToHome(MouseEvent event) throws IOException {

        if(registerAccount(event) == true){ //Successfully registered account

            root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            
        }

    }

    @FXML private TextField emailInput;
    @FXML private TextField passwordInput;

    @FXML
    public boolean registerAccount(MouseEvent event) throws IOException{

        //Make user with correct information
        User newUser = new User();
        newUser.setUserName(emailInput.getText());
        newUser.setPassword(passwordInput.getText() );

        //Check if there are no current users
        if(userList.isEmpty()){

            userList.add(newUser);
            userNumber = 0;
            return true;

        } else { //Filled list of users
            int i;
            for(i = 0; i < userList.size(); i++){ //Iterate through list of users

                if(newUser.getUserName() == userList.get(i).getUserName() ){
                    return false; //Can't register because there is an account with this username already
                }
            }

            userList.add(newUser);
            userNumber = i;
            return true;
        }

    }






}
