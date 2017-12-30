package com.sma.Agents;

import com.sma.Main;

/***
 * An Agent for the system
 * An Agent is a Thread : all agents are running at the same time
 */
public abstract class Agent extends Thread {

    private Agent higherAgent; //Agent au dessus de l'agent courant => position courante
    private Agent lowerAgent; //Agent dessous de l'agent courant => position courante
    private Agent targetLowerAgent; //Agent que l'on souhaite avoir dessous soi => objectif
    private char image;

    protected boolean mustMove;

    public Agent(Agent targetLowerAgent, char image) {
        this.targetLowerAgent = targetLowerAgent;
        this.image = image;
        this.mustMove = false;
    }

    public Agent getHigherAgent() {
        return higherAgent;
    }

    public void setHigherAgent(Agent higherAgent) {
        this.higherAgent = higherAgent;
    }

    public Agent getLowerAgent() {
        return lowerAgent;
    }

    public void setLowerAgent(Agent lowerAgent) {
        this.lowerAgent = lowerAgent;
    }

    public Agent getTargetLowerAgent() {
        return targetLowerAgent;
    }

    public char getImage() {
        return image;
    }

    public boolean isMustMove() {
        return mustMove;
    }

    public void setMustMove(boolean mustMove) {
        this.mustMove = mustMove;
    }

    @Override
    public abstract void run();

    /***
     * Check if the agent is at it's target position
     * @return
     */
    public boolean isAtTargetPosition() {
        return getLowerAgent() == getTargetLowerAgent();
    }

    /***
     * Moves an agent to the xth column of the board
     * The agent only moves if there is nobody above him
     * @param x
     */
    public void seDeplacer(int x) {

        synchronized (Main.board) {

            if (getHigherAgent() == null) {
                //on se déplace
                Main.board.moveAgentTo(this, x);
                setMustMove(false);
            } else {
                //on demande à l'agent du dessus de se pousser
                pousser();
            }
        }
    }

    /***
     * Push the agent above the current agent
     */
    public void pousser() {
        getHigherAgent().setMustMove(true);
    }

    /***
     * Make the thread waiting for 1 milis
     */
    protected void sleep() {
        try{
            sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
