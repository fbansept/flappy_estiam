package edu.fbansept;

import edu.fbansept.models.Decor;
import edu.fbansept.models.Flappy;
import edu.fbansept.models.Tuyau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Fenetre extends Canvas {

    protected Flappy flappy;
    protected Tuyau[] listeTuyaux = new Tuyau[10];
    protected List<Decor> listeDecors = new ArrayList<>();

    public static int LARGEUR = 500;
    public static int HAUTEUR = 500;


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

                dessin.setColor(Color.red);
                dessin.fillOval(
                        flappy.getX(),
                        flappy.getY() - flappy.getHauteur()/2,
                        flappy.getLargeur(),
                        flappy.getHauteur());

                for(int i = 0; i < listeTuyaux.length; i++) {
                    Tuyau tuyau = listeTuyaux[i];
                    tuyau.setX((int)(tuyau.getX() - tuyau.getVitesseX()));

                    dessin.setColor(Color.green);
                    dessin.fillRect(tuyau.getX(),tuyau.getY(),tuyau.getLargeur(), tuyau.getHauteur());

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
