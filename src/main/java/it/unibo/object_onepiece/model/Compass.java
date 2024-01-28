package it.unibo.object_onepiece.model;

import java.util.Random;

import it.unibo.object_onepiece.model.Utils.Direction;
import it.unibo.object_onepiece.model.Utils.Position;

class Compass implements NavigationSystem {
    
    final Random rand = new Random(); 
    Position objective;
    
    public Compass(){
        defineRandomObjective();
    }

    @Override
    public Direction Move(Position objectivePosition,Position currentPosition) {
        var direction = currentPosition.vectorialDirection(objectivePosition);
        return Position.vectorToDirectionMap.get(direction);
    }

    public Direction Move(Position currentPosition){
        if(objectiveReached(objective)){
            defineRandomObjective();
        } 
        return this.Move(objective, currentPosition);
    }

    private void defineRandomObjective(){
        final int maxDistance = 5;
        final int minDistance = 2;

        int x = minDistance + rand.nextInt(maxDistance - minDistance);
        int y = minDistance + rand.nextInt(maxDistance - minDistance);

        objective = objective.translate(new Position(x, y));
    }

    private Boolean objectiveReached(Position position){
        if(position.equals(objective)){
            return true;
        }else{ return false; } 
    }  

}