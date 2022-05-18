package br.senai.sp.cotia.afazerapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;

import br.senai.sp.cotia.afazerapp.R;
import br.senai.sp.cotia.afazerapp.databinding.FragmentDetalheTarefaBinding;
import br.senai.sp.cotia.afazerapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.cotia.afazerapp.model.Tarefa;

public class DetalheTarefaFragment extends Fragment {
    private FragmentDetalheTarefaBinding binding;
    Tarefa tarefa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia o binding
        binding = FragmentDetalheTarefaBinding.inflate(getLayoutInflater(), container, false);

        // verifica se foi passado algo no bundle
        if (getArguments() != null) {
            // recupera a tarefa do bundle
            tarefa = (Tarefa) getArguments().getSerializable("tarefa");
            binding.tvTitulo.setText(tarefa.getTitulo());
            binding.tvDescricao.setText(tarefa.getDescricao());
            // formata a data e exibe no textView
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            binding.tvData.setText(simpleDateFormat.format(tarefa.getDataPrevista()));
        }

        binding.btnAdicionarTarefa.setOnClickListener(v -> {
            // variavel paa "pendurar" a tarefa
            Bundle bundle = new Bundle();
            // "pendura" a tarefa no bundle
            bundle.putSerializable("tarefa", tarefa);
            NavHostFragment.findNavController(DetalheTarefaFragment.this).navigate(R.id.action_detalheTarefaFragment_to_cadSubTarefaFragment, bundle);
        });
        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }
}