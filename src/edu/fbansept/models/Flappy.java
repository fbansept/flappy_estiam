package edu.fbansept.models;

import edu.fbansept.Fenetre;

import java.awt.*;

public class Flappy extends MovingSprite{

    public Flappy() {
        super(50, Fenetre.HAUTEUR / 2, 30, 30, 0, 0);
    }

    @Override
    public void dessine(Graphics2D dessin) {
        dessin.setColor(Color.red);
        dessin.fillOval( x,y - hauteur/2,largeur, hauteur);
    }

    @Override
    public void deplacement() {
        vitesseY += 0.08f;
        y += vitesseY;
    }
}
