package com.example.facemaker;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * Takes user interaction with the layout and modifies a Face object accordingly
 * Implements listener for every element of the layout
 *
 * @author Andres Giesemann
 */
public class Controller implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {



    public enum FacialFeature{HAIR, SKIN, EYES}

    protected Face face;
    protected FaceView fv;
    protected SeekBar redSeek;
    protected SeekBar greenSeek;
    protected SeekBar blueSeek;
    protected Spinner hairStyler;

    protected FacialFeature currentFeature = FacialFeature.HAIR;

    /**
     * Constructor. Takes elements of the layout as parameters so that they may be effectively manipulated
     * Controller is created in MainActivity to be given access to those parameters
     *
     * SeekBars are given to the controller so that they can update to reflect changes that were made to
     * the colors by the randomizer button.
     *
     * Similarly, the Spinner controlling hairstyle is passed to the controller so that changes made by
     * the randomizer can be reflected in the spinner
     *
     * @param f FaceView. Included so that it may be invalidated and reflect changes that have been made
     * @param r SeekBar that controls red portion of RGB
     * @param g SeekBar that controls green portion of RGB
     * @param b SeekBar that controls blue portion of RGB
     * @param s Spinner for controlling hairstyle
     */
    public Controller(FaceView f, SeekBar r, SeekBar g, SeekBar b, Spinner s) {

        fv = f;
        face = f.getFace();
        redSeek = r;
        greenSeek = g;
        blueSeek = b;
        hairStyler = s;
        onClick(fv);

    }

    /**
     * Handles clicks of the randomizer button and updates status of Spinner and SeekBars
     * to reflect changes.
     * @param view (not used) the button that was clicked (in this case the randomizer)
     */
    @Override
    public void onClick(View view) {

        face.randomize();
        hairStyler.setSelection(face.hairStyle);
        updateSeeks();
        fv.invalidate();

    }

    /**
     * Handles changes to the RGB SeekBars
     * Sends user's update of RGB values to the appropriate Face methods
     * Allows user to control color of facial features
     * @param seekBar (not used) the SeekBar that triggered a progressChange
     * @param i (not used) progress of triggering SeekBar
     * @param triggeredByUser true when user causes progress change
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean triggeredByUser) {

        //Checks if triggeredByUser so that when progress is set after randomization, the call to onProgressChanged won't reset Face colors to their previous values
        if (triggeredByUser) {

            switch (currentFeature) {

                case HAIR:
                    face.setHairColor(redSeek.getProgress(), greenSeek.getProgress(), blueSeek.getProgress());
                    fv.invalidate();
                    break;

                case EYES:
                    face.setEyeColor(redSeek.getProgress(), greenSeek.getProgress(), blueSeek.getProgress());
                    fv.invalidate();
                    break;

                case SKIN:
                    face.setSkinColor(redSeek.getProgress(), greenSeek.getProgress(), blueSeek.getProgress());
                    fv.invalidate();
                    break;
            }
        }
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     * Handles changes to the selected RadioButton, setting the currentFeature variable accordingly
     * @param compoundButton the RadioButton that triggered this method. Each button corresponds to one of three facial features: hair, eyes, skin
     * @param isChecked whether the triggering RadioButton is checked
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        if (compoundButton.getText().equals("Hair") && isChecked) {
            currentFeature = FacialFeature.HAIR;
            //make the SeekBars show the RGB values of the newly selected feature
            updateSeeks();
        }

        else if (compoundButton.getText().equals("Skin") && isChecked) {
            currentFeature = FacialFeature.SKIN;
            updateSeeks();
        }

        else if (compoundButton.getText().equals("Eyes") && isChecked) {
            currentFeature = FacialFeature.EYES;
            updateSeeks();
        }

    }

    /**
     * Sets the progress of each SeekBar to match the RGB values of the currently selected facial feature
     */
    public void updateSeeks() {

        switch (currentFeature) {

            case HAIR:
                redSeek.setProgress((face.hairColor>>16)&0xff);
                greenSeek.setProgress((face.hairColor>>8)&0xff);
                blueSeek.setProgress((face.hairColor)&0xff);
                break;

            case EYES:
                redSeek.setProgress((face.eyeColor>>16)&0xff);
                greenSeek.setProgress((face.eyeColor>>8)&0xff);
                blueSeek.setProgress((face.eyeColor)&0xff);
                break;

            case SKIN:
                redSeek.setProgress((face.skinColor>>16)&0xff);
                greenSeek.setProgress((face.skinColor>>8)&0xff);
                blueSeek.setProgress((face.skinColor)&0xff);
                break;
        }
    }

    /**
     * Handles selection of hairstyle using the hairstyle Spinner
     * @param adapterView (not used)
     * @param view (not used)
     * @param selectedItemIndex simply index of the item selected from the Spinner. Passed directly to Face object to update hairstyle selection
     * @param l (not used)
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int selectedItemIndex, long l) {

        face.setHairStyle(selectedItemIndex);
        fv.invalidate();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
