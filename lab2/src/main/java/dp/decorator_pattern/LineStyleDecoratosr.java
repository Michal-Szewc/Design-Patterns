package dp.decorator_pattern;

import javafx.scene.shape.Shape;

class SolidStyleDecorator extends GeneralDecorator {
    public SolidStyleDecorator(AbstractShape s) {
        this.s = s;
    }

    @Override
    public Shape draw() {
        Shape to_draw = s.draw();
        to_draw.setStrokeDashOffset(0);
        return to_draw;
    }
}

class DashedStyleDecorator extends GeneralDecorator {
    public DashedStyleDecorator(AbstractShape s) {
        this.s = s;
    }

    @Override
    public Shape draw() {
        Shape to_draw = s.draw();
        to_draw.getStrokeDashArray().addAll(25.0, 25.0);
        return to_draw;
    }
}

class DottedStyleDecorator extends GeneralDecorator {
    public DottedStyleDecorator(AbstractShape s) {
        this.s = s;
    }

    @Override
    public Shape draw() {
        Shape to_draw = s.draw();
        to_draw.getStrokeDashArray().addAll(1.0, 15.0);
        return to_draw;
    }
}