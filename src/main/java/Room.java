import java.util.HashMap;

import javafx.scene.image.Image;

/**
 * Room Class, each Room class represents one room in the map.
 */
public class Room {

    private String name;
    private String description;
    private HashMap<String,Room> exits = new HashMap<>();
    private HashMap<String,Image> pictures = new HashMap<>();
    private HashMap<String,Image> logicMap = new HashMap<>();


    public Room(String name, String description){
        this.name = name;
        this.description = description;
    }

    public void setExits(String direction,Room neighbor){
        exits.put(direction, neighbor);
    }

    public Room getExits(String direction){
        return exits.get(direction);
    }

    public void setPictures(String direction,String path){
        try{
            Image picture = new Image(path);
            pictures.put(direction, picture);
        }catch(Exception e){
            System.out.println("picture error");
        }
    }

    public Image getPictures(String direction){
        return pictures.get(direction);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
            return name;
    }

    public void setLogicMap(String direction,String path){
        try{
            Image picture = new Image(path);
            logicMap.put(direction, picture);
        }catch(Exception e){
            System.out.println("picture error");
        }
    }

    public Image getLogicMap(String direction){
        return logicMap.get(direction);
    }
}
