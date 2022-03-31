import java.util.ArrayList;

public class Menufx
{
    //DATA
    private ArrayList<MenuItem> MenuItems = new ArrayList<MenuItem>();


    //Functions
    public Menufx() {
        // constructs menu with default MenuItems

        //first MenuItem
        String name = "Hamburger";
        String desc = "Beef Patty, Lettuce, Tomato, Pickles, Cheese, Mayo\n";
        MenuItem MenuItem = new MenuItem(name , (float) 80, desc);
        MenuItems.add(MenuItem);
        //second MenuItem
        name = "Carnitas Taco";
        desc = "Pork, Onion, Cabbage, Tomato, Tortilla";
        MenuItem MenuItem1 = new MenuItem(name , (float) 80 , desc);
        MenuItems.add(MenuItem1);

        //third MenuItem
        name ="French Fries";
        desc = "Potato, Salt";
        MenuItem MenuItem2 = new MenuItem(name , (float)80 , desc);
        MenuItems.add(MenuItem2);

    }
    //add new MenuItem to the menu
    public void addItem(MenuItem MenuItem)
    {
        MenuItems.add(MenuItem);
    }

    //print menu
    public void print()
    {
        for (int i = 0; i < MenuItems.size(); i++) {
            System.out.println("Item name: " + MenuItems.get(i).getName().toString());
        }
    }


    public void addMenuItem(MenuItem MenuItem)
    {
        MenuItems.add(MenuItem);
    }


    public int get_size()
    {
        return MenuItems.size();
    }

    public MenuItem get_MenuItem(int idx)
    {
        return MenuItems.get(idx);
    }

}