package it.unibo.object_onepiece.model;

import it.unibo.object_onepiece.model.Utils.Direction;
import it.unibo.object_onepiece.model.Utils.Position;
import it.unibo.object_onepiece.model.events.Event;
import it.unibo.object_onepiece.model.events.EventArgs.Argument;
import it.unibo.object_onepiece.model.ship.Bow;
import it.unibo.object_onepiece.model.ship.Sail;
import it.unibo.object_onepiece.model.ship.Ship;
import it.unibo.object_onepiece.model.ship.Weapon;

/**
 * Models a particular Ship which is the Player of the game.
 * This distinction is necessary as the Player will also have the possibility of gaining experience.
 * @see Ship
 * @see Viewable
 */
public interface Player extends Viewable, Ship {
    /**
     * Creates a default Player.
     * @param spawnSection the reference to the Section containing this Player
     * @param spawnPosition the position to place this Player at
     * @return the newly created Player object
     */
    static Player getDefault(Section spawnSection, Position spawnPosition) {
        return new PlayerImpl(spawnSection, spawnPosition, Direction.UP, Weapon.cannon(), Sail.sloop(), Bow.standard(), 0);
    }

    /**
     * Getter for the experience private field.
     * @return the currently owned experience value
     */
    int getExperience();

    /**
     * Adds experience to the Player's owned experience.
     * @param experience the experience value to add
     */
    void addExperience(int experience);

    /**
     * Getter for the onExperienceAdded Event.
     * @return an Event object that gets invoked when experience is added to the Player.
     * @see Event
     */
    Event<Argument<Integer>> getExperienceAddedEvent();

    /**
     * @see Viewable
     */
    @Override
    default Type getViewType() {
        return Type.PLAYER;
    }
}
