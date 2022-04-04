package application;


public class MenuItem
{
    private String name;
    private String imageURL;
    private String description;
    private Float price;
    private Float quantity;
    private int prepTime;

    //constructor
    public MenuItem(String Name , Float Price, String Description, int PrepTime, String URL)
    {
        name = Name;
        price = Price;
        description =Description;
        prepTime = PrepTime;
        imageURL = URL;
    }

    public void setImageURL(String URL){
        this.imageURL = URL;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public void setPrice(Float price) 
    {
        this.price = price;
    }

    public void setQuantity(Float quantity) 
    {
        this.quantity = quantity;
    }
    
    public void setTime(int prepTime)
    {
        this.prepTime = prepTime;
    }

    public String getName() 
    {
        return name;
    }

    public String getDescription() 
    {
        return description;
    }

    public Float getPrice() 
    {
        return price;
    }

    public Float getQuantity() 
    {
        return quantity;
    }
    
    public int getPrepTime()
    {
        return prepTime;
    }

    public String getImageURL(){

        return imageURL;
    }
}
