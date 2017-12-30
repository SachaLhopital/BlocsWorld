package com.sma.Agents;

import com.sma.Main;

public class AgentCognitiveBrodcast extends Agent {

    private Agent broadcaster;
    private int targetColumn;
    private int forbiddenColumn;

    public AgentCognitiveBrodcast(Agent targetLowerAgent, char image) {
        super(targetLowerAgent, image);

        if(targetLowerAgent == null) {
            //Premier agent à se placer
            targetColumn = 1;
        } else {
            targetColumn = -1;
            broadcaster = null;
        }
    }

    public void setForbiddenColumn(int f) {
        forbiddenColumn = f;
    }

    public Agent getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(Agent broadcaster) {
        this.broadcaster = broadcaster;
    }

    public int getTargetColumn() {
        return targetColumn;
    }

    public void setTargetColumn(int targetColumn) {
        this.targetColumn = targetColumn;
        setForbiddenColumn(-1);
    }

    public int getForbiddenColumn() {
        return forbiddenColumn;
    }

    public void run() {

        while(Main.board.isNotComplete()) {

            if(isMustMove()) {
                //bouger en respectant la contrainte de position
                System.out.println(getImage() + " is asking to move");
                moves();

            } else {

                if(isAtTargetPosition()) {
                    //do nothing
                } else {

                    if (getTargetColumn() != -1) {
                        //On se déplace
                        System.out.println("\n" +getImage() + " souhaite se déplacer en " + targetColumn);
                        seDeplacer(getTargetColumn());

                    } else {

                        if (getBroadcaster() != null && getBroadcaster() == getTargetLowerAgent()) {
                            //On vérifie si un agent s'est correctement placé et si cela nous arrange
                            System.out.println("\n" +getTargetLowerAgent().getImage() + " s'est déplacé : donc " + getImage() + " set sa target column to " + Main.board.getColumn(getBroadcaster()));
                            setTargetColumn(Main.board.getColumn(getBroadcaster()));
                        }
                    }
                }
            }
            sleep();
        }
    }

    private void moves() {
        synchronized (Main.board) {

            for(int i = 0; i < Main.board.NB_COLUMN; i++) {
                if(i != getForbiddenColumn() && i != Main.board.getColumn(this)) {
                    seDeplacer(i);
                    break;
                }
            }
        }
    }

    /***
     * Moves an agent to the xth column of the board
     * The agent only moves if there is nobody above him
     * @param x
     */
    private void seDeplacer(int x) {

        synchronized (Main.board) {

            if (getHigherAgent() == null) {
                //on se déplace
                System.out.println("Personne n'est au dessus de " + getImage() + " donc il bouge");
                Main.board.moveAgentFromTo(this, x);

                setMustMove(false);
                setTargetColumn(-1);

                if (isAtTargetPosition()
                        && (getLowerAgent() == null || getLowerAgent().isAtTargetPosition())) {
                    setBroadcaster(null);
                    Main.board.broadcast(this);
                }

            } else {
                //on demande à l'agent du dessus de se pousser
                pousser();
            }
        }
        sleep();
    }

    /***
     * Push the agent above the current agent
     */
    private void pousser() {
        getHigherAgent().setMustMove(true);

        if(getTargetColumn() == -1){
            //On se déplace pour quelqu'un d'autre qui nous interdit d'aller en forbiddenColumn
            ((AgentCognitiveBrodcast) getHigherAgent()).setForbiddenColumn(getForbiddenColumn());
        } else {
            //On lui demande de se pousser pour notre propre interet
            System.out.println(getImage() + " interdit à " + getHigherAgent().getImage() + " d'aller en " + getTargetColumn());
            ((AgentCognitiveBrodcast) getHigherAgent()).setForbiddenColumn(getTargetColumn());
        }
    }
}
