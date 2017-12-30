package com.sma.Agents;

import com.sma.Main;

import java.util.Random;

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

    /***
     * Moves an agent to the xth column of the board
     * The agent only moves if there is nobody above him
     * @param x
     */
    private void seDeplacer(int x) {

        if(getHigherAgent() == null) {
            //on se déplace
            Main.board.moveAgentFromTo(this, x);
            setMustMove(false);
        } else {
            //on demande à l'agent du dessus de se pousser
            pousser();
        }
    }

    /***
     * Push the agent above the current agent
     */
    private void pousser() {
        getHigherAgent().setMustMove(true);
    }

}
