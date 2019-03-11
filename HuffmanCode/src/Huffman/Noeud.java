/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Huffman;

/**
 *
 * @author soufiyan
 */
public class Noeud {
    Noeud gauche;
    Noeud droite;
    double valeur;
    String caractere;

    public Noeud(double valeur, String caractere) {
        this.valeur = valeur;
        this.caractere = caractere;
        gauche = null;
        droite = null;
    }

    public Noeud(Noeud gauche, Noeud droite) {
        this.valeur = gauche.valeur + droite.valeur;
        caractere = gauche.caractere + droite.caractere;
        if (gauche.valeur < droite.valeur) {
            this.droite = droite;
            this.gauche = gauche;
        } else {
            this.droite = gauche;
            this.gauche = droite;
        }
}
}
