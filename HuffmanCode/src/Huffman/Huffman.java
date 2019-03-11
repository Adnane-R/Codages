/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Huffman;


import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Soufiyan & Adnane
 */
public class Huffman {

    static PriorityQueue<Noeud> Noeuds = new PriorityQueue<>((o1, o2) -> (o1.valeur < o2.valeur) ? -1 : 1);
    static TreeMap<Character, String> codes = new TreeMap<>();
    static String texte = "";
    static String Codes = "";
    static String Decodes = "";
    static int Alphabet[] = new int[128];

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int decision = 1;
        while (decision != -1) {
            if (GestionDecision(scanner, decision)) continue;
            decision = consoleMenu(scanner);
        }
    }

    private static int consoleMenu(Scanner scanner) {
        int decision;
        System.out.println("\n---- Menu ----\n" +
                "-- [-1] Quitter \n" +
                "-- [1] Donner un nouveau texte \n" +
                "-- [2] Encoder un texte\n" +
                "-- [3] Decoder un texte");
        decision = Integer.parseInt(scanner.nextLine());
        return decision;
    }

    private static boolean GestionDecision(Scanner scanner, int decision) {
        switch (decision) {
            case 1:
                if (GestionTexte(scanner)) return true;
                break;
            case 2:
                if (GestionCodage(scanner)) return true;
                break;
            case 3:
                GestionDecodage(scanner);
                break;
            default:
                break;
        }
        return false;
    }

    private static void GestionDecodage(Scanner scanner) {
        System.out.println("Donnez le texte a decoder :");
        Codes = scanner.nextLine();
        System.out.println("Le texte saisi est : " + Codes);
        Decoder();
    }

    private static boolean GestionCodage(Scanner scanner) {
        System.out.println("Veuillez saisir le texte à encoder : ");
        texte = scanner.nextLine();
        System.out.println("Le texte saisi est : " + texte);
        Codage_Texte();
        return false;
    }

    private static boolean GestionTexte(Scanner scanner) {
        int TextLength = texte.length();
        System.out.println("Veuillez donner le texte : ");
        texte = scanner.nextLine();
        if (TextLength != 0) {
            System.out.println("Erreur : Aucune entrée valide ! ");
            texte = "";
            return true;
        }
            Alphabet = new int[128];
            Noeuds.clear();
            codes.clear();
            Codes = "";
            Decodes = "";
            System.out.println("Text: " + texte);
            CalculInterval(Noeuds, true);
            Construire_Arbre(Noeuds);
            GenererCode(Noeuds.peek(), "");

            AfficherCodes();
            System.out.println("-- Codage/Decodage --");
            Codage_Texte();
            Decoder();
            return false;
    }

    private static void Decoder() {   // Il ne peut decoder que le dernier message codés, car c'est les intervalles de ce messages qui sont enregistrés en mémoire !
        Decodes = "";
        Noeud node = Noeuds.peek();
        for (int i = 0; i < Codes.length(); ) {
            Noeud tmpNode = node;
            while (tmpNode.gauche != null && tmpNode.droite != null && i < Codes.length()) {
                if (Codes.charAt(i) == '1')
                    tmpNode = tmpNode.droite;
                else tmpNode = tmpNode.gauche;
                i++;
            }

            if (tmpNode.caractere.length() == 1)
                Decodes += tmpNode.caractere;
            else
                System.out.println("Erreur : Entrée invalide");
        }
        System.out.println("Decoded Text: " + Decodes);
    }

    private static void Codage_Texte() {
        Codes = "";
        for (int i = 0; i < texte.length(); i++)
            Codes += codes.get(texte.charAt(i));
        System.out.println("Texte codé : " + Codes);
    }

    private static void Construire_Arbre(PriorityQueue<Noeud> vector) {
        while (vector.size() > 1)
            vector.add(new Noeud(vector.poll(), vector.poll()));
    }

    private static void AfficherCodes() {
        System.out.println("--- Affichage de Codes ---");
        codes.forEach((k, v) -> System.out.println("'" + k + "' : " + v));
    }

    private static void CalculInterval(PriorityQueue<Noeud> vector, boolean printIntervals) {
        if (printIntervals) System.out.println("-- Probabilités ( pour la gestion d'intervallles ) --");

        for (int i = 0; i < texte.length(); i++)
            Alphabet[texte.charAt(i)]++;

        for (int i = 0; i < Alphabet.length; i++)
            if (Alphabet[i] > 0) {
                vector.add(new Noeud(Alphabet[i] / (texte.length() * 1.0), ((char) i) + ""));
                if (printIntervals)
                    System.out.println("'" + ((char) i) + "' : " + Alphabet[i] / (texte.length() * 1.0));
            }
    }

    private static void GenererCode(Noeud node, String s) {
        if (node != null) {
            if (node.droite != null)
                GenererCode(node.droite, s + "1");

            if (node.gauche != null)
                GenererCode(node.gauche, s + "0");

            if (node.gauche == null && node.droite == null)
                codes.put(node.caractere.charAt(0), s);
        }
    }
}