package com.r605.r605;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class CreerGraphique {

    private final BarChart<String, Number> barChart;
    private final String titre;
    private final XYChart.Series<String, Number> seriesPrix;

    public CreerGraphique(String titre, String critere) {
        this.titre = titre;

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(critere);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Prix au m²");

        this.barChart = new BarChart<>(xAxis, yAxis);
        this.barChart.setTitle(titre);

        this.seriesPrix = new XYChart.Series<>();
        this.seriesPrix.setName("Prix au m²");

        this.barChart.getData().add(seriesPrix);
        this.barChart.setLegendVisible(false);
    }

    public void addData(String categorie, Number prixAuM2) {
        seriesPrix.getData().add(new XYChart.Data<>(categorie, prixAuM2));
    }

    public void show(Stage stage) {
        VBox vbox = new VBox(10);
        Label sourceLabel = new Label("Source : data.gouv.fr (Prix au m²)");
        vbox.getChildren().addAll(this.barChart, sourceLabel);

        Scene scene = new Scene(vbox, 800, 600);
        stage.setTitle(this.titre);
        stage.setScene(scene);
        stage.show();
    }
}