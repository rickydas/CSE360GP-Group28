package sample;

import java.util.ArrayList;

public class User {


    private String name;
    private String password;

    //Constructor for user objects
    public User(){

        name = "";
        password = "";

    }

    public User(String userName, String userPassword){

        name = userName;
        password = userPassword;

    }

    //Setter methods for a user
    public void setUserName(String userName){

        name = userName;
    }

    public void setPassword(String userPassword){

        password = userPassword;
    }

    //Accessor methods for a user
    public String getUserName(){

        return name;
    }

    public String getPassword(){

        return password;
    }


}
