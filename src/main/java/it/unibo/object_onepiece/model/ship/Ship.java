package it.unibo.object_onepiece.model.ship;
import it.unibo.object_onepiece.model.Collider;
import it.unibo.object_onepiece.model.Utils.Direction;
import it.unibo.object_onepiece.model.events.Event;
import it.unibo.object_onepiece.model.events.EventArgs.BiArgument;
import it.unibo.object_onepiece.model.events.EventArgs.Argument;

/**
 * This interface represents a Ship Entity that extends the Collider interface.
 * It defines methods for taking damage, setting and retrieving weapons, sails, bows, and direction.
 * It also provides access to events related to changes in direction and damage taken.
 */
public interface Ship extends Collider {
    /**
     * Method to inflict damage to the ship.
     * 
     * @param  damage the amout of damage to be inflicted
     * @param  s      the ShipComponent that needs to be hit
     */
    void takeDamage(int damage, ShipComponent s);

    /**
     * Sets the Weapon of the Ship.
     * 
     * @param  weapon the Weapon object to set
    */
    void setWeapon(Weapon weapon);
    
    /**
     * Sets the Sail of the Ship.
     * 
     * @param  sail the Sail object to set
    */
    void setSail(Sail sail);

    /**
     * Sets the Bow of the Ship.
     * 
     * @param  bow the Bow object to set
    */
    void setBow(Bow bow);

    Weapon getWeapon();
    Sail getSail();
    Bow getBow();
    Direction getDirection();
    
    Event<BiArgument<Direction>> getDirectionChangedEvent();
    Event<Argument<Integer>> getTookDamageEvent();

    @Override
    default Rigidness getRigidness() {
        return Rigidness.MEDIUM;
    }
}
