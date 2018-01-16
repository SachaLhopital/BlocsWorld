package com.sma.Agents;

import com.sma.Main;

/***
 * Specific agent that doesn't take place on the bottom of someone greater then himself.
 * For instance : 'A' will never take place above 'B', unless there is no other options.
 */
public class AgentCognitiveIncreasing extends Agent {

    public AgentCognitiveIncreasing(Agent targetLowerAgent, char image) {
        super(targetLowerAgent, image);
    }

    @Override
    public void run() {

        while(Main.board.isNotComplete()) {

            if(isMustMove() || !isAtTargetPosition()) {
                moves();
            }

            sleep();
        }
    }

    /***
     * Find a column where the agent can move.
     * A ponderation is created to find the better column :
     * > If the column allow the agent to reach it's target position : +10
     * > If the column break the order rule : -2
     * > If the column doesn't break the order rule but doesn't allow the agent to reach it's target position : 1
     */
    private void moves() {
        synchronized (Main.board) {

            int bestColumnToMoveOn = 0;
            int bestPonderationValue = 0;
            int ponderationValue;

            for(int i = 0; i < Main.board.NB_COLUMN; i++) {

                if((i == Main.board.getColumn(this))) {
                    ponderationValue = -10;
                } else {

                    if(shouldBeUnderMe(Main.board.getHeadOfColumn(i))) {
                        ponderationValue = Main.board.isColumnInFinalState(i) ? 10 : 1;
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
     * Check if agent specified in parameter should be under the current agent or not.
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
