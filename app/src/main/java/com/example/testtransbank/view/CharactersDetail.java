package com.example.testtransbank.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.testtransbank.R;

public class CharactersDetail extends AppCompatActivity {

    ImageView characterImage;
    TextView characterName;
    TextView characterSpecies;
    TextView characterStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_TestTransbank);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters_detail);

        characterImage = findViewById(R.id.img_character_detail);
        characterName = findViewById(R.id.tv_character_name_detail);
        characterSpecies = findViewById(R.id.tv_character_species);
        characterStatus = findViewById(R.id.tv_character_status_detail);

        characterName.setText(getIntent().getStringExtra("name"));
        characterSpecies.setText(getIntent().getStringExtra("species"));
        characterStatus.setText(getIntent().getStringExtra("status"));

        Glide.with(this)
                .load(getIntent().getStringExtra("image"))
                .apply(RequestOptions.centerCropTransform())
                .into(characterImage);

    }
}