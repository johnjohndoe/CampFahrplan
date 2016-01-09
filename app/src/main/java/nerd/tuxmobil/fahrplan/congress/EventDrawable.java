package nerd.tuxmobil.fahrplan.congress;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;

public class EventDrawable extends LayerDrawable {


    public EventDrawable(int backgroundColor, float cornerRadius) {
        super(initialize(backgroundColor, cornerRadius));
    }

    public EventDrawable(
            int backgroundColor,
            int strokeColor,
            float strokeWidth,
            float cornerRadius) {
        super(initialize(backgroundColor, strokeColor, strokeWidth, cornerRadius));
    }

    private static Drawable[] initialize(int backgroundColor, float cornerRadius) {

        final float[] RADII = {
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius
        };
        Drawable[] layers = new Drawable[1];
        ShapeDrawable backgroundShapeDrawable = getBackgroundShapeDrawable(backgroundColor, RADII);
        layers[0] = backgroundShapeDrawable;
        return layers;
    }

    private static Drawable[] initialize(
            int backgroundColor,
            int strokeColor,
            float strokeWidth,
            float cornerRadius) {

        Drawable[] layers = new Drawable[2];

        final float[] RADII = {
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius
        };

        // Background
        ShapeDrawable backgroundShapeDrawable = getBackgroundShapeDrawable(backgroundColor, RADII);
        layers[0] = backgroundShapeDrawable;

        final RectF INSET = new RectF(
                strokeWidth,
                strokeWidth,
                strokeWidth,
                strokeWidth);

        // Stroke aka. border
        Shape strokeShape = new RoundRectShape(RADII, INSET, RADII);
        ShapeDrawable strokeShapeDrawable = new ShapeDrawable(strokeShape);
        strokeShapeDrawable.getPaint().setColor(strokeColor);
        layers[1] = strokeShapeDrawable;

        return layers;
    }

    private static ShapeDrawable getBackgroundShapeDrawable(int backgroundColor, float[] RADII) {
        Shape backgroundShape = new RoundRectShape(RADII, null, null);
        ShapeDrawable backgroundShapeDrawable = new ShapeDrawable(backgroundShape);
        backgroundShapeDrawable.getPaint().setColor(backgroundColor);
        return backgroundShapeDrawable;
    }

}
