package br.senai.sp.cotia.afazerapp.model;

import lombok.Data;

@Data
public class Tarefa {
    private Long id;
    private String titulo;
    private String descricao;
    private long dataCriacao;
    private long dataPrevista;
    private long dataFinalizada;
}
