package com.sma;

import com.sma.Agents.Agent;
import com.sma.Agents.AgentCognitiveBrodcast;
import com.sma.Agents.AgentCognitiveIncreasing;
import com.sma.Agents.AgentReactive;

import java.util.Scanner;

public class Main {

    public static Board board;
    public static Agent a, b, c, d, e, f, g, h, i, j , k, l, m, n, o, p, q, r, s, t, u, v, w , x, y;

    public static void main(String[] args) {

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
                e = new AgentReactive(d, 'E');
                f = new AgentReactive(e, 'F');
                g = new AgentReactive(f, 'G');
                h = new AgentReactive(g, 'H');
                configureAndRun();
                break;

            case "2":
                a = new AgentCognitiveBrodcast(null, 'A');
                b = new AgentCognitiveBrodcast(a, 'B');
                c = new AgentCognitiveBrodcast(b, 'C');
                d = new AgentCognitiveBrodcast(c, 'D');
                e = new AgentCognitiveBrodcast(d, 'E');
                f = new AgentCognitiveBrodcast(e, 'F');

                configureAndRun();
                break;

            case "3":
                a = new AgentCognitiveIncreasing(null, 'A');
                b = new AgentCognitiveIncreasing(a, 'B');
                c = new AgentCognitiveIncreasing(b, 'C');
                d = new AgentCognitiveIncreasing(c, 'D');
                e = new AgentCognitiveIncreasing(d, 'E');
                f = new AgentCognitiveIncreasing(e, 'F');

                configureAndRun();
                break;

            default:
                System.out.println("Saisie non reconnue."
                        + "\nAssurez-vous d'avoir saisie un chiffre (1, 2 ou 3)\n");
        }
    }

    /***
     * Configure the initial board and run the game
     */
    private static void configureAndRun() {
        b.setLowerAgent(null);
        b.setHigherAgent(e);

        e.setLowerAgent(b);
        e.setHigherAgent(d);

        d.setLowerAgent(e);
        d.setHigherAgent(g);

        g.setLowerAgent(d);
        g.setHigherAgent(a);

        a.setLowerAgent(g);
        a.setHigherAgent(f);

        f.setLowerAgent(a);
        f.setHigherAgent(h);

        h.setLowerAgent(f);
        h.setHigherAgent(c);

        c.setLowerAgent(h);
        c.setHigherAgent(null);

        board = new Board(b, e, d, g, a, f, h, c);
        board.resolve();
    }
}
