public class Items {

    public static void main(String[] arg)
    {
        new Items();
    }

    public Items(){
        itemName();
        itemLocation();
        itemDescription();
    }

    //provides item Name
    public void itemName(){
        System.out.print("itemName");
    }

    //provides item location
    public void itemLocation(){
        System.out.print("itemLocation");
    }

    //provides item description
    public void itemDescription(){
        System.out.print("itemDescription");
    }

}
