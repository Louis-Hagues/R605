package com.r605.r605;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Map;
import java.util.LinkedHashMap;

public class AccueilController {

    @FXML private ComboBox<String> searchRegion;
    @FXML private ComboBox<String> searchDep;
    @FXML private ComboBox<String> searchVille;
    @FXML private ComboBox<String> searchFiltre;
    @FXML private Label errorLabel;

    private final Map<String, Map<String, Map<String, Map<String, Number[]>>>> data = Map.of(
            "Île-de-France", Map.of(
                    "Paris", Map.of(
                            "Paris", Map.of(
                                    "Densité de population", new Number[]{20500, 20700, 20900, 21000, 21100},
                                    "Âge moyen", new Number[]{41, 42, 43, 44, 45},
                                    "Pourcentage de diplômés du supérieur", new Number[]{65, 66, 67, 68, 69},
                                    "Taux de population active", new Number[]{75, 76, 77, 78, 79}
                            )
                    ),
                    "Hauts-de-Seine", Map.of(
                            "Boulogne-Billancourt", Map.of(
                                    "Densité de population", new Number[]{19000, 19200, 19400, 19600, 19800},
                                    "Âge moyen", new Number[]{39, 40, 41, 42, 43},
                                    "Pourcentage de diplômés du supérieur", new Number[]{60, 61, 62, 63, 64},
                                    "Taux de population active", new Number[]{78, 79, 80, 81, 82}
                            )
                    )
            ),
            "Grand Est", Map.of(
                    "Marne", Map.of(
                            "Reims", Map.of(
                                    "Densité de population", new Number[]{3800, 3850, 3900, 3950, 4000},
                                    "Âge moyen", new Number[]{38, 39, 40, 41, 42},
                                    "Pourcentage de diplômés du supérieur", new Number[]{35, 36, 37, 38, 39},
                                    "Taux de population active", new Number[]{70, 71, 72, 73, 74}
                            )
                    ),
                    "Bas-Rhin", Map.of(
                            "Strasbourg", Map.of(
                                    "Densité de population", new Number[]{3500, 3550, 3600, 3650, 3700},
                                    "Âge moyen", new Number[]{37, 38, 39, 40, 41},
                                    "Pourcentage de diplômés du supérieur", new Number[]{40, 41, 42, 43, 44},
                                    "Taux de population active", new Number[]{72, 73, 74, 75, 76}
                            )
                    )
            )
    );

    @FXML
    private void initialize() {
        searchRegion.getItems().addAll(data.keySet());
        searchDep.setDisable(true);
        searchVille.setDisable(true);
        searchFiltre.setDisable(true);

        searchRegion.setOnAction(event -> updateDepartements());
        searchDep.setOnAction(event -> updateVilles());
        searchVille.setOnAction(event -> updateFiltres());
    }

    private void updateDepartements() {
        String selectedRegion = searchRegion.getValue();
        if (selectedRegion != null) {
            searchDep.getItems().clear();
            searchDep.getItems().addAll(data.get(selectedRegion).keySet());
            searchDep.setDisable(false);
            searchVille.setDisable(true);
            searchFiltre.setDisable(true);
            searchVille.getItems().clear();
            searchFiltre.getItems().clear();
            errorLabel.setText("");
        }
    }

    private void updateVilles() {
        String selectedRegion = searchRegion.getValue();
        String selectedDep = searchDep.getValue();
        if (selectedRegion != null && selectedDep != null) {
            searchVille.getItems().clear();
            searchVille.getItems().addAll(data.get(selectedRegion).get(selectedDep).keySet());
            searchVille.setDisable(false);
            searchFiltre.setDisable(true);
            searchFiltre.getItems().clear();
            errorLabel.setText("");
        }
    }

    private void updateFiltres() {
        String selectedRegion = searchRegion.getValue();
        String selectedDep = searchDep.getValue();
        String selectedVille = searchVille.getValue();
        if (selectedRegion != null && selectedDep != null && selectedVille != null) {
            searchFiltre.getItems().clear();
            searchFiltre.getItems().addAll(data.get(selectedRegion).get(selectedDep).get(selectedVille).keySet());
            searchFiltre.setDisable(false);
            errorLabel.setText("");
        }
    }

    @FXML
    protected void onGenerer() {
        String region = searchRegion.getValue();
        String dep = searchDep.getValue();
        String ville = searchVille.getValue();
        String filtre = searchFiltre.getValue();

        if (region == null || dep == null || ville == null || filtre == null) {
            errorLabel.setText("Veuillez sélectionner tous les champs.");
            return;
        }

        errorLabel.setText("");

        Map<String, Double> prixAuM2 = getPrixAuM2(ville, filtre);

        String titreGraphique = "Prix au m² par " + filtre + " - " + ville + ", " + dep + " (" + region + ")";
        CreerGraphique creerGraphique = new CreerGraphique(titreGraphique, filtre);

        for (Map.Entry<String, Double> entry : prixAuM2.entrySet()) {
            creerGraphique.addData(entry.getKey(), entry.getValue());
        }

        Stage stage = new Stage();
        creerGraphique.show(stage);
    }

    private Map<String, Double> getPrixAuM2(String ville, String filtre) {
        Map<String, Double> prixAuM2 = new LinkedHashMap<>();

        switch (filtre) {
            case "Âge moyen":
                prixAuM2.put("15-19 ans", 10000.0);
                prixAuM2.put("20-24 ans", 10500.0);
                prixAuM2.put("25-29 ans", 11000.0);
                prixAuM2.put("30-34 ans", 11500.0);
                prixAuM2.put("35-39 ans", 12000.0);
                break;
            case "Densité de population":
                prixAuM2.put("0-5000 hab/km²", 8000.0);
                prixAuM2.put("5000-10000 hab/km²", 9000.0);
                prixAuM2.put("10000-15000 hab/km²", 10000.0);
                prixAuM2.put("15000-20000 hab/km²", 11000.0);
                prixAuM2.put(">20000 hab/km²", 12000.0);
                break;
            case "Pourcentage de diplômés du supérieur":
                prixAuM2.put("0-20%", 7000.0);
                prixAuM2.put("20-40%", 8000.0);
                prixAuM2.put("40-60%", 9000.0);
                prixAuM2.put("60-80%", 10000.0);
                prixAuM2.put("80-100%", 11000.0);
                break;
            case "Taux de population active":
                prixAuM2.put("0-20%", 7500.0);
                prixAuM2.put("20-40%", 8500.0);
                prixAuM2.put("40-60%", 9500.0);
                prixAuM2.put("60-80%", 10500.0);
                prixAuM2.put("80-100%", 11500.0);
                break;
            default:
                prixAuM2.put("Catégorie 1", 9000.0);
                prixAuM2.put("Catégorie 2", 9500.0);
                prixAuM2.put("Catégorie 3", 10000.0);
                prixAuM2.put("Catégorie 4", 10500.0);
                prixAuM2.put("Catégorie 5", 11000.0);
        }

        return prixAuM2;
    }
}