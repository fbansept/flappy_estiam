package edu.fbansept;

import edu.fbansept.models.Decor;
import edu.fbansept.models.Flappy;
import edu.fbansept.models.Tuyau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Fenetre extends Canvas {

    protected Flappy flappy;
    protected Tuyau[] listeTuyaux = new Tuyau[10];
    protected List<Decor> listeDecors = new ArrayList<>();

    public static int LARGEUR = 500;
    public static int HAUTEUR = 500;

    protected boolean spacePressed = false;


    public Fenetre() {
        setSize(LARGEUR,HAUTEUR);

        JFrame frame = new JFrame("Super jeu !");
        JPanel panneau = (JPanel) frame.getContentPane();
        panneau.setSize(LARGEUR,HAUTEUR);
        panneau.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
        setBounds(0, 0, LARGEUR , HAUTEUR);
        panneau.add(this);

        frame.setSize(LARGEUR,HAUTEUR);
        frame.pack();
        frame.setResizable(false);
        frame.setIgnoreRepaint(true);
        frame.requestFocus();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {

                    if(!spacePressed) {
                        flappy.setVitesseY(-3);
                    }
                    spacePressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    spacePressed = false;
                }
            }
        });

        createBufferStrategy(2);
        this.setFocusable(false);

        frame.setVisible(true);

        demarrer();
    }

    public void demarrer() {

        flappy = new Flappy();

        for(int i = 0; i < listeTuyaux.length; i++) {

            int hauteurTuyau = 300;

            listeTuyaux[i] = new Tuyau(
                    LARGEUR + 200 * i,
                    HAUTEUR - hauteurTuyau,
                    80,
                    hauteurTuyau,
                    2);
        }

        try {
            long frame = 0;

            while(true) {
                frame ++;

                Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

                dessin.setColor(Color.white);
                dessin.fillRect(0,0,LARGEUR,HAUTEUR);

                flappy.deplacement();
                flappy.dessine(dessin);

                for(int i = 0; i < listeTuyaux.length; i++) {
                    Tuyau tuyau = listeTuyaux[i];
                    tuyau.deplacement();
                    tuyau.dessine(dessin);
                    if(flappy.collision(tuyau)) {
                        System.out.println("collision");
                    }
                }

                dessin.dispose();
                getBufferStrategy().show();
                Thread.sleep(17);//environ 1000 / 60
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Fenetre();
    }

}
