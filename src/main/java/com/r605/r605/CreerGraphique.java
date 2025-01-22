package com.r605.r605;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class CreerGraphique {

    private final BarChart<String, Number> barChart;

    // Constructeur pour créer le BarChart avec un titre
    public CreerGraphique(String chartTitle, String xAxisLabel, String yAxisLabel) {
        // Créer les axes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xAxisLabel);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yAxisLabel);

        // Initialiser le BarChart
        this.barChart = new BarChart<>(xAxis, yAxis);
        this.barChart.setTitle(chartTitle);
    }

    // Méthode pour ajouter une série de données
    public void addSeries(String seriesName, String[] categories, Number[] values) {
        if (categories.length != values.length) {
            throw new IllegalArgumentException("Les catégories et les valeurs doivent avoir la même longueur.");
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesName);

        for (int i = 0; i < categories.length; i++) {
            series.getData().add(new XYChart.Data<>(categories[i], values[i]));
        }

        this.barChart.getData().add(series);
    }

    // Méthode pour afficher le BarChart dans une fenêtre JavaFX
    public void show(Stage stage) {
        Scene scene = new Scene(this.barChart, 800, 600);
        stage.setTitle(this.barChart.getTitle());
        stage.setScene(scene);
        stage.show();
    }

    // Méthode pour récupérer le BarChart (utile pour intégrer dans une autre interface)
    public BarChart<String, Number> getBarChart() {
        return this.barChart;
    }
}