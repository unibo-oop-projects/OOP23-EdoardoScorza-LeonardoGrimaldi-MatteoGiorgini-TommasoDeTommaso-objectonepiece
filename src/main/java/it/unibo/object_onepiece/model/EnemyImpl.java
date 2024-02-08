package it.unibo.object_onepiece.model;

import java.util.ArrayList;
import java.util.List;

import it.unibo.object_onepiece.model.Utils.*;

public class EnemyImpl extends Ship implements Enemy {
    private final List<EnemyState> enemyStates;
    private EnemyState currentState;
    
    protected EnemyImpl(Section section, Position position, CardinalDirection direction) {
        super(section, position, direction);
        enemyStates = new ArrayList<>(List.of(
            new Patrol(this, new Compass(this.getPosition(),section.getBounds())),
            new ObstacleAvoidance(this),
            new AttackState(this)
        ));
        currentState = findState(States.PATROLLING);
    }

    protected Section getSection() {
        return super.getSection();
    }


    @Override
    public void goNext() {
        while (!currentState.perform());
    }

    @Override
    public States getCurrentState() {
        return currentState.getState();
    }

    @Override
    public void changeState(States state) {
       this.currentState = findState(state);
    }

    private EnemyState findState(States stato){
        return enemyStates.stream().filter(x -> x.getState().equals(States.PATROLLING)).findFirst().get();
    }
}
