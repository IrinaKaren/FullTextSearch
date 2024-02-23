package model;

import dbaccess.PGSQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Produit {

    public static String extractInteger(String requete) throws Exception {
        String regex = "\\b\\d+\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(requete);
        if(matcher.find()){
            return Integer.parseInt(matcher.group())+"";
        }
        else{
            throw new Exception("pas de chiffre");
        }
    }
    //Produit
    private int id;
    private String produit;
    private Timestamp date_produit;
    private double prix;
    private String devise;
    private String description;
    
    //Marque
    private int idmarque;
    private String marque;
    
    //Categorie
    private int idcategorie;
    private String categorie;
    
    //Getter Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public Timestamp getDate_produit() {
        return date_produit;
    }

    public void setDate_produit(Timestamp date_produit) {
        this.date_produit = date_produit;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdmarque() {
        return idmarque;
    }

    public void setIdmarque(int idmarque) {
        this.idmarque = idmarque;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    //-------------- CATEGORIE -------------------------------------------------
    public static List<Produit> getcategories()throws Exception{
        List<Produit> produits = new ArrayList();
        Connection connection = PGSQLConnection.getConnection();
        String sql = "SELECT * FROM categorie ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Produit ss = new Produit();
            ss.setIdcategorie(resultSet.getInt("id"));  
            ss.setCategorie(resultSet.getString("nom"));
            produits.add(ss);
        }
        connection.close();
        return produits;
    }
    
    //-------------- MARQUE ----------------------------------------------------
        public static List<Produit> getmarques()throws Exception{
        List<Produit> produits = new ArrayList();
        Connection connection = PGSQLConnection.getConnection();
        String sql = "SELECT * FROM marque ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Produit ss = new Produit();
            ss.setIdcategorie(resultSet.getInt("idcategorie")); 
            ss.setIdmarque(resultSet.getInt("id"));  
            ss.setMarque(resultSet.getString("nom"));
            produits.add(ss);
        }
        connection.close();
        return produits;
    }
    
    //------------ PRODUIT -----------------------------------------------------
    public static List<Produit> getproduits(String requettes)throws Exception{
        List<Produit> produits = new ArrayList();
        Connection connection = PGSQLConnection.getConnection();
        String sql = requettes;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Produit ss = new Produit();
            ss.setId(resultSet.getInt("idproduit"));
            ss.setProduit(resultSet.getString("produit"));
            ss.setDate_produit(resultSet.getTimestamp("date_produit"));
            ss.setPrix(resultSet.getDouble("prix"));
            ss.setDevise(resultSet.getString("devise"));
            ss.setDescription(resultSet.getString("description"));
            ss.setIdmarque(resultSet.getInt("idmarque"));  
            ss.setMarque(resultSet.getString("marque"));
            ss.setIdcategorie(resultSet.getInt("idcategorie"));  
            ss.setCategorie(resultSet.getString("categorie"));
            produits.add(ss);
        }
        connection.close();
        return produits;
    }
    
    public static List<Produit> recherche(String requete) throws Exception {
        List<Produit> produits = new ArrayList<>();

        // Recherche avec marque et catégorie
        for (int i = 0; i < getcategories().size(); i++) {
            for (int j = 0; j < getmarques().size(); j++) {
                // Rapport qualité-prix
                if (requete.toLowerCase().contains("meilleur") && requete.toLowerCase().contains("prix") && requete.toLowerCase().contains("qualite") && requete.toLowerCase().contains(getcategories().get(i).getCategorie().toLowerCase()) && requete.toLowerCase().contains(getmarques().get(j).getMarque().toLowerCase())) {
                    // Qualité-prix le plus proche de 1
                    return getproduits("SELECT *, (qualite/prix) AS qualite_prix FROM v_produits WHERE categorie = '" + getcategories().get(i).getCategorie() + "' AND marque = '" + getmarques().get(j).getMarque() + "' ORDER BY (qualite/prix) DESC LIMIT 1");
                }
                if (requete.toLowerCase().contains("pire") && requete.toLowerCase().contains("prix") && requete.toLowerCase().contains("qualite") && requete.toLowerCase().contains(getcategories().get(i).getCategorie().toLowerCase()) && requete.toLowerCase().contains(getmarques().get(j).getMarque().toLowerCase())) {
                    // Qualité-prix le plus proche de 1
                    return getproduits("SELECT *, (qualite/prix) AS qualite_prix FROM v_produits WHERE categorie = '" + getcategories().get(i).getCategorie() + "' AND marque = '" + getmarques().get(j).getMarque() + "' ORDER BY (qualite/prix) ASC LIMIT 1");
                }

                // Prix 
                if (requete.toLowerCase().contains("meilleur") && requete.toLowerCase().contains("prix") && requete.toLowerCase().contains(getmarques().get(j).getMarque().toLowerCase()) && requete.toLowerCase().contains(getcategories().get(i).getCategorie().toLowerCase())) {
                    return getproduits("SELECT * FROM v_produits WHERE prix = (SELECT MIN(prix) FROM v_produits WHERE marque = '" + getmarques().get(j).getMarque() + "' AND categorie = '" + getcategories().get(i).getCategorie() + "') AND marque = '" + getmarques().get(j).getMarque() + "' AND categorie = '" + getcategories().get(i).getCategorie() + "'");
                }
                if (requete.toLowerCase().contains("pire") && requete.toLowerCase().contains("prix") && requete.toLowerCase().contains(getmarques().get(j).getMarque().toLowerCase()) && requete.toLowerCase().contains(getcategories().get(i).getCategorie().toLowerCase())) {
                    return getproduits("SELECT * FROM v_produits WHERE prix = (SELECT MAX(prix) FROM v_produits WHERE marque = '" + getmarques().get(j).getMarque() + "' AND categorie = '" + getcategories().get(i).getCategorie() + "')");
                }
                if (requete.toLowerCase().contains("prix") && requete.toLowerCase().contains(getmarques().get(j).getMarque().toLowerCase()) && requete.toLowerCase().contains(getcategories().get(i).getCategorie().toLowerCase())) {
                    return getproduits("SELECT * FROM v_produits WHERE marque = '" + getmarques().get(j).getMarque() + "' AND categorie = '" + getcategories().get(i).getCategorie() + "'");
                }
            }
        }

        // Recherche avec catégorie uniquement
        for (int i = 0; i < getcategories().size(); i++) {
            // Rapport qualité-prix avec catégorie
            if (requete.toLowerCase().contains("meilleur") && requete.toLowerCase().contains("prix") && requete.toLowerCase().contains("qualite") && requete.toLowerCase().contains(getcategories().get(i).getCategorie().toLowerCase())) {            
                // Qualité-prix le plus proche de 1
                return getproduits("SELECT *, (qualite/prix) AS qualite_prix FROM v_produits WHERE categorie = '" + getcategories().get(i).getCategorie() + "' ORDER BY (qualite/prix) DESC LIMIT 1");
            }
            if (requete.toLowerCase().contains("pire") && requete.toLowerCase().contains("prix") && requete.toLowerCase().contains("qualite") && requete.toLowerCase().contains(getcategories().get(i).getCategorie().toLowerCase())) {
                // Qualité-prix le plus proche de 1
                return getproduits("SELECT *, (qualite/prix) AS qualite_prix FROM v_produits WHERE categorie = '" + getcategories().get(i).getCategorie() + "' ORDER BY (qualite/prix) ASC LIMIT 1");
            }

            // Recherche avec catégorie
            if (requete.toLowerCase().contains("meilleur") && requete.toLowerCase().contains("prix") && requete.toLowerCase().contains(getcategories().get(i).getCategorie().toLowerCase())) {
                if (requete.toLowerCase().contains("top")) {
                    String integer = extractInteger(requete);
                    return getproduits("select * from v_produits WHERE categorie = '" + getcategories().get(i).getCategorie() + "' ORDER BY prix ASC LIMIT " + integer);
                }

                return getproduits("SELECT * FROM v_produits WHERE prix = (SELECT MIN(prix) FROM v_produits WHERE categorie = '" + getcategories().get(i).getCategorie() + "')");
            }
            if (requete.toLowerCase().contains("pire") && requete.toLowerCase().contains("prix") && requete.toLowerCase().contains(getcategories().get(i).getCategorie().toLowerCase())) {
                if (requete.toLowerCase().contains("top")) {
                    String integer = extractInteger(requete);
                    return getproduits("select * from v_produits WHERE categorie = '" + getcategories().get(i).getCategorie() + "' ORDER BY prix DESC LIMIT " + integer);
                }
                return getproduits("SELECT * FROM v_produits WHERE prix = (SELECT MAX(prix) FROM v_produits WHERE categorie = '" + getcategories().get(i).getCategorie() + "')");
            }

            // Recherche normale
            if (requete.toLowerCase().contains("prix") && requete.toLowerCase().contains(getcategories().get(i).getCategorie().toLowerCase())) {
                return getproduits("SELECT * FROM v_produits WHERE categorie = '" + getcategories().get(i).getCategorie() + "'");
            }
            if (requete.toLowerCase().contains("mobile") ) {
                return getproduits("SELECT * FROM v_produits WHERE categorie = 'Telephone'");
            }
        }

        // Recherche avec marque uniquement
        for (int i = 0; i < getmarques().size(); i++) {
            // Rapport qualité-prix avec marque
            if (requete.toLowerCase().contains("meilleur") && requete.toLowerCase().contains("prix") && requete.toLowerCase().contains("qualite") && requete.toLowerCase().contains(getmarques().get(i).getMarque().toLowerCase())) {
                // Qualité-prix le plus proche de 1
                return getproduits("SELECT *, (qualite/prix) AS qualite_prix FROM v_produits WHERE categorie = '" + getmarques().get(i).getMarque() + "' ORDER BY (qualite/prix) DESC LIMIT 1");
            }
            if (requete.toLowerCase().contains("pire") && requete.toLowerCase().contains("prix") && requete.toLowerCase().contains("qualite") && requete.toLowerCase().contains(getmarques().get(i).getMarque().toLowerCase())) {
                // Qualité-prix le plus proche de 1
                return getproduits("SELECT *, (qualite/prix) AS qualite_prix FROM v_produits WHERE categorie = '" + getmarques().get(i).getMarque() + "' ORDER BY (qualite/prix) ASC LIMIT 1");
            }
            
            // Recherche avec marque
            if (requete.toLowerCase().contains("meilleur") && requete.toLowerCase().contains("prix") && requete.toLowerCase().contains(getmarques().get(i).getMarque().toLowerCase())) {
                return getproduits("SELECT * FROM v_produits WHERE prix = (SELECT MIN(prix) FROM v_produits WHERE marque = '" + getmarques().get(i).getMarque() + "')");
            }
            if (requete.toLowerCase().contains("pire") && requete.toLowerCase().contains("prix") && requete.toLowerCase().contains(getmarques().get(i).getMarque().toLowerCase())) {
                return getproduits("SELECT * FROM v_produits WHERE prix = (SELECT MAX(prix) FROM v_produits WHERE marque = '" + getmarques().get(i).getMarque() + "')");
            }

            // Recherche normale
            if (requete.toLowerCase().contains("prix") && requete.toLowerCase().contains(getmarques().get(i).getMarque().toLowerCase())) {
                return getproduits("SELECT * FROM v_produits WHERE marque = '" + getmarques().get(i).getMarque() + "'");
            }
        }

        // Rapport qualité-prix
        if (requete.toLowerCase().contains("meilleur") && requete.toLowerCase().contains("prix") && requete.toLowerCase().contains("qualite")) {
            // Qualité-prix le plus proche de 1
            return getproduits("SELECT *, (qualite/prix) AS qualite_prix FROM v_produits ORDER BY (qualite/prix) DESC LIMIT 1");
        }
        if (requete.toLowerCase().contains("pire") && requete.toLowerCase().contains("prix") && requete.toLowerCase().contains("qualite")) {
            // Qualité-prix le plus proche de 1
            return getproduits("SELECT *, (qualite/prix) AS qualite_prix FROM v_produits ORDER BY (qualite/prix) ASC LIMIT 1");
        }

        // Recherche normale
        if (requete.toLowerCase().contains("meilleur") && requete.toLowerCase().contains("prix")) {
            return getproduits("SELECT * FROM v_produits WHERE prix = (SELECT MIN(prix) FROM v_produits)");
        }
        if (requete.toLowerCase().contains("pire") && requete.toLowerCase().contains("prix")) {
            return getproduits("SELECT * FROM v_produits WHERE prix = (SELECT MAX(prix) FROM v_produits)");
        }
        if (requete.toLowerCase().contains("prix")) {
            return getproduits("SELECT * FROM v_produits");
        }

        return produits;
    }
}
