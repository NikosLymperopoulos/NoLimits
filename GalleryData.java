package com.example.nolimits;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class GalleryData extends AppCompatActivity {

    Gallery simpleGallery;

    // CustomizedGalleryAdapter is a java class which extends BaseAdapter
    // and implement the override methods.
    CustomizedGalleryAdapter customGalleryAdapter;
    ImageView selectedImageView;

    // To show the selected language, we need this
    // array of images, here taken 10 different kind of most popular programming languages
    int[] images = {R.drawable.aroma, R.drawable.bigtavern, R.drawable.blueprint, R.drawable.chubbycat, R.drawable.finetaste,
            R.drawable.gastrognome, R.drawable.honeychicken, R.drawable.pearcity, R.drawable.prettypalace, R.drawable.ramentown,
            R.drawable.restaurantcarte, R.drawable.sharkfin, R.drawable.smalltavern, R.drawable.streetwise, R.drawable.tastyfish,
            R.drawable.thehotfish, R.drawable.themellowgrill};

    int positionClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_layout);

        // Our layout is activity_main
        // get the reference of Gallery. As we are showing languages it is named as languagesGallery
        // meaningful names will be good for easier understanding
        simpleGallery = (Gallery) findViewById(R.id.languagesGallery);

        // get the reference of ImageView
        selectedImageView = (ImageView) findViewById(R.id.imageView);

        // initialize the adapter
        customGalleryAdapter = new CustomizedGalleryAdapter(getApplicationContext(), images);

        // set the adapter for gallery
        simpleGallery.setAdapter(customGalleryAdapter);

        // Let us do item click of gallery and image can be identified by its position
        simpleGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Whichever image is clicked, that is set in the selectedImageView
                // position will indicate the location of image
                selectedImageView.setImageResource(images[position]);
                positionClicked = position;
            }
        });
        selectedImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //nothing for now
            }
        });
        simpleGallery.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    //this if condition is true when edittext lost focus...
                    //check here for number is larger than 10 or not
                    View button = findViewById(R.id.frag_one_btn);
                    button.performClick();
                }
            }
        });


    }
}
