package br.senai.sp.cotia.afazerapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.senai.sp.cotia.afazerapp.R;
import br.senai.sp.cotia.afazerapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // instancia o binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // seta na contentView a raiz (root) do binding
        setContentView(binding.getRoot());
    }
}