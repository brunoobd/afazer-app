package br.senai.sp.cotia.afazerapp.model;

import lombok.Data;

@Data
public class SubTarefa {
    private Long id;
    private String descicao;
    private String foto;
    private boolean concluido;
    private Long idTarefa;
}
