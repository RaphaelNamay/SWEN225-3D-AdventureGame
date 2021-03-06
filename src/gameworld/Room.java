package gameworld;

import gameworld.Location.Direction;
import gameworld.entities.Door;
import gameworld.entities.Item;
import gameworld.entities.PickUpAbleStrategy;
import gameworld.entities.Player;
import java.util.HashMap;
import java.util.Map;


/**
 * The room handles all the object within it such as removing, adding, and
 * moving them.
 * 
 * @author Deanne Alabastro 300346210
 */

public class Room {

  /** The Constant SIZE of the room. */
  public static final int SIZE = 7;

  /** The locations within the room. */
  Location[][] locations = new Location[SIZE][SIZE];

  /** The game items within the room. */
  Map<Location, Item> gameItems = new HashMap<Location, Item>();

  /** The has player. */
  boolean hasPlayer;

  /** The name. */
  String name;

  /**
   * Instantiates a new room.
   *
   * @param name
   *          the name
   */
  public Room(String name) {
    this.name = name;
    initialiseLocations();
  }

  /**
   * Initialise locations.
   */
  private void initialiseLocations() {
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        locations[row][col] = new Location(row, col);
      }
    }
  }

  /**
   * Gets all the room's locations.
   *
   * @return the locations
   */
  public Location[][] getLocations() {
    return locations;
  }

  /**
   * Gets the location given a row and column.
   *
   * @param row
   *          the row
   * @param col
   *          the col
   * @return the location
   */
  public Location getLocation(int row, int col) {
    return locations[row][col];
  }

  /**
   * Gets the game items in the room.
   *
   * @return the gameItems
   */
  public Map<Location, Item> getGameItems() {
    return gameItems;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Checks for player.
   *
   * @return the hasPlayer
   */
  public boolean hasPlayer() {
    return hasPlayer;
  }

  /**
   * Sets the checks for player.
   *
   * @param hasPlayer
   *          the hasPlayer to set
   */
  public void setHasPlayer(boolean hasPlayer) {
    this.hasPlayer = hasPlayer;
  }

  /**
   * Adds the game object.
   *
   * @param row
   *          the row
   * @param col
   *          the col
   * @param e
   *          the e
   */
  public void addGameItem(int row, int col, Item e) {
    this.gameItems.put(locations[row][col], e);
    if (e.isSolid()) {
      locations[row][col].setSolid(true);
    }
    if (e.isDoor()) {
      locations[row][col].setDoor(true);
    }
  }

  /**
   * Player drop game item - used to add items to the room when player drops it.
   *
   * @param e
   *          the e
   * @return the string
   */
  public String playerDropGameItem(PickUpAbleStrategy e) {
    // check if there is already an item (besides player) on player location
    Item playerLocItem = gameItems.get(Player.getInstance().getLocation());
    if (playerLocItem == null) {
      Player.getInstance().getInventory().remove(e);
      addGameItem(Player.getInstance().getLocation().getRow(),
          Player.getInstance().getLocation().getCol(), new Item(e));
      return "Player dropped " + e.getName();
    } else {
      return "Player cannot drop " + e.getName() + " location is occupied.";
    }

  }

  /**
   * Removes the game item from specified location.
   *
   * @param loc
   *          the loc
   */
  public void removeGameItem(Location loc) {
    this.gameItems.remove(loc);
  }

  /**
   * Removes the game item from specified row and col.
   *
   * @param row
   *          the row
   * @param col
   *          the col
   */
  public void removeGameItem(int row, int col) {
    this.gameItems.remove(locations[row][col]);
  }
  
  /**
   * Removes the game object given the item.
   *
   * @param e
   *          the e
   */
  public void removeGameItem(PickUpAbleStrategy e) {
    Location loc = null;
    for (Map.Entry<Location, Item> entry : gameItems.entrySet()) {
      if (entry.getValue().getItem().equals(e)) {
        loc = entry.getKey();
      }
    }
    this.gameItems.remove(loc);
  }

  /**
   * Gets the game item location.
   *
   * @param e
   *          the e
   * @return the game item location
   */
  public Location getGameItemLocation(Item e) {
    for (Map.Entry<Location, Item> entry : gameItems.entrySet()) {
      if (entry.getValue().getItem().equals(e.getItem())) {
        return entry.getKey();
      }
    }
    return null;
  }

  /**
   * Move player.
   *
   * @param dir
   *          the dir
   * @return true, if successful
   */
  public boolean movePlayer(Direction dir) {
    int playerLocRow = Player.getInstance().getLocation().getRow();
    int playerLocCol = Player.getInstance().getLocation().getCol();
    Location playerLoc = locations[playerLocRow][playerLocCol];
    Player.getInstance().setDirection(dir);
    Item itemInLoc = gameItems.get(playerLoc);

    switch (dir) {
      case NORTH:
        // check if player can move into loc
        if (!playerLoc.isDoor()
            && locations[playerLocRow - 1][playerLocCol].isSolid()) {
          return false;
        } else if (playerLoc.isDoor() 
            && itemInLoc.getItem().getDirection().equals(Direction.SOUTH)) {
          return movePlayerToConnectingRoom(Direction.NORTH);
        }
  
        // move normally
        Player.getInstance().setLocation(locations[playerLocRow - 1][playerLocCol]);
        return true;
      case EAST:
        if (!locations[playerLocRow][playerLocCol].isDoor()
            && locations[playerLocRow][playerLocCol + 1].isSolid()) {
          return false;
        } else if (playerLoc.isDoor() 
            && itemInLoc.getItem().getDirection().equals(Direction.WEST)) {
          return movePlayerToConnectingRoom(Direction.EAST);
        }
  
        Player.getInstance().setLocation(locations[playerLocRow][playerLocCol + 1]);
        return true;
      case SOUTH:
        if (!locations[playerLocRow][playerLocCol].isDoor()
            && locations[playerLocRow + 1][playerLocCol].isSolid()) {
          return false;
        } else if (playerLoc.isDoor() 
            && itemInLoc.getItem().getDirection().equals(Direction.NORTH)) {
          return movePlayerToConnectingRoom(Direction.SOUTH);
        }
  
        Player.getInstance().setLocation(locations[playerLocRow + 1][playerLocCol]);
        return true;
      case WEST:
        if (!locations[playerLocRow][playerLocCol].isDoor()
            && locations[playerLocRow][playerLocCol - 1].isSolid()) {
          return false;
        } else if (playerLoc.isDoor() 
            && itemInLoc.getItem().getDirection().equals(Direction.EAST)) {
          return movePlayerToConnectingRoom(Direction.WEST);
        }
  
        Player.getInstance().setLocation(locations[playerLocRow][playerLocCol - 1]);
        return true;
      default:
        throw new IllegalArgumentException("Direction: " + dir.toString() + " not "
            + "recognised");
    }
  }

  /**
   * Move player to connecting room.
   *
   * @param dir
   *          the dir
   * @return true, if successful
   */
  private boolean movePlayerToConnectingRoom(Direction dir) {
    int playerLocRow = Player.getInstance().getLocation().getRow();
    int playerLocCol = Player.getInstance().getLocation().getCol();

    Door door = (Door) gameItems.get(Player.getInstance().getLocation()).getItem();

    // Player can only move room if door is open and unlocked
    if (!door.isOpen() || door.isLocked()) {
      return false;
    }

    Location[][] connectingRoomLoc = null; // locations of the room player is going to be moving to
    Room goingTo = null; // room the player is going to be moving to

    // check which side of the door player is in
    if (Player.getInstance().getCurrentRoom().equals(door.getFirstRoom())) {
      // get the locations of the other side
      connectingRoomLoc = door.getSecondRoom().getLocations(); 
      goingTo = door.getSecondRoom();
    } else {
      connectingRoomLoc = door.getFirstRoom().getLocations();
      goingTo = door.getFirstRoom();
    }

    // move the player to a location in the other room
    switch (dir) {
      case NORTH:
        Player.getInstance().setLocation(connectingRoomLoc[SIZE - 1][playerLocCol]);
        break;
      case EAST:
        Player.getInstance().setLocation(connectingRoomLoc[playerLocRow][0]);
        break;
      case SOUTH:
        Player.getInstance().setLocation(connectingRoomLoc[0][playerLocCol]);
        break;
      case WEST:
        Player.getInstance().setLocation(connectingRoomLoc[playerLocRow][SIZE - 1]);
        break;
      default:
        throw new IllegalArgumentException("Direction: " + dir.toString() + " not "
            + "recognised");
    }

    // set the player's current room to the new one
    Player.getInstance().setCurrentRoom(goingTo);
    return true;
  }

}
