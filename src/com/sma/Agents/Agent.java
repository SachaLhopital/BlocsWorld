package com.sma.Agents;

public abstract class Agent extends Thread {

    private Agent higherAgent; //Agent au dessus de l'agent courant => position courante
    private Agent lowerAgent; //Agent dessous de l'agent courant => position courante
    private Agent targetLowerAgent; //Agent que l'on souhaite avoir dessous soi pour atteindre son objectif
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

    public boolean isAtTargetPosition() {
        return getLowerAgent() == getTargetLowerAgent();
    }

    /***
     * PROTECTED
     * ***/

    protected void sleep() {
        try{
            sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
