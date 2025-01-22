package com.r605.r605;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AccueilController {
    private final CreerGraphique creerGraphique;

    // Constructeur par défaut
    public AccueilController() {
        // Initialisation du graphique avec un titre et des labels pour les axes
        this.creerGraphique = new CreerGraphique("Graphique des Produits", "Produits", "Quantités");

        // Ajouter des séries de données
        this.creerGraphique.addSeries("2023", new String[]{"Produit A", "Produit B", "Produit C"}, new Number[]{150, 200, 170});
        this.creerGraphique.addSeries("2024", new String[]{"Produit A", "Produit B", "Produit C"}, new Number[]{180, 220, 190});
    }

    // Méthode appelée via le bouton (ou un autre événement lié au FXML)
    @FXML
    protected void onGenerer() {
        // Créer un nouveau stage pour chaque clic sur le bouton
        Stage stage = new Stage();

        // Créer une nouvelle instance de CreerGraphique pour chaque stage
        CreerGraphique creerGraphique = new CreerGraphique("Graphique des Produits", "Produits", "Quantités");

        // Ajouter des séries de données à ce graphique
        creerGraphique.addSeries("2023", new String[]{"Produit A", "Produit B", "Produit C"}, new Number[]{150, 200, 170});
        creerGraphique.addSeries("2024", new String[]{"Produit A", "Produit B", "Produit C"}, new Number[]{180, 220, 190});

        // Afficher le graphique dans la nouvelle fenêtre (stage)
        creerGraphique.show(stage);
    }
}
