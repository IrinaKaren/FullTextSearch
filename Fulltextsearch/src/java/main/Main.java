package main;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Produit;

public class Main {
    public static void main(String[] args) {
        try {
            List<Produit> produits = Produit.recherche("liste des prix");
            Produit.getmarques();
            for (int i = 0; i < produits.size(); i++) {
                System.out.println("valiny "+produits.get(i).getCategorie()+" "+produits.get(i).getProduit());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
