package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Garry on 2015-10-08.
 */
public class InventoryTest extends ActivityInstrumentationTestCase2 {
    public InventoryTest(){
        super(MainMenu.class);
    }

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

    public void testViewItem(){

    }

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

    public void testModifyItems(){
        Item item = new Item();
        item.setName("Vehicle");
        assertTrue(item.name == "Vehicle");
        item.setName("Car");
        assertTrue(item.name == "Car");
    }

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


}
