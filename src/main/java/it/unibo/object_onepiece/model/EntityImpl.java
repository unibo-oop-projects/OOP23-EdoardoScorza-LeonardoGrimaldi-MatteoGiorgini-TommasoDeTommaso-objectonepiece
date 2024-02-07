package it.unibo.object_onepiece.model;
import it.unibo.object_onepiece.model.Utils.Position;
import it.unibo.object_onepiece.model.events.Event;
import it.unibo.object_onepiece.model.events.EventArgs.Argument;
import it.unibo.object_onepiece.model.events.EventArgs.BiArgument;

/**
 * Abstract class that defines the implementation of the Entity interface.
 */
public abstract class EntityImpl implements Entity {
    private final Section section;
    private Position position;

    private final Event<BiArgument<Position>> onPositionChanged = Event.get();
    private final Event<Argument<Position>> onEntityRemoved = Event.get();

    /**
     * Constructor for class EntityImpl.
     * 
     * @param  s section where the entity should be located
     * @param  p position of the entity in the section
     */
    protected EntityImpl(final Section s, final Position p) {
        this.section = s;
        this.setPosition(p);
    }

    /**
     * Getter for the current section of the entity.
     * 
     * @return the section where the entity is located.
     */
    @Override
    public Section getSection() {
       return this.section;
    }

    /**
     * Getter for the current position of the entity.
     * 
     * @return the position of the entity in the section.
     */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /**
     * Getter for the onPositionChanged Event.
     */
    @Override
    public Event<BiArgument<Position>> getPositionChangedEvent() {
        return this.onPositionChanged;
    }

    /**
     * Getter for the onEntityRemoved Event.
     */
    @Override
    public Event<Argument<Position>> getEntityRemovedEvent() {
        return this.onEntityRemoved;
    }

    /**
     * Setter for the position of the entity.
     * This method invoke an Event onPositionChanged
     * and changes the current position of the Entity
     * to the new position.
     * 
     * @param  newPosition the new position of the entity
     */
    protected void setPosition(final Position newPosition) {
        onPositionChanged.invoke(new BiArgument<>(this.position, newPosition));
        this.position = newPosition;
    }

    /**
     * This method invoke an Event onEntityRemoved
     * and remove the entity from the current section.
     */
    protected void remove() {
        onEntityRemoved.invoke(new Argument<>(this.position));
        this.getSection().removeEntityAt(this.position);
    }
}
