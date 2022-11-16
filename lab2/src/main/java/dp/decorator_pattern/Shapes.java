package dp.decorator_pattern;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

class RectangleShape extends AbstractShape {
    @Override
    public Shape draw() {
        Rectangle r = new Rectangle(200,250,400,200);
        r.setFill(Color.TRANSPARENT);
        r.setStroke(Color.BLACK);
        r.setStrokeWidth(5);
        return r;
    }
}

class CircleShape extends AbstractShape {

    @Override
    public Shape draw() {
        Circle c = new Circle(400,350,150);
        c.setFill(Color.TRANSPARENT);
        c.setStroke(Color.BLACK);
        c.setStrokeWidth(5);
        return c;
    }
}

class TriangleeShape extends AbstractShape {

    @Override
    public Shape draw() {
        Path t = new Path(new MoveTo(450, 200),
                new LineTo(250, 500),
                new LineTo(650, 500),
                new ClosePath());
        t.setFill(Color.TRANSPARENT);
        t.setStroke(Color.BLACK);
        t.setStrokeWidth(5);
        return t;
    }
}
