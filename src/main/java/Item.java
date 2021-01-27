import javafx.scene.image.Image;

/**
 * @Description Item Class
 * @Author XU LI
 */
public class Item {
    private String name;
    private Image image;

    /**
     * Constructor of Item Class
     * @param name the name of the item
     * @param path the path of the corresponding image
     */
    public Item(String name,String path){
        this.name = name;
        try{
            this.image = new Image(path);
        }catch(Exception e){
            System.out.println("item picture error");
        }
    }

    /**
     * Set the name of the item
     * @param name the name of the item
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Get the name of the item
     * @return name the name of the item
     */
    public String getName(){
        return name;
    }

    /**
     * Set the image of the item
     * @param path path of the item image
     */
    public void setImage(String path){
        try{
            this.image = new Image(path);
        }catch(Exception e){
            System.out.println("item picture error");
        }
    }

    /**
     * Get the name of the item
     * @return image image of this item
     */
    public Image getImage(){
        return image;
    }
}
