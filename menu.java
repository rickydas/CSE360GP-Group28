import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;


public class menu
{
    //DATA
    private ArrayList<MenuItem> MenuItems = new ArrayList<MenuItem>();


    //Functions
    public menu() 
    {
    	
    }
    //add new MenuItem to the menu
    public void addItem(MenuItem MenuItem)
    {
        MenuItems.add(MenuItem);
    }

    //print menu
    public void print()
    {
        for (int i = 0; i < MenuItems.size(); i++) 
        {
            System.out.println("Item name: " + MenuItems.get(i).getItemName().toString());
            System.out.println("Item name: " + MenuItems.get(i).getItemIngr().toString());
            System.out.println("Item name: " + MenuItems.get(i).getPrice().toString());
        }
    }
    
    public void search(String userItemInput)
    {
    	String userItem = userItemInput;
    	MenuItem searchedItem;
        for (int i = 0; i < MenuItems.size(); i++) 
        {
            if(MenuItems.get(i).getItemName().toString().equals(userItemInput)
            {
            	searchedItem = MenuItems.get(i);
            }
        }
    }
    
    Image image = null;
    URL url = new URL("");
    image = ImageIO.read(url);
