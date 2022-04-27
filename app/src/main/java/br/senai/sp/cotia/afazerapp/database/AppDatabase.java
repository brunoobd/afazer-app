package br.senai.sp.cotia.afazerapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.senai.sp.cotia.afazerapp.model.Tarefa;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    // variavel para acessar a database
    private static AppDatabase database;
    // método para tarefaDao
    public abstract  TarefaDao getTarefaDao();

    // método para instanciar a database
    public static  AppDatabase getDatabase(Context context) {
        // verifica se a database é nula
        if (database == null) {
            // instancia a database
            database = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "afazer").build();
        }
        // retorna a database
        return database;
    }
}
