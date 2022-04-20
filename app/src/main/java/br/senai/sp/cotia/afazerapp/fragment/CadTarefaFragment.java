package br.senai.sp.cotia.afazerapp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

import br.senai.sp.cotia.afazerapp.R;
import br.senai.sp.cotia.afazerapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.cotia.afazerapp.databinding.FragmentPrincipalBinding;

public class CadTarefaFragment extends Fragment {
    private FragmentCadTarefaBinding binding;
    private DatePickerDialog datePicker;
    // váriaveis para ano, mês e dia
    int year, month, day;
    // váriavel para a data formatada
    Calendar dataAtual;
    // variável para a data formatada
    String dataFormatada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        }, year, month, day);

        // ação do click do botão de seleção da data
        binding.btData.setOnClickListener(view -> {
            datePicker.show();
        });

        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }
}