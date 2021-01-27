import javafx.scene.image.Image;

import java.util.*;

/**
 * @author XU LI, s2058650@ed.ac.uk
 * @version 24/11/2020
 */
public class Player {
    private Room currentRoom;
    private Direction currentDirection;
    Set<Item> allItems = new HashSet<>();
    Set<Item> itemsLeft = new HashSet<>();
    HashMap<String,List<Item>> items_map = new HashMap<>();

    /**
     * when initializing the player, create all rooms and items.
     */
    public Player(){
        createRooms();
        createItems();
    }

    /**
     * Get the name of current room
     * @return Current room name
     */
    public String getCurrentRoomName(){
        return currentRoom.getName();
    }

    /**
     * Get current direction
     * @return Current direction
     */
    public String getCurrentDirection(){
        return currentDirection.getDirection();
    }

    /**
     * Get the image of current room & direction
     * @return Current the image of current room & direction
     */
    public Image getCurrentImage(){
        return currentRoom.getPictures(currentDirection.getDirection());
    }

    /**
     * Get the image of current logicmap
     * @return current logicmap
     */
    public Image getCurrentLogicMap(){
        return currentRoom.getLogicMap(currentDirection.getDirection());
    }

    /**
     * change current direction when turn left or right
     * @param Direction 'left' or 'right': Turn left or right
     */
    public void turn(String Direction){
        currentDirection.turn(Direction);
    }

    /**
     * change current room when move forward
     */
    public void goFoward(){
        if (hasNext()){
            this.currentRoom = currentRoom.getExits(currentDirection.getDirection());
        }
    }

    //Check if there is an exit at current room and current direction.
    public boolean hasNext(){
        return currentRoom.getExits(currentDirection.getDirection()) != null;
    }

    /**
     * Drop an item and add that item to items_map
     * @param item The item user try to drop
     */
    public void Drop(Item item){
        String key = currentRoom.getName()+":"+currentDirection.getDirection();
        List<Item> itemsHere = new ArrayList<>();
        if(items_map.get(key)==null){
            itemsHere.add(item);
        }else{
            itemsHere = items_map.get(key);
            itemsHere.add(item);
        }
        items_map.put(key, itemsHere);
    }

    /**
     * Pick up an item and remove that item from items_map
     * @param item The item user try to pick
     */
    public void Pick(Item item){
        String key = currentRoom.getName()+":"+currentDirection.getDirection();
        List<Item> itemsHere = items_map.get(key); // All items at this position
        itemsHere.remove(item); // Remove this item
        items_map.put(key, itemsHere); // Put the rest items back
    }

    /**
     * Get the set of all items
     * @return all items, including items on the map & items on the user
     */
    public Set<Item> getAllItems(){
        return allItems;
    }

    /**
     * Get the item on current position
     * @return items at this position
     */
    public List<Item> getItems(){
        String key = currentRoom.getName()+":"+currentDirection.getDirection();
        return items_map.get(key);
    }

    /**
     * Get the items on the user.
     * @return items on the user
     */
    public Set<Item> getRestItems(){
        itemsLeft.clear();
        for(List<Item> temp:items_map.values()){
            if(!temp.equals(getItems())){ // If an item is not on the map, it must be on the user
                itemsLeft.addAll(temp);
            }
        }
        return itemsLeft;
    }


    /**
     * Initialize all items in this program
     */
    public void createItems() {
        String[] icons = {"bear", "cat", "dog", "fox", "panda", "lion", "rabbit"};
        for (String icon : icons) {
            this.allItems.add(new Item(icon, "icons/" + icon + ".png"));
        }
    }

    /**
     * Initialize all rooms in this program
     */
    private void createRooms()
    {
        Room hallA, hallB, hallC, hallD, toilet, kitchenA, kitchenB, roomA, roomB;
        // create the Rooms
        hallA = new Room("hallA","Just entering the hallway of the dormitory");
        hallB = new Room("hallB","Middle of hallway");
        hallC = new Room("hallC","Middle of hallway");
        hallD = new Room("hallD","End of hallway");
        toilet = new Room("toilet","in the toilet");
        kitchenA = new Room("kitchenA","in the kitchen");
        kitchenB = new Room("kitchenB","in the kitchen");
        roomA = new Room("roomA","in the Room A");
        roomB = new Room("roomB","in the Room B");

        Room[] rooms = {hallA, hallB, hallC, hallD, toilet, kitchenA, kitchenB, roomA, roomB};
        String[] directions = {"north", "south", "east", "west"};

        // Set pictures
        for (Room r : rooms) {
            for (String direction : directions){
                r.setPictures(direction,"pictures/"+r.getName()+'_'+direction+".jpg");
                r.setLogicMap(direction,"pictures/"+r.getName()+'_'+direction+".png");
            }
        }

        // initialize Room exits
        hallA.setExits("north", hallB);
        hallA.setExits("west", roomA);

        hallB.setExits("north", hallC);
        hallB.setExits("east", kitchenA);
        hallB.setExits("south", hallA);

        hallC.setExits("north", hallD);
        hallC.setExits("west", roomB);
        hallC.setExits("south", hallB);

        hallD.setExits("north", toilet);
        hallD.setExits("south", hallC);

        toilet.setExits("south", hallD);

        kitchenA.setExits("south", kitchenB);
        kitchenA.setExits("west", hallB);

        kitchenB.setExits("north", kitchenA);

        roomA.setExits("east", hallA);

        roomB.setExits("east", hallC);

        this.currentRoom = hallA;
        this.currentDirection = Direction.North;

    }

}
