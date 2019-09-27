package com.example.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Child class of surface view for displaying a Face object
 *
 * @author Andres Giesemann
 */
public class FaceView extends SurfaceView {

    protected Face face;

    /**
     * Constructor of FaceView. Prepares for drawing and initializes face with new Face object
     * @param context
     * @param attrs
     */
    public FaceView (Context context, AttributeSet attrs) {

        super(context,attrs);
        setWillNotDraw(false);
        face = new Face();

    }

    public void onDraw(Canvas canvas) {

        face.draw(canvas);

    }

    /**
     * Allows the controller to have a reference to the same Face as the View
     * @return Face object of this FaceView
     */
    public Face getFace() {

        return face;

    }

}
