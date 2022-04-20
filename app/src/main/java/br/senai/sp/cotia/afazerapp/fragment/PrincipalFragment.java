package br.senai.sp.cotia.afazerapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.cotia.afazerapp.R;
import br.senai.sp.cotia.afazerapp.databinding.FragmentPrincipalBinding;

public class PrincipalFragment extends Fragment {
    private FragmentPrincipalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia o binding
        binding = FragmentPrincipalBinding.inflate(getLayoutInflater(), container, false);
        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }
}