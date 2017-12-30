package com.sma;

import com.sma.Agents.Agent;
import com.sma.Agents.AgentCognitiveBrodcast;
import com.sma.Agents.AgentCognitiveIncreasing;
import com.sma.Agents.AgentReactive;

import java.util.Scanner;

public class Main {

    public static Board board;

    public static void main(String[] args) {

        Agent a, b, c, d;
        Scanner sc = new Scanner(System.in);

        System.out.println("-------------------------------------------\n"
                + "Le Monde des Blocs Multi-agents :\n"
                + "Réalisé par S. LHOPITAL & T. PRIEUR-DREVON - Hiver 2017 - 2018\n"
                + "-------------------------------------------");

        System.out.println("\nSelectionner la partie avec la laquelle vous souhaitez jouer : " +
                "\n- 1 : Partie 1 - Eco-Résolution " +
                "\n- 2 : Partie 2 - Stratégie de coordination par Brodcasting" +
                "\n- 3 : Partie 2 bis - Stratégie de coordination par Ordre Croissant");

        switch (sc.nextLine()) {

            case "1":
                a = new AgentReactive(null, 'A');
                b = new AgentReactive(a, 'B');
                c = new AgentReactive(b, 'C');
                d = new AgentReactive(c, 'D');

                configureAndRun(a, b, c, d);
                break;

            case "2":
                a = new AgentCognitiveBrodcast(null, 'A');
                b = new AgentCognitiveBrodcast(a, 'B');
                c = new AgentCognitiveBrodcast(b, 'C');
                d = new AgentCognitiveBrodcast(c, 'D');

                configureAndRun(a, b, c, d);
                break;

            case "3":
                a = new AgentCognitiveIncreasing(null, 'A');
                b = new AgentCognitiveIncreasing(a, 'B');
                c = new AgentCognitiveIncreasing(b, 'C');
                d = new AgentCognitiveIncreasing(c, 'D');

                configureAndRun(a, b, c, d);
                break;

            default:
                System.out.println("Saisie non reconnue."
                        + "\nAssurez-vous d'avoir saisie un chiffre (1, 2 ou 3)\n");
        }
    }

    /***
     * Configure the initial board and run the game
     * @param a
     * @param b
     * @param c
     * @param d
     */
    private static void configureAndRun(Agent a, Agent b, Agent c, Agent d) {
        b.setLowerAgent(null);
        b.setHigherAgent(d);

        d.setLowerAgent(b);
        d.setHigherAgent(a);

        a.setLowerAgent(d);
        a.setHigherAgent(c);

        c.setLowerAgent(a);
        c.setHigherAgent(null);

        board = new Board(b, d, a, c);
        board.resolve();
    }
}
