package br.senai.sp.cotia.afazerapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.senai.sp.cotia.afazerapp.R;
import br.senai.sp.cotia.afazerapp.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>{
    // variavel para a lista de tarefas
    private List<Tarefa> tarefas;
    // variavel para o Context
    private Context context;
    // variavel pro listener
    private OnTarefaCLickListener listenerTarefa;

    // construtor que recebe os parametros para o adapter
    public TarefaAdapter(List<Tarefa> tarefas, Context context, OnTarefaCLickListener listenerTarefa) {
        this.tarefas = tarefas;
        this.context = context;
        this.listenerTarefa = listenerTarefa;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflar a view do ViewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_tarefa, parent, false);
        // retorna uma viewHolder
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        // obtem a tarefa atraves da position
        Tarefa t = tarefas.get(position);
        // transporta a info da tarefa para o holder
        holder.tvTitulo.setText(t.getTitulo());
        // formata a data e exibe no textView
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvData.setText(simpleDateFormat.format(t.getDataPrevista()));

        // verifica se esta concluida
        if(t.isConcluida()) {
            holder.vStatus.setBackgroundColor(context.getResources().getColor(R.color.green));
        } else if (t.isAtrasada()) {
            holder.vStatus.setBackgroundColor(context.getResources().getColor(R.color.red));
        } else {
            // trocar para amarela quando aberta
            holder.vStatus.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        // implementa o click na tarefa
        holder.itemView.setOnClickListener(v -> {
            listenerTarefa.onClick(v, t);
        });
    }

    @Override
    public int getItemCount() {
        if (tarefas != null) {
            return tarefas.size();
        }
        return 0;
    }

    class TarefaViewHolder extends RecyclerView.ViewHolder {
        // váriaveis para os componentes do layout
        TextView tvTitulo, tvData;
        View vStatus;
        public TarefaViewHolder(View view) {
            super(view);
            // passar da view para os componentes
            tvTitulo = (TextView) view.findViewById(R.id.tv_titulo);
            tvData = (TextView) view.findViewById(R.id.tv_data);
            vStatus = (View) view.findViewById(R.id.tv_status);
        }
    }

    // interface para o click na tarefa
    public interface OnTarefaCLickListener {
        void onClick(View view, Tarefa tarefa);
    }
}
