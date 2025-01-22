package com.r605.r605;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Charger le fichier FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fenetre-accueil.fxml"));

        // Créer une scène avec une taille par défaut
        Scene scene = new Scene(fxmlLoader.load(), 400, 400); // Dimensions ajustables

        // Configurer le titre de la fenêtre
        stage.setTitle("Graphiques - JavaFX");

        // Attacher la scène à la fenêtre principale
        stage.setScene(scene);

        // Afficher la fenêtre principale
        stage.show();

    }

    public static void main(String[] args) {
        launch(); // Lancer l'application JavaFX
    }
}
