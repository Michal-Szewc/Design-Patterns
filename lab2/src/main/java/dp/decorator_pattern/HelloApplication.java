package dp.decorator_pattern;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    Stage stage;

    Group root;
    Scene scene;

    RectangleShape rectangle = new RectangleShape();
    CircleShape circle = new CircleShape();
    TriangleeShape triangle = new TriangleeShape();

    AbstractShape chosenShape = circle;
    String chosenColor = "b";
    String chosenStyle = "s";
    void update(){
        AbstractShape s;
        if(chosenColor == "r")
            s = new RedColorDecorator(chosenShape);
        else if(chosenColor == "g")
            s = new GreenColorDecorator(chosenShape);
        else
            s = new BlueColorDecorator(chosenShape);

        if(chosenStyle == "s")
            s = new SolidStyleDecorator(s);
        else if (chosenStyle == "-")
            s = new DashedStyleDecorator(s);
        else
            s = new DottedStyleDecorator(s);

        root.getChildren().remove(root.getChildren().size() - 1);
        root.getChildren().add(s.draw());
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        root = new Group();

        initButtons();
        update();
        scene = new Scene(root, 900, 600);
        stage.setTitle("App");
        stage.setScene(scene);
        stage.show();
    }

    void initButtons(){
        Button circleButton = new Button("circle");
        circleButton.setOnAction(e -> {
            chosenShape = circle;
            update();
        });
        circleButton.setLayoutX(0);
        circleButton.setLayoutY(0);

        Button rectangleButton = new Button("rectangle");
        rectangleButton.setOnAction(e -> {
            chosenShape = rectangle;
            update();
        });
        rectangleButton.setLayoutX(43);
        rectangleButton.setLayoutY(0);

        Button triangleButton = new Button("triangle");
        triangleButton.setOnAction(e -> {
            chosenShape = triangle;
            update();
        });
        triangleButton.setLayoutX(108);
        triangleButton.setLayoutY(0);

        Button redButton = new Button("red");
        redButton.setOnAction(e -> {
            chosenColor = "r";
            update();
        });
        redButton.setLayoutX(0);
        redButton.setLayoutY(25);

        Button greenButton = new Button("green");
        greenButton.setOnAction(e -> {
            chosenColor = "g";
            update();
        });
        greenButton.setLayoutX(33);
        greenButton.setLayoutY(25);

        Button blueButton = new Button("blue");
        blueButton.setOnAction(e -> {
            chosenColor = "b";
            update();
        });
        blueButton.setLayoutX(80);
        blueButton.setLayoutY(25);

        Button solidButton = new Button("solid");
        solidButton.setOnAction(e -> {
            chosenStyle = "s";
            update();
        });
        solidButton.setLayoutX(0);
        solidButton.setLayoutY(50);

        Button dashedButton = new Button("dashed");
        dashedButton.setOnAction(e -> {
            chosenStyle = "-";
            update();
        });
        dashedButton.setLayoutX(40);
        dashedButton.setLayoutY(50);

        Button dottedButton = new Button("dotted");
        dottedButton.setOnAction(e -> {
            chosenStyle = ".";
            update();
        });
        dottedButton.setLayoutX(95);
        dottedButton.setLayoutY(50);

        root.getChildren().addAll(circleButton, rectangleButton, triangleButton,
                redButton, greenButton, blueButton,
                solidButton, dashedButton, dottedButton,
                chosenShape.draw());
    }

    public static void main(String[] args) {
        launch();
    }
}