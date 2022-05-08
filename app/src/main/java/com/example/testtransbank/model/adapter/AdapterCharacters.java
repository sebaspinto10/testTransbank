package com.example.testtransbank.model.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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

        Glide.with(context)
                .load(results.getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.characterImage);
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

        public CharactersViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            characterImage = itemView.findViewById(R.id.img_character);
            characterName = itemView.findViewById(R.id.tv_character_name);
            characterStatus = itemView.findViewById(R.id.tv_character_status);
        }
    }
}
