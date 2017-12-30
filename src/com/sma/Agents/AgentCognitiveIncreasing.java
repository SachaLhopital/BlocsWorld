package com.sma.Agents;

import com.sma.Main;

import java.util.HashMap;

/***
 * Specific agent that doesn't take place on the bottom of someone grather then himself.
 * For instance : 'A' will never take place above 'B', unless there is no other options.
 */
public class AgentCognitiveIncreasing extends Agent {

    public AgentCognitiveIncreasing(Agent targetLowerAgent, char image) {
        super(targetLowerAgent, image);
    }

    @Override
    public void run() {

        while(Main.board.isNotComplete()) {

            if(isMustMove()) {
                //bouge n'importe où en essayant de respecter l'ordre croissant
                System.out.println("\n" + getImage() + " is asking to move");
                moves();

            } else {

                if(isAtTargetPosition()) {
                    //do nothing
                } else {
                    moves();
                }
            }
            sleep();
        }
    }

    private void moves() {
        synchronized (Main.board) {

            int bestColumnToMoveOn = 0;
            int bestPonderationValue = 0;
            int ponderationValue;

            for(int i = 0; i < Main.board.NB_COLUMN; i++) {

                if((i == Main.board.getColumn(this))) {
                    ponderationValue = -1;
                } else {

                    if(shouldBeUnderMe(Main.board.getHeadOfColumn(i))) {
                        ponderationValue = Main.board.getHeadOfColumn(i) == getTargetLowerAgent() ? 10 : 1;
                    } else {
                        ponderationValue = -2;
                    }
                }

                if(ponderationValue >= bestPonderationValue) {
                    bestColumnToMoveOn = i;
                    bestPonderationValue = ponderationValue;
                }
            }
            seDeplacer(bestColumnToMoveOn);
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

    /***
     * Check if agent should be under me or not
     * @param agent
     * @return
     */
    private boolean shouldBeUnderMe(Agent agent) {

        Agent agentToCheck = agent;

        while(agentToCheck != null && agentToCheck != this) {

            if (agentToCheck.getTargetLowerAgent() == this) {
                return false;
            }

            agentToCheck = agentToCheck.getTargetLowerAgent();
        }
        return true;
    }
}
