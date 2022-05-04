package br.senai.sp.cotia.afazerapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.cotia.afazerapp.R;
import br.senai.sp.cotia.afazerapp.adapter.TarefaAdapter;
import br.senai.sp.cotia.afazerapp.database.AppDatabase;
import br.senai.sp.cotia.afazerapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.cotia.afazerapp.model.Tarefa;

public class PrincipalFragment extends Fragment {
    private FragmentPrincipalBinding binding;
    // variavel para a lista
    private List<Tarefa> tarefas;
    // variavel para o adapter
    private TarefaAdapter adapter;
    // variavel para o dataBase
    private AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia o binding
        binding = FragmentPrincipalBinding.inflate(getLayoutInflater(), container, false);

        binding.btnAdicionarTarefa.setOnClickListener(v -> {
            NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_cadTarefaFragment);;
        });

        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }

    class ReadTarefa extends AsyncTask<Void, Void, List<Tarefa>> {
        @Override
        protected List<Tarefa> doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(List<Tarefa> tarefas) {
            super.onPostExecute(tarefas);
        }


    }
}