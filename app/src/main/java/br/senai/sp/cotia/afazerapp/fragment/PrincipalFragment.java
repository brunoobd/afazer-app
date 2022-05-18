package br.senai.sp.cotia.afazerapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

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
            NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_cadTarefaFragment);
            ;
        });

        // instancia a database
        database = AppDatabase.getDatabase(getContext());

        // define o layout manager do recycler
        binding.recyclerTarefas.setLayoutManager(new LinearLayoutManager(getContext()));

        // executar a asynctask
        new ReadTarefa().execute();


        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }

    class ReadTarefa extends AsyncTask<Void, Void, List<Tarefa>> {
        @Override
        protected List<Tarefa> doInBackground(Void... voids) {
            // buscar as tarefas e guardar na variável tarefas
            tarefas = database.getTarefaDao().findAll();
            return tarefas;
        }

        @Override
        protected void onPostExecute(List<Tarefa> tarefas) {
            // instancia o adapter
            adapter = new TarefaAdapter(tarefas, getContext(), listenerClick);
            // aplica o adapter no recycler
            binding.recyclerTarefas.setAdapter(adapter);
        }
    }

    // listener para click nas tarefas
    private TarefaAdapter.OnTarefaCLickListener listenerClick = (view, tarefa) -> {
        // variavel paa "pendurar" a tarefa
        Bundle bundle = new Bundle();
        // "pendura" a tarefa no bundle
        bundle.putSerializable("tarefa", tarefa);
        // navego para o fragmento de detalhes enviando a tarefa no bundle
        NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_detalheTarefaFragment, bundle);
    };
}