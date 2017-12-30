package com.sma;

import com.sma.Agents.Agent;
import com.sma.Agents.AgentCognitive;
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
                "\n- 2 : Partie 2 - Stratégie de coordination par Brodcasting");

        switch (sc.nextLine()) {

            case "1":
                a = new AgentReactive(null, 'A');
                b = new AgentReactive(a, 'B');
                c = new AgentReactive(b, 'C');
                d = new AgentReactive(c, 'D');

                configureAndRun(a, b, c, d);
                break;

            case "2":

                a = new AgentCognitive(null, 'A');
                b = new AgentCognitive(a, 'B');
                c = new AgentCognitive(b, 'C');
                d = new AgentCognitive(c, 'D');

                configureAndRun(a, b, c, d);
                break;

            default:
                System.out.println("Saisie non reconnue."
                        + "\nAssurez-vous d'avoir saisie un chiffre (1 ou 2)\n");
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
