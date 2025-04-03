import java.util.ArrayList;


public class Player
{
  
  // attributes for the player's inventory
  private ArrayList<LakeObject> inventory;
  private int maxInventory;
  private int cash;
  private String currentLocation;

  /*---------- constructor ----------*/
  public Player()
  {
    cash = 50; // starting cash
    inventory = new ArrayList<LakeObject>(); // 16 spaces for inventory
    inventory.add(new Wallet()); // Wallet must be first item
    inventory.get(0).setCost(cash);
    inventory.add(new Hook());
    inventory.add(new Bait());      
    maxInventory = 16;
    currentLocation = "forest";
  }

  /*---------- accessors ----------*/
  public int getWallet()
  {
    return cash;
  }
  public ArrayList<LakeObject> getInventory()
  {
    return inventory;
  }
  public String getCurrentLocation()
  {
    return currentLocation;
  }
  public int getInventorySize()
  {
    return inventory.size();
  }
  public int getMaxInventory()
  {
    return maxInventory;
  }

  /*---------- mutators ----------*/
  public void updateWallet(int cash)
  {
    this.cash += cash;
    // Wallet is item 0
    inventory.get(0).setCost(this.cash);

  }

  public void setCurrentLocation(String location)
  {
    currentLocation = location;
  }

  /*---------- additional methods ----------*/
  public boolean hasHook()
  { 
    boolean hasHook = false;
    for (LakeObject item : inventory)
    {
      if (item == null)
        hasHook = false;
      // Object methods getClass().getName() return the name of the subclassed item
      else if (item.getClass().getName().equals("Hook"))
        return true;
    }
    return hasHook;
  }


  public boolean hasBait()
  {
    boolean hasBait = false;
    for(LakeObject item : inventory)
    {
      if (item == null)
        hasBait = false;
      // Object methods getClass().getName() return the name of the subclassed item
      else if (item.getClass().getName().equals("Bait"))
        return true;
    }
    return hasBait;
  }


  public Hook getStrongestHook()
  { 
    Hook strongestHook = null;

    if (hasHook())
    {
      for (LakeObject item : inventory)
      {
      // Object methods getClass().getName() return the name of the subclassed item
      if (item.getClass().getName().equals("Hook"))
          strongestHook = (Hook)item;
      }
    }
    return strongestHook;
  }
  

  public LakeObject getItem(int index)
  {
       return inventory.get(index);
  }


  public String getInventoryName(int index)
  {
    return inventory.get(index).getObjectName();
  }
  
  

  public void updateInventory(LakeObject newItem, boolean toAdd)
  {
    if (roomInInventory() && toAdd)
    {
      if (roomInInventory())
      {
        inventory.add(newItem);
        // Object methods getClass().getName() return the name of the subclassed item
        if (newItem.getClass().getName().equals("Fish"))
        {
          loseItem("Bait");
        }
      }
    }
    else
    {
      loseItem("Hook");
    }
  }


  public void loseItem(String itemType)
  {
    for (int i = 0; i < inventory.size(); i++)
    {
      if (inventory.get(i) != null)
      {
        // Object methods getClass().getName() return the name of the subclassed item
        if (inventory.get(i).getClass().getName().equals(itemType))
        {
          inventory.remove(i); 
          break;
        }
    }
    }
  }


  public boolean loseItem(String itemType, int location, boolean sold) 
  {
      // Object methods getClass().getName() return the name of the subclassed item  
    if (sold && (location > 0) && inventory.get(location-1).getClass().getName().equals(itemType))
    {
      updateWallet(inventory.get(location-1).getCost());
      inventory.remove(location-1);
      return true;
    }
    return false;
  
  }


  public boolean roomInInventory() {
    if (inventory.size() < maxInventory) {
      return true;
    }
    return false;
  }
}