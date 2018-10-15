package gameworld.entities;

import javax.xml.bind.annotation.XmlTransient;

/**
 * The Interface Container.
 * @author yangcarr 300368805
 */

public interface Container {
	
	/**
	 * Place item.
	 *
	 * @param item the item
	 */
	public void placeItem(PickUpAbleStrategy item);		// place an item inside
	
	/**
	 * Take item.
	 *
	 * @return the pick up able strategy
	 */
	public PickUpAbleStrategy takeItem();		// get the item from inside the container
}
