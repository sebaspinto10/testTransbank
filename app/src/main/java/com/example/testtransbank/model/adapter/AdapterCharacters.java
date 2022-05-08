package com.example.testtransbank.model.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.testtransbank.R;
import com.example.testtransbank.model.Characters;
import com.example.testtransbank.model.Results;
import com.example.testtransbank.view.CharactersDetail;
import com.example.testtransbank.view.RickAndMortyCharacters;

import java.util.ArrayList;

public class AdapterCharacters extends RecyclerView.Adapter<AdapterCharacters.CharactersViewHolder> {

    Context context;
    ArrayList<Results> characters;

    public AdapterCharacters(ArrayList<Results> characters, Context context) {
        this.characters = characters;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterCharacters.CharactersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_characters, parent, false);
        return new CharactersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCharacters.CharactersViewHolder holder, int position) {

        Results results = characters.get(position);
        holder.characterName.setText(results.getName());
        holder.characterStatus.setText(results.getStatus());
        holder.img = results.getImage();
        holder.characterSpecies = results.getSpecies();

        Glide.with(context)
                .load(holder.img)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.characterImage);

        holder.cv.setOnClickListener(v -> {

            Intent characterDetailIntent = new Intent(context, CharactersDetail.class);
            characterDetailIntent.putExtra("image", holder.img);
            characterDetailIntent.putExtra("name", holder.characterName.getText());
            characterDetailIntent.putExtra("species", holder.characterSpecies);
            characterDetailIntent.putExtra("status", holder.characterStatus.getText());

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                    holder.characterImage, holder.characterImage.getTransitionName());
            context.startActivity(characterDetailIntent, options.toBundle());

        });
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @SuppressLint("NotifyDataSetChanged")
    public void addCharacters (ArrayList <Results> moreCharacters){
        characters.addAll(moreCharacters);
        notifyDataSetChanged();
    }

    public static class CharactersViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView characterImage;
        TextView characterName;
        TextView characterStatus;
        String img;
        String characterSpecies;

        public CharactersViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            characterImage = itemView.findViewById(R.id.img_character);
            characterName = itemView.findViewById(R.id.tv_character_name);
            characterStatus = itemView.findViewById(R.id.tv_character_status);
        }
    }
}
