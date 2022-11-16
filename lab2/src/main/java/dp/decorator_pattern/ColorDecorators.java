package dp.decorator_pattern;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

class RedColorDecorator extends GeneralDecorator{

    RedColorDecorator(AbstractShape s){
        this.s = s;
    }

    @Override
    public Shape draw() {
        Shape to_draw = s.draw();
        to_draw.setStroke(Color.RED);
        return to_draw;
    }
}

class GreenColorDecorator extends GeneralDecorator{

    GreenColorDecorator(AbstractShape s){
        this.s = s;
    }

    @Override
    public Shape draw() {
        Shape to_draw = s.draw();
        to_draw.setStroke(Color.GREEN);
        return to_draw;
    }
}

class BlueColorDecorator extends GeneralDecorator{

    BlueColorDecorator(AbstractShape s){
        this.s = s;
    }

    @Override
    public Shape draw() {
        Shape to_draw = s.draw();
        to_draw.setStroke(Color.BLUE);
        return to_draw;
    }
}