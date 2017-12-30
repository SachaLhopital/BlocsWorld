package com.sma;

import com.sma.Agents.Agent;
import com.sma.Agents.AgentCognitiveBrodcast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/***
 * Board of the system
 */
public class Board {

    public final int NB_COLUMN = 3;

    private HashMap<Integer, List<Agent>> agents;
    private int nbMovements;

    /***
     * WARNINGS : allAgents must be given in the "start" order !
     * @param allAgents
     */
    public Board (Agent... allAgents) {

        nbMovements = 0;
        agents = new HashMap();
        List<Agent> temp = new ArrayList<>();

        for(Agent a : allAgents) {
            temp.add(a);
        }

        agents.put(0, temp);

        for(int i = 1 ; i < NB_COLUMN; i++) {
            agents.put(i, new ArrayList<>());
        }

        printBoard();
    }

    /***
     * PUBLIC METHODS
     * ***/

    /***
     * Print the board on the console
     */
    public synchronized void printBoard() {

        System.out.println("\n Mouvement n° " + nbMovements++ + "");

        for(int i = 0; i < NB_COLUMN; i++) {
            System.out.print("\n" + i + " |");

            for(int j = 0; j < agents.get(i).size(); j++) {
                System.out.print(" " + agents.get(i).get(j).getImage());
            }
        }
    }

    /***
     * Run all agents
     */
    public void resolve() {
        for(int i = 0; i < agents.get(0).size(); i++) {
            agents.get(0).get(i).start();
        }
    }

    /***
     * Check if the run is complete
     * @return
     */
    public synchronized boolean isNotComplete() {
        for(int i = 0; i < NB_COLUMN; i++) {
            for(int j = 0; j < agents.get(i).size(); j++) {
                if(! agents.get(i).get(j).isAtTargetPosition()) {
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * Get the column number where the agent is
     * @param a
     * @return
     */
    public synchronized int getColumn(Agent a) {
        for(int i = 0; i < NB_COLUMN; i++) {
            for(int j = 0; j < agents.get(i).size(); j++) {
                if(agents.get(i).get(j) == a) {
                    return i;
                }
            }
        }
        return -1;
    }

    /***
     * Moves an agent to a target column
     * (Also update all neigbours)
     * @param agent
     * @param targetColumn
     */
    public synchronized void moveAgentTo(Agent agent, int targetColumn) {

        //Mise à jour des voisins
        Agent newLowerAgent = (agents.get(targetColumn).size() == 0) ? null : agents.get(targetColumn).get(agents.get(targetColumn).size() - 1);

        if(agent.getLowerAgent() != null) {
            agent.getLowerAgent().setHigherAgent(null);
        }

        if(newLowerAgent != null) {
            newLowerAgent.setHigherAgent(agent);
        }

        //Déplacement effectif de l'agent
        for(int i = 0; i < NB_COLUMN; i++) {
            agents.get(i).remove(agent);
        }
        agents.get(targetColumn).add(agent);
        agent.setLowerAgent(newLowerAgent);
        agent.setHigherAgent(null);

        printBoard();
    }

    /***
     * Broadcast everyone that agent is in his target position
     * @param agent
     */
    public synchronized void broadcast(Agent agent) {
        for(int i = 0; i < NB_COLUMN; i++) {
            for(int j = 0; j < agents.get(i).size(); j++) {
                ((AgentCognitiveBrodcast) agents.get(i).get(j)).setBroadcaster(agent);
            }
        }
    }

    /***
     * Get the agent at the top of the columnNumber
     * @param columnNumber
     * @return
     */
    public Agent getHeadOfColumn(int columnNumber) {
        return agents.get(columnNumber).size() > 0
                ? agents.get(columnNumber).get(agents.get(columnNumber).size() - 1)
                : null;
    }

    /***
     * Check if the columnNumber is in final state :
     * |CD
     * |
     * |AB
     * In the example above, the first and the second column return false,
     * while the third one return true because 'A' and 'B' will never move.
     * @param columnNumber
     * @return
     */
    public boolean isColumnInFinalState(int columnNumber) {

        if(agents.get(columnNumber).size() == 0) {
            return false;
        }

        for(int i = 0; i < agents.get(columnNumber).size(); i++) {
            if(! agents.get(columnNumber).get(i).isAtTargetPosition()) {
                return false;
            }
        }
        return true;
    }
}
