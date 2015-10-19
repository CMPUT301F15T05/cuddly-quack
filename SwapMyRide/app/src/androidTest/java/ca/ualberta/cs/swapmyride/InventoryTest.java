package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Garry on 2015-10-08.
 */
public class InventoryTest extends ActivityInstrumentationTestCase2 {
    public InventoryTest(){
        super(MainMenu.class);
    }

    // Use Case 1: Add Item
    public void testAddItem(){
        InventoryList inventoryList = new InventoryList();
        Item item = new Item();
        //list should be empty
        assertTrue(inventoryList.getList() = null);
        inventoryList.add(item);
        //make sure an item was added
        assetTrue(inventoryList.size() == 1);
        //make sure it is the correct item
        assertTrue(inventoryList.getList().get(0) == item);
    }

    // Use Case 2: Browse Inventory
    public void testBrowseInventory(){
        InventoryList inventoryList = new InventoryList();
        Item item = new Item();
        //list should be empty
        assertTrue(inventoryList.getList() = null);
        inventoryList.add(item);
        //check there is an item in the list
        assertFalse(inventoryList.getList().size() == 0);
        //check the item is the item we put into the list
        assertTrue(inventoryList.getList().get(0) == item);
    }

    // Use Case 3: View Item
    public void testViewItem(){
        Item item = new Item();
        item.setPhoto(picture);
        item.setName("Cadillac");
        item.setCategory("Sedan");
        item.setQuality("Good");
        item.setQuantity(1);
        item.setComments("1995 Cadillac");

        assertTrue(item.getPhoto().equals(photo));
        assertTrue(item.getName().equals("Cadillac"));
        assertTrue(item.getCategory().equals("Sedan"));
        assertTrue(item.getQuality().equals("Good"));
        assertTrue(item.getQuantity().equals(1));
        assertTrue(item.getComments().equals("1995 Cadillac"));
    }

    // Use Case 4: Set Public
    public void testSetPublic(){
        InventoryList inventoryList = new InventoryList();
        Item item = new Item();
        item.setPublic(false);
        inventoryList.add(item);
        //check the item does not appear in the public return
        assertTrue(inventoryList.getPublic().size() == 0);
        //check the item does appear in the private list
        assertTrue(inventoryList.getPrivate().size() == 1);
    }

    // Use Case 5: Modify Items
    public void testModifyItems(){
        Item item = new Item();
        item.setName("Vehicle");
        assertTrue(item.name == "Vehicle");
        item.setName("Car");
        assertTrue(item.name == "Car");
    }

    // Use Case 6: Delete Item
    public void testDeleteItem() {
        InventoryList inventoryList = new InventoryList();
        Item item = new Item();
        Item newItem = new Item();
        //list should be empty
        assertTrue(inventoryList.getList() = null);
        inventoryList.add(item);
        inventoryList.add(newItem);
        //check there is an item in the list
        assertFalse(inventoryList.getList().size() == 0);
        inventoryList.delete(newItem);
        //check the size is correct
        assertTrue(inventoryList.getList().size() == 1);
        //check the item left is the correct item
        assertTrue(inventoryList.getList().contains(item));
    }

    // Use Case 7: Minimum Navigation
    // No related test case as evaluation is not concrete

}
