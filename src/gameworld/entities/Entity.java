package gameworld.entities;

import controller.Controller;
import gameworld.Location;

/**
 * The Entity class includes any attackable thing that may optionally drop items upon death
 */
public abstract class Entity {
	
	/** The controller. */
	protected Controller controller;
	
	/** The location. */
	private Location location;
	
	/** The active. */
	protected boolean active = true;
	
	/**
	 * Instantiates a new entity.
	 *
	 * @param controller the controller
	 * @param location the location
	 */
	public Entity(Controller controller, Location location) {
		this.controller = controller;
		this.location = location;
	}
	
	// ********** ABSTRACT METHODS ********** //
	/**
	 * Update.
	 */
	abstract protected void update();
	
	/**
	 * Render.
	 */
	abstract protected void render();
	
	// ********** GETTERS AND SETTERS ********** //

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Checks if is active.
	 *
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets if active.
	 *
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}
