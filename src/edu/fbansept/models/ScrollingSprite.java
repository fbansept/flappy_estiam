package edu.fbansept.models;

public class ScrollingSprite extends Sprite{

    protected float vitesseX;

    public ScrollingSprite(int x, int y, int largeur, int hauteur, float vitesseX) {
        super(x, y, largeur, hauteur);
        this.vitesseX = vitesseX;
    }

    public float getVitesseX() {
        return vitesseX;
    }

    public void setVitesseX(float vitesseX) {
        this.vitesseX = vitesseX;
    }
}
