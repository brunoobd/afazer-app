package br.senai.sp.cotia.afazerapp.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.RoomDatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.senai.sp.cotia.afazerapp.R;
import br.senai.sp.cotia.afazerapp.database.AppDatabase;
import br.senai.sp.cotia.afazerapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.cotia.afazerapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.cotia.afazerapp.model.Tarefa;

public class CadTarefaFragment extends Fragment {
    private FragmentCadTarefaBinding binding;
    private DatePickerDialog datePicker;
    // váriaveis para ano, mês e dia
    int year, month, day;
    // váriavel para a data formatada
    Calendar dataAtual;
    // variável para a data formatada
    String dataFormatada;

    // variavel para a database
    AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia a database
        database = AppDatabase.getDatabase(getContext());

        // instancia o binding
        binding = FragmentCadTarefaBinding.inflate(getLayoutInflater(), container, false);

        // instanciar a data atual
        dataAtual = Calendar.getInstance();
        // obter ano, mes e dia, da data atual
        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);

        // instanciar o datePicker
        datePicker = new DatePickerDialog(getContext(), (datePicker, ano, mes, dia)-> {
            // ao escolher uma data no dataPicker, cai aqui
            // passar para as váriaveis globais
            year = ano;
            month = mes;
            day = dia;
            // formata a data
            dataFormatada = String.format("%02d/%02d/%04d", day, month+1, year);
            // coloca a data formatada no texto do botao
            binding.btData.setText(dataFormatada);
        }, year, month, day);

        // ação do click do botão de seleção da data
        binding.btData.setOnClickListener(view -> {
            datePicker.show();
        });

        //listener do botao salvar
        binding.btSalvar.setOnClickListener(v -> {
            // validando a tarefa antes de salvar
            if (binding.etTitulo.getText().toString().isEmpty()) {
                // informando erro na edit text
                binding.etTitulo.setError(getString(R.string.vali_tarefa));
                // mensagem na tela
                Toast.makeText(getContext(), "Digite um título para sua tarefa.", Toast.LENGTH_SHORT).show();
            } else if (dataFormatada == null) {
                Toast.makeText(getContext(), "Escolha uma data de conclusão para sua tarefa.", Toast.LENGTH_SHORT).show();
            } else {
                // cria uma tarefa
                Tarefa tarefa = new Tarefa();
                tarefa.setTitulo(binding.etTitulo.getText().toString());
                tarefa.setDescricao(binding.etDescricao.getText().toString());
                tarefa.setDataCriacao(dataAtual.getTimeInMillis());
                // criar um calendar
                Calendar dataPrevista = Calendar.getInstance();
                // muda a data ára a data escolhida no datepicker
                dataPrevista.set(year, month, day);
                // passa os milisegundos da data para a data prevista
                tarefa.setDataPrevista(dataPrevista.getTimeInMillis());
                // salvar a tarefa
                new InsertTarefa().execute(tarefa);
            }
        });

        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }
    // AsyncTask para inserir Tarefa
    private class InsertTarefa extends AsyncTask<Tarefa, Void, String> {

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            // pegar a tarefa a partir do vetor
            Tarefa t = tarefas[0];
            // chamar o método para salvar a tarefa
            try {
                database.getTarefaDao().insert(t);
                return "ok";
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals("ok")) {
                Toast.makeText(getContext(), "Tarefa Inserida com sucesso!", Toast.LENGTH_SHORT).show();
                // voltar ao fragment anterior
                getActivity().onBackPressed();
            } else {
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
            }
        }
    }
}