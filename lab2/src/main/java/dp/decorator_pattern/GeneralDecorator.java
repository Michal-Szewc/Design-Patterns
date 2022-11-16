package dp.decorator_pattern;

import javafx.scene.shape.Shape;

public abstract class GeneralDecorator extends AbstractShape{
    protected AbstractShape s;
    public abstract Shape draw();
}
