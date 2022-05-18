package br.senai.sp.cotia.afazerapp.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.cotia.afazerapp.R;
import br.senai.sp.cotia.afazerapp.databinding.FragmentCadSubTarefaBinding;
import br.senai.sp.cotia.afazerapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.cotia.afazerapp.model.Tarefa;

public class CadSubTarefaFragment extends Fragment {
    private FragmentCadSubTarefaBinding binding;
    private Tarefa tarefa;
    // váriavel para o dialog da foto
    private AlertDialog dialogFoto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia o binding
        binding = FragmentCadSubTarefaBinding.inflate(getLayoutInflater(), container, false);

        // verifica se foi passado algo no bundle
        if (getArguments() != null) {
            // recupera a tarefa do bundle
            tarefa = (Tarefa) getArguments().getSerializable("tarefa");
            binding.tvTituloTarefa.setText(tarefa.getTitulo());
        }

        // habilita o menu
        setHasOptionsMenu(true);

        // instancia o dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.origem_imagem);
        String[] opcoes = {getString(R.string.camera), getString(R.string.galeria)};
        builder.setItems(opcoes, listenerDialog);
        dialogFoto = builder.create();

        // listener do click da imagem
        binding.ivImagem.setOnClickListener(v -> {
            dialogFoto.show();
        });

        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }

    private DialogInterface.OnClickListener listenerDialog = (dialog, i) -> {
       switch (i) {
           case 0:
               Log.w("ITEM", "CAMERA");
               break;
           case 1:
               Log.w("ITEM", "GALERIA");
               break;
       }
    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_subtarefa, menu);
    }
}