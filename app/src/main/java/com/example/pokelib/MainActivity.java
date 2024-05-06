package com.example.pokelib;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
{
    private IApiService _api_service;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _api_service = ApiClient.getRetrofitInstance().create(IApiService.class);

        GetRandomPokemon();

        ImageButton _btn_get_data = findViewById(R.id.btn_find_new_data);

        _btn_get_data.setOnClickListener(v -> { GetRandomPokemon(); });
    }

    private void GetRandomPokemon()
    {
        int _random_pokemon_id = (int)(Math.random() * 1000) + 1;

        _api_service.GetPokemonById(_random_pokemon_id).enqueue(new Callback<Pokemon>()
        {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    Pokemon pokemon = response.body();

                    DisplayPokemonDetails(pokemon);
                }
                else
                    Toast.makeText(MainActivity.this, "Error while receiving data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, "Error executing request", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void DisplayPokemonDetails(Pokemon pokemon)
    {
        if (pokemon != null && pokemon.GetSprites() != null && pokemon.GetStats() != null)
        {
            ImageView pokemon_image = findViewById(R.id.pokemon_image);
            TextView text_name = findViewById(R.id.text_name);
            TextView weight_value = findViewById(R.id.text_weight_value);
            TextView height_value = findViewById(R.id.text_height_value);
            ProgressBar progressbar_hp = findViewById(R.id.progressbar_hp);
            ProgressBar progressbar_atk = findViewById(R.id.progressbar_atk);
            ProgressBar progressbar_def = findViewById(R.id.progressbar_def);
            ProgressBar progressbar_spa = findViewById(R.id.progressbar_spa);
            ProgressBar progressbar_spd = findViewById(R.id.progressbar_spd);
            ProgressBar progressbar_exp = findViewById(R.id.progressbar_exp);

            text_name.setText(pokemon.GetName().toUpperCase());
            weight_value.setText(String.valueOf(pokemon.GetWeight()) + " KG");
            height_value.setText(String.valueOf(pokemon.GetHeight()) + " M");

            Picasso.get().load(pokemon.GetSprites().GetFrontDefault()).into(pokemon_image);

            for (Pokemon.Stat stat : pokemon.GetStats())
            {
                if (stat.getStat().getName().equals("hp"))
                    progressbar_hp.setProgress(stat.getBase_stat());
                else if (stat.getStat().getName().equals("attack"))
                    progressbar_atk.setProgress(stat.getBase_stat());
                else if (stat.getStat().getName().equals("defense"))
                    progressbar_def.setProgress(stat.getBase_stat());
                else if (stat.getStat().getName().equals("special-attack"))
                    progressbar_spa.setProgress(stat.getBase_stat());
                else if (stat.getStat().getName().equals("special-defense"))
                    progressbar_spd.setProgress(stat.getBase_stat());
                else if (stat.getStat().getName().equals("speed"))
                    progressbar_exp.setProgress(stat.getBase_stat());
            }
        }
    }
}