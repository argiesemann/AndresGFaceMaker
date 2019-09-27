package com.example.facemaker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Defines a representation of a face to be drawn and customized by the user
 * @author Andres Giesemann
 */
public class Face {

    protected int skinColor;
    protected int eyeColor;
    protected int hairColor;
    protected int hairStyle;

    protected Paint skinPaint = new Paint();
    protected Paint eyePaint = new Paint();
    protected Paint hairPaint = new Paint();
    protected Paint eyeWhitePaint = new Paint();
    protected Paint pupilPaint = new Paint();
    protected Paint nosePaint = new Paint();

    protected Random gen = new Random();

    /**
     * Constructs a face with random initial values
     */
    public Face() {

        randomize();
        pupilPaint.setColor(Color.BLACK);
        eyeWhitePaint.setColor(Color.WHITE);
        nosePaint.setColor(Color.DKGRAY);

    }

    /**
     * Handles drawing of the face. Gets called in the FaceView onDraw method
     * @param canvas the same canvas as the FaceView onDraw's method
     */
    public void draw(Canvas canvas) {

        //draws face with skin color upon which eyes and hair will be drawn
        canvas.drawOval(canvas.getWidth()/2 - 200,canvas.getHeight()/2 - 300,canvas.getWidth()/2 + 200,canvas.getHeight()/2 + 300,skinPaint);

        //draw mouth
        canvas.drawOval(canvas.getWidth()/2 - 100, canvas.getHeight()/2 + 100, canvas.getWidth()/2 + 100, canvas.getHeight()/2 + 175, pupilPaint);

        //draw nose
        canvas.drawOval(canvas.getWidth()/2 - 50, canvas.getHeight()/2, canvas.getWidth()/2 + 50, canvas.getHeight()/2 + 25, nosePaint);
        canvas.drawOval(canvas.getWidth()/2 - 15, canvas.getHeight()/2 - 100, canvas.getWidth()/2 + 15, canvas.getHeight()/2 + 25, nosePaint);

        drawEyes(canvas);
        drawHair(canvas);

    }

    /**
     * draws eyes on top of the face
     * @param canvas again the same canvas from the FaceView
     */
    public void drawEyes(Canvas canvas) {

        //draw whites of eyes
        canvas.drawCircle((float)(canvas.getWidth()/2 - 100), (float)(canvas.getHeight()/2 - 100), 50f, eyeWhitePaint);
        canvas.drawCircle((float)(canvas.getWidth()/2 + 100), (float)(canvas.getHeight()/2 - 100), 50f, eyeWhitePaint);

        //draw irises with user controlled (or randomly selected) eye color
        canvas.drawCircle((float)(canvas.getWidth()/2 - 100),(float)(canvas.getHeight()/2 - 100), 30f,eyePaint);
        canvas.drawCircle((float)(canvas.getWidth()/2 + 100),(float)(canvas.getHeight()/2 - 100), 30f,eyePaint);

        //draw pupils
        canvas.drawCircle((float)(canvas.getWidth()/2 - 100),(float)(canvas.getHeight()/2 - 100), 15f,pupilPaint);
        canvas.drawCircle((float)(canvas.getWidth()/2 + 100),(float)(canvas.getHeight()/2 - 100), 15f,pupilPaint);

    }

    /**
     * draws hair onto the face based on the user's hairstyle choice
     * @param canvas
     */
    public void drawHair(Canvas canvas) {

        switch(hairStyle) {

            //beret
            case 0:
                canvas.drawOval(canvas.getWidth()/2 - 200, canvas.getHeight()/2 - 300, canvas.getWidth()/2 + 200, canvas.getHeight()/2 - 150, hairPaint);
                break;

            //moustache and goatee
            case 1:
                canvas.drawRect(canvas.getWidth()/2 - 100, canvas.getHeight()/2 + 70, canvas.getWidth()/2 + 100, canvas.getHeight()/2 + 90, hairPaint);
                canvas.drawRect(canvas.getWidth()/2 - 10, canvas.getHeight()/2 + 185, canvas.getWidth()/2 + 10, canvas.getHeight()/2 + 240, hairPaint);
                break;

            //unibrow
            case 2:
                canvas.drawRect(canvas.getWidth()/2 - 125, canvas.getHeight()/2 - 170, canvas.getWidth()/2 + 125, canvas.getHeight()/2 - 190, hairPaint);
                break;
        }

    }

    /**
     * resets Face's variables to random values
     * called on creation and by random button
     */
    public void randomize() {

        skinColor = gen.nextInt();
        eyeColor = gen.nextInt();
        hairColor = gen.nextInt();
        hairStyle = gen.nextInt(3);

        //bitwise ORs with a color of maximum opacity
        skinPaint.setColor(0xff000000|skinColor);

        eyePaint.setColor(0xff000000|eyeColor);

        hairPaint.setColor(0xff000000|hairColor);

    }

    /**
     * Updates skinColor int and gives that color to skinPaint
     * @param r red part of RGB value
     * @param g green part of RGB value
     * @param b blue part of RGB value
     */
    public void setSkinColor(int r, int g, int b) {

        //bitwise ORs with a color of maximum opacity
        skinColor = (0xff000000)|(r<<16)|(g<<8)|(b);
        skinPaint.setColor(skinColor);

    }

    /**
     * Updates eyeColor int and gives that color to eyePaint
     * @param r red part of RGB value
     * @param g green part of RGB value
     * @param b blue part of RGB value
     */
    public void setEyeColor(int r, int g, int b) {

        //bitwise ORs with a color of maximum opacity
        eyeColor = (0xff000000)|(r<<16)|(g<<8)|(b);
        eyePaint.setColor(eyeColor);

    }

    /**
     * Updates hairColor int and gives that color to hairPaint
     * @param r red part of RGB value
     * @param g green part of RGB value
     * @param b blue part of RGB value
     */
    public void setHairColor(int r, int g, int b) {

        //bitwise ORs with a color of maximum opacity
        hairColor = (0xff000000)|(r<<16)|(g<<8)|(b);
        hairPaint.setColor(hairColor);

    }

    public void setHairStyle(int h) {

        hairStyle = h;

    }


}
