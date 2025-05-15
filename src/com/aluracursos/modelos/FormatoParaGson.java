package com.aluracursos.modelos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class FormatoParaGson {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static Gson getGson() {
        return gson;
    }
}
