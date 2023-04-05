package edu.fbansept.models;

import edu.fbansept.Fenetre;

public class Flappy extends Sprite{

    public Flappy() {
        super(50, Fenetre.HAUTEUR / 2, 30, 30);
        this.vitesseY = 0;
    }

    public Flappy(int x, int y, int largeur, int hauteur, float vitesseY) {
        super(x, y, largeur, hauteur);
        this.vitesseY = vitesseY;
    }

    protected float vitesseY;
}
