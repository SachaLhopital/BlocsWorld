package com.sma;

import com.sma.Agents.Agent;
import com.sma.Agents.AgentCognitiveBrodcast;
import com.sma.Agents.AgentCognitiveIncreasing;
import com.sma.Agents.AgentReactive;

import java.util.Scanner;

public class Main {

    public static Board board;
    public static Scanner sc;
    public static Agent a, b, c, d, e, f, g, h, i, j , k, l, m, n, o, p, q, r, s, t, u, v, w , x, y, z;

    public static void main(String[] args) {

        sc = new Scanner(System.in);

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
                i = new AgentReactive(h, 'I');
                j = new AgentReactive(i, 'J');
                k = new AgentReactive(j, 'K');
                l = new AgentReactive(k, 'L');
                m = new AgentReactive(l, 'M');
                n = new AgentReactive(m, 'N');
                o = new AgentReactive(n, 'O');
                p = new AgentReactive(o, 'P');

                q = new AgentReactive(p, 'Q');
                r = new AgentReactive(q, 'R');
                s = new AgentReactive(r, 'S');
                t = new AgentReactive(s, 'T');
                u = new AgentReactive(t, 'U');
                v = new AgentReactive(u, 'V');
                w = new AgentReactive(v, 'W');
                x = new AgentReactive(w, 'X');
                y = new AgentReactive(x, 'Y');
                z = new AgentReactive(y, 'Z');

                configureAndRun();
                break;

            case "2":
                a = new AgentCognitiveBrodcast(null, 'A');
                b = new AgentCognitiveBrodcast(a, 'B');
                c = new AgentCognitiveBrodcast(b, 'C');
                d = new AgentCognitiveBrodcast(c, 'D');
                e = new AgentCognitiveBrodcast(d, 'E');
                f = new AgentCognitiveBrodcast(e, 'F');
                g = new AgentCognitiveBrodcast(f, 'G');
                h = new AgentCognitiveBrodcast(g, 'H');
                i = new AgentCognitiveBrodcast(h, 'I');
                j = new AgentCognitiveBrodcast(i, 'J');
                k = new AgentCognitiveBrodcast(j, 'K');
                l = new AgentCognitiveBrodcast(k, 'L');
                m = new AgentCognitiveBrodcast(l, 'M');
                n = new AgentCognitiveBrodcast(m, 'N');
                o = new AgentCognitiveBrodcast(n, 'O');
                p = new AgentCognitiveBrodcast(o, 'P');

                q = new AgentCognitiveBrodcast(p, 'Q');
                r = new AgentCognitiveBrodcast(q, 'R');
                s = new AgentCognitiveBrodcast(r, 'S');
                t = new AgentCognitiveBrodcast(s, 'T');
                u = new AgentCognitiveBrodcast(t, 'U');
                v = new AgentCognitiveBrodcast(u, 'V');
                w = new AgentCognitiveBrodcast(v, 'W');
                x = new AgentCognitiveBrodcast(w, 'X');
                y = new AgentCognitiveBrodcast(x, 'Y');
                z = new AgentCognitiveBrodcast(y, 'Z');

                configureAndRun();
                break;

            case "3":
                a = new AgentCognitiveIncreasing(null, 'A');
                b = new AgentCognitiveIncreasing(a, 'B');
                c = new AgentCognitiveIncreasing(b, 'C');
                d = new AgentCognitiveIncreasing(c, 'D');
                e = new AgentCognitiveIncreasing(d, 'E');
                f = new AgentCognitiveIncreasing(e, 'F');
                g = new AgentCognitiveIncreasing(f, 'G');
                h = new AgentCognitiveIncreasing(g, 'H');
                i = new AgentCognitiveIncreasing(h, 'I');
                j = new AgentCognitiveIncreasing(i, 'J');
                k = new AgentCognitiveIncreasing(j, 'K');
                l = new AgentCognitiveIncreasing(k, 'L');
                m = new AgentCognitiveIncreasing(l, 'M');
                n = new AgentCognitiveIncreasing(m, 'N');
                o = new AgentCognitiveIncreasing(n, 'O');
                p = new AgentCognitiveIncreasing(o, 'P');

                q = new AgentCognitiveIncreasing(p, 'Q');
                r = new AgentCognitiveIncreasing(q, 'R');
                s = new AgentCognitiveIncreasing(r, 'S');
                t = new AgentCognitiveIncreasing(s, 'T');
                u = new AgentCognitiveIncreasing(t, 'U');
                v = new AgentCognitiveIncreasing(u, 'V');
                w = new AgentCognitiveIncreasing(v, 'W');
                x = new AgentCognitiveIncreasing(w, 'X');
                y = new AgentCognitiveIncreasing(x, 'Y');
                z = new AgentCognitiveIncreasing(y, 'Z');

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

        sc = new Scanner(System.in);
        System.out.println("Pour combien d'agents souhaitez-vous lancer la simulation ? (4, 8, 16 ou 26 ? )");

        switch (sc.nextLine()) {

            case "4":
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
                break;

            case "8":
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
                break;

            case "16":
                b.setLowerAgent(null);
                b.setHigherAgent(e);

                e.setLowerAgent(b);
                e.setHigherAgent(l);

                l.setLowerAgent(e);
                l.setHigherAgent(h);

                h.setLowerAgent(l);
                h.setHigherAgent(d);

                d.setLowerAgent(h);
                d.setHigherAgent(m);

                m.setLowerAgent(d);
                m.setHigherAgent(g);

                g.setLowerAgent(m);
                g.setHigherAgent(i);

                i.setLowerAgent(g);
                i.setHigherAgent(n);

                n.setLowerAgent(i);
                n.setHigherAgent(a);

                a.setLowerAgent(n);
                a.setHigherAgent(f);

                f.setLowerAgent(a);
                f.setHigherAgent(o);

                o.setLowerAgent(f);
                o.setHigherAgent(j);

                j.setLowerAgent(o);
                j.setHigherAgent(p);

                p.setLowerAgent(j);
                p.setHigherAgent(c);

                c.setLowerAgent(p);
                c.setHigherAgent(k);

                k.setLowerAgent(c);
                k.setHigherAgent(null);

                board = new Board(b,e,l,h,d,m,g,i,n,a,f,o,j,p,c,k);
                board.resolve();
                break;

            case "26":
                b.setLowerAgent(null);
                b.setHigherAgent(e);

                e.setLowerAgent(b);
                e.setHigherAgent(y);

                y.setLowerAgent(e);
                y.setHigherAgent(q);

                q.setLowerAgent(y);
                q.setHigherAgent(l);

                l.setLowerAgent(q);
                l.setHigherAgent(z);

                z.setLowerAgent(l);
                z.setHigherAgent(h);

                h.setLowerAgent(z);
                h.setHigherAgent(r);

                r.setLowerAgent(h);
                r.setHigherAgent(d);

                d.setLowerAgent(r);
                d.setHigherAgent(m);

                m.setLowerAgent(d);
                m.setHigherAgent(s);

                s.setLowerAgent(m);
                s.setHigherAgent(g);

                g.setLowerAgent(s);
                g.setHigherAgent(i);

                i.setLowerAgent(g);
                i.setHigherAgent(t);

                t.setLowerAgent(i);
                t.setHigherAgent(n);

                n.setLowerAgent(i);
                n.setHigherAgent(a);

                a.setLowerAgent(n);
                a.setHigherAgent(u);

                u.setLowerAgent(a);
                u.setHigherAgent(f);

                f.setLowerAgent(u);
                f.setHigherAgent(o);

                o.setLowerAgent(f);
                o.setHigherAgent(v);

                v.setLowerAgent(o);
                v.setHigherAgent(j);

                j.setLowerAgent(v);
                j.setHigherAgent(p);

                p.setLowerAgent(j);
                p.setHigherAgent(w);

                w.setLowerAgent(p);
                w.setHigherAgent(c);

                c.setLowerAgent(w);
                c.setHigherAgent(k);

                k.setLowerAgent(c);
                k.setHigherAgent(x);

                x.setLowerAgent(k);
                x.setHigherAgent(null);

                board = new Board(b,e,y,q,l,z,h,r,d,m,s,g,i,t,n,a,u,f,o,v,j,p,w,c,k,x);
                board.resolve();
                break;

            default:
                System.out.println("Saisie non reconnue."
                        + "\nAssurez-vous d'avoir saisie un chiffre (4, 8, 16 ou 26 uniquement)\n");
        }
    }
}
