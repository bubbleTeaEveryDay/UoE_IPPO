/**
 * @author XU LI, s2058650@ed.ac.uk
 * @version 24/11/2020
 */

public enum Direction {

    North("north"),
    East("east"),
    South("south"),
    West("west");

    private String currentDirection;


    Direction(String direction){
        this.currentDirection = direction;
    }

    /**
     * Get current direction
     * @return current direction
     */
    public String getDirection(){
        return currentDirection;
    }

    /**
     * change currentDirection when turn left or right
     * @param Direction 'left' or 'right': Turn left or right
     */
    public void turn(String Direction){
        if (Direction.equals("Left")) {
            switch (currentDirection) {
                case "north" -> currentDirection = "west";
                case "west" -> currentDirection = "south";
                case "south" -> currentDirection = "east";
                case "east" -> currentDirection = "north";
            }
        }else if (Direction.equals("Right")){
            switch (currentDirection) {
                case "north" -> currentDirection = "east";
                case "east" -> currentDirection = "south";
                case "south" -> currentDirection = "west";
                case "west" -> currentDirection = "north";

            }
        }
    }

}
