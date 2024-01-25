package it.unibo.object_onepiece.model;

public class IslandImpl extends EntityImpl implements Island {

    private final int healthGiven;

    public IslandImpl(int healthGiven) {
        this.healthGiven = healthGiven;
    }

    @Override
    public void save() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void heal(Player player) {
        player.heal(healthGiven);
    }
    
}
