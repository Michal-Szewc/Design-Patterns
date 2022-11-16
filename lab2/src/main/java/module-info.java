module dp.decorator_pattern {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens dp.decorator_pattern to javafx.fxml;
    exports dp.decorator_pattern;
}