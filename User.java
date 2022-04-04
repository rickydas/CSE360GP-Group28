package application;

import java.util.ArrayList;

public class User {


    private String userName;
    private String name;
    private String password;
    private String cardNumber;
    private String email;
    private String phoneNumber;
    private boolean isOwner = false;
	private String experationDateYear;
	private String codeOnBack;
	private String experationDateMonth;
	private boolean hasCoupon;
	private int couponCounter;
	private ArrayList<MenuItem> CartItems = new ArrayList<MenuItem>();
	private ArrayList<MenuItem> MenuItems = new ArrayList<MenuItem>();
    //Constructor for user objects
    public User(){

        name = "";
        password = "";

    }
    public String getExperationMonth()
    {
    	return experationDateMonth;
    }
    public String getExperationYear()
    {
    	return experationDateYear;
    }
    public String getNumberOnBack()
    {
    	return codeOnBack;
    }
    public boolean getCoupon()
    {
        return hasCoupon;
    }
    public void setCouponCounter()
    {
    	couponCounter += 1;
    }
    public int getCouponCounter()
    {
    	return couponCounter;
    }

    public User(String userName, String userPassword){

        this.userName = userName;
        password = userPassword;

        if(userName.equals("Owner") && password.equals("CSE360")){
            isOwner = true;
        }

    }

    //Setter methods for a user
    public void setUserName(String userName){

        this.userName = userName;
    }

    public void setPassword(String userPassword){

        password = userPassword;
    }

    //Accessor methods for a user
    public String getUserName(){

        return userName;
    }

    public String getPassword(){

        return password;
    }
    public void setCoupon(boolean coupon)
    {
        this.hasCoupon = coupon;
    }

    public void setCardNumber(String newCardNumber)
    {
        if(newCardNumber.matches("[1-9]+"))
        {
            this.cardNumber = newCardNumber;
        }
    }

    public void setExperationDateMonth(String month)
    {
        if(month.matches("[1-9]+"))
        {

            this.experationDateMonth = month;

        }

    }

    public void setExperationDateYear(String year)
    {
        if(year.matches("[1-9]+"))
        {

            this.experationDateYear = year;
        }

    }

    public void setNumberOnBackOfCard(String newNumber)
    {
        if(newNumber.matches("[1-9]+"))
        {
            this.codeOnBack = newNumber;
        }
        
    }

    public void setName(String newName)
    {
        if(newName.matches("[a-zA-Z]+"))
        {
            this.name = newName;
        }


    }

    public void setEmail(String newEmail)
    {
        this.email = newEmail;
    }

    public void setPhoneNumber(String newPhoneNumber)
    {
        if(newPhoneNumber.matches("[1-9]+"))
        {
            this.phoneNumber = newPhoneNumber;

        }

    }

    public String getPhoneNumber()
    {
        if(phoneNumber == null)
        {
            return "Please set your phone number";
        }
        else
        {
            return this.phoneNumber;
        }

    }
    public String getEmail()
    {
        if(email == null)
        {
            return "Please set your email";
        }
        else
        {
            return this.email;
        }

    }

    public String getName()
    {
        if(name == null)
        {
            return "Please set your name";
        }
        else
        {
            return this.name;
        }
    }
    public String getCardNumber()
    {
        if(cardNumber == null)
        {
            return "Please set your payment information";
        }
        else
        {
            return this.cardNumber;
        }

    }
    
    public ArrayList<MenuItem> getCart(){
    	
    	return this.CartItems;
    	
    }
    public ArrayList<MenuItem> getMenu(){
    	return this.MenuItems;
    }
    public void setMenu() {
    	this.MenuItems = new ArrayList<MenuItem>();
    }
    
    public void addToCart(MenuItem item)
    {
    	
    	CartItems.add(item);
    	
    }
    
    public void deleteFromCart(String name) {
    	
    	
    	for(int i = 0; i < CartItems.size(); i++) {
    		
    		if(name.equals(CartItems.get(i).getName())){
    			
    			CartItems.remove(i);
    			break;
    			
    		}
    		
    	}
    	
    }
    public void setCart(ArrayList<MenuItem> cart)
    {
    	this.CartItems = cart;
    }
    
    
}
