package com.sma;

import com.sma.Agents.Agent;
import com.sma.Agents.AgentCognitiveBrodcast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {

    public final int NB_COLUMN = 3;

    private HashMap<Integer, List<Agent>> agents;
    private int nbMovements;

    /***
     * ATTENTION : allAgents should be given in the started order !
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
     * Run all agent in the board
     */
    public void resolve() {
        for(int i = 0; i < agents.get(0).size(); i++) {
            agents.get(0).get(i).start();
        }
    }

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

    public synchronized void moveAgentFromTo(Agent agent, int to) {

        //debug
        if(agent.getImage() == 'D') {
            int x = 2;
        }

        //Mise à jour des voisins
        Agent newLowerAgent = (agents.get(to).size() == 0) ? null : agents.get(to).get(agents.get(to).size() - 1);

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
        agents.get(to).add(agent);
        agent.setLowerAgent(newLowerAgent);
        agent.setHigherAgent(null);

        printBoard();
    }

    /****
     * a broadcast to everyone else that he is in his target position
     * @param a
     */
    public synchronized void broadcast(Agent a) {
        for(int i = 0; i < NB_COLUMN; i++) {
            for(int j = 0; j < agents.get(i).size(); j++) {
                ((AgentCognitiveBrodcast) agents.get(i).get(j)).setBroadcaster(a);
            }
        }
    }

    public Agent getHeadOfColumn(int columnNumber) {
        return agents.get(columnNumber).size() > 0
                ? agents.get(columnNumber).get(agents.get(columnNumber).size() - 1)
                : null;
    }

    public int getEmptiestColumn() {

        int emptiestColumn = -1;

        for(int i = 0; i < NB_COLUMN; i++) {

            int currentSize = agents.get(i).size();

            if(emptiestColumn == -1 || currentSize < emptiestColumn) {
                emptiestColumn = currentSize;
            }
        }
        return emptiestColumn;
    }

    public int getNumberOfAgentProperlyPositionnedForColumn(int columnNumber) {

        int nbAgentProperlyPositionned = 0;

        for(int i = 0; i < agents.get(columnNumber).size(); i++) {
            if(! agents.get(columnNumber).get(i).isAtTargetPosition()) {
                nbAgentProperlyPositionned--;
            }
        }
        return nbAgentProperlyPositionned;
    }

}
