package com.sma.Agents;

import com.sma.Main;
import java.util.Random;

/***
 * Reactive Agent
 * Moves randomly until it get in his target position.
 * If needeed, ask to the higher agent to moves also.
 */
public class AgentReactive extends Agent {

    public AgentReactive(Agent targetLowerAgent, char image) {
        super(targetLowerAgent, image);
    }

    public void run() {

        while(Main.board.isNotComplete()) {

            if(isMustMove() || ! isAtTargetPosition()) {
                movesRandomly();
            }
            sleep();
        }
    }

    /***
     * Moves randomly to another column
     */
    private void movesRandomly() {
        synchronized(Main.board) {
            int newColumn = new Random().nextInt(2);
            newColumn = (newColumn == Main.board.getColumn(this)) ? 2 : newColumn;
            seDeplacer(newColumn);
        }
    }
}
