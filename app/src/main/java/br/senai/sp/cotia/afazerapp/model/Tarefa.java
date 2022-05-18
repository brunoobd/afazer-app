package br.senai.sp.cotia.afazerapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;

import lombok.Data;
// lombok que cria os getters and setters
@Data
// anotação que indica para o bd que a classe é uma tabela
@Entity
public class Tarefa implements Serializable {
    // anotação para identificar o ID da nossa tabela, o autoGenarate é o auto increment
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String titulo;
    private String descricao;
    private long dataCriacao;
    private long dataPrevista;
    private long dataFinalizada;

    public boolean isConcluida() {
        return dataFinalizada != 0;
    }

    public boolean isAtrasada() {
        return Calendar.getInstance().getTimeInMillis() > dataPrevista;
    }
}
