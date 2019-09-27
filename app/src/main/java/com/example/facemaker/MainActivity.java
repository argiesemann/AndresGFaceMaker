package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * FaceMaker App for CS301 Fall 2019
 * Displays a face that the user can customize
 *
 * @author Andres Giesemann
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrieve reference to hair style Spinner from layout
        //This Spinner is used to select one of three hairstyles
        Spinner hairStyler = (Spinner)(findViewById(R.id.hairSpinner));


        //Retrieve references to RGB SeekBars from layout
        //These sliders adjust the color of a selected face feature
        SeekBar redSeek = (SeekBar)findViewById(R.id.RedSeekBar);
        SeekBar greenSeek = (SeekBar)findViewById(R.id.GreenSeekBar);
        SeekBar blueSeek = (SeekBar)findViewById(R.id.BlueSeekBar);


        //Retrieve reference to FaceView from layout
        //Face displayed on this
        FaceView fv = (FaceView) (findViewById(R.id.faceView));


        //Controller object created with references to RGB SeekBars and hairstyle Spinner
        //Controller implements all the necessary listeners for modifications of the face
        Controller controller = new Controller(fv, redSeek, greenSeek, blueSeek, hairStyler);


        //listener assignment
        redSeek.setOnSeekBarChangeListener(controller);
        greenSeek.setOnSeekBarChangeListener(controller);
        blueSeek.setOnSeekBarChangeListener(controller);


        //Sets up Spinner entries from strings.xml with three hairstyle options
        ArrayAdapter stylesAdapter = ArrayAdapter.createFromResource(this, R.array.hair_styles, android.R.layout.simple_spinner_dropdown_item);
        stylesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairStyler.setAdapter(stylesAdapter);


        //assign listener to Spinner
        hairStyler.setOnItemSelectedListener(controller);


        //Button for randomizing face configuration
        Button randButton = (Button)(findViewById(R.id.randButton));
        randButton.setOnClickListener(controller);


        //RadioButtons for selecting which face feature (hair, eyes, skin) will be affected by color changes
        RadioButton hairButton = (RadioButton)findViewById(R.id.Hair);
        hairButton.setOnCheckedChangeListener(controller);

        RadioButton skinButton = (RadioButton)findViewById(R.id.Skin);
        skinButton.setOnCheckedChangeListener(controller);

        RadioButton eyeButton = (RadioButton)findViewById(R.id.Eyes);
        eyeButton.setOnCheckedChangeListener(controller);

    }
}
