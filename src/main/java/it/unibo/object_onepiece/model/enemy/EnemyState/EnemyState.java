package it.unibo.object_onepiece.model.enemy.EnemyState;
import it.unibo.object_onepiece.model.enemy.Enemy.States;

public interface EnemyState {
    /**
     * 
     * @return true if the action was executed
     * false if not ( is given that the state has been changed and another attempt will be performed )
     */
    public Boolean perform();
    public States getState();
}
