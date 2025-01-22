module com.r.r {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.r605.r605 to javafx.fxml;
    exports com.r605.r605;
}