package com.fourall.exercicioselecaomobile.api;

import com.fourall.exercicioselecaomobile.models.Comentario;
import com.fourall.exercicioselecaomobile.models.Elemento;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julianonardon on 31/12/14.
 */

// Leitura API
public class ApiResponses {

    public static Elemento getElemento(JSONObject elementoObject) {
        try {
            return new Elemento(
                    elementoObject.getString("id"),
                    elementoObject.getString("cidade"),
                    elementoObject.getString("bairro"),
                    elementoObject.getString("urlFoto"),
                    elementoObject.getString("urlLogo"),
                    elementoObject.getString("titulo"),
                    elementoObject.getString("telefone"),
                    elementoObject.getString("texto"),
                    elementoObject.getString("endereco"),
                    elementoObject.getDouble("latitude"),
                    elementoObject.getDouble("longitude"),
                    getComentarios(elementoObject.getJSONArray("comentarios"))
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Comentario getComentario(JSONObject comentarioObject) {
        try {
            return new Comentario(
                    comentarioObject.getString("urlFoto"),
                    comentarioObject.getString("nome"),
                    comentarioObject.getInt("nota"),
                    comentarioObject.getString("titulo"),
                    comentarioObject.getString("comentario")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Comentario> getComentarios(JSONArray comentariosArray) {

        try {
            List<Comentario> comentarios = new ArrayList<>();

            for (int i = 0; i < comentariosArray.length(); i++) {
                JSONObject comentarioObject = comentariosArray.getJSONObject(i);
                comentarios.add(getComentario(comentarioObject));
            }

            return comentarios;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getElementosIds(JSONObject listObject) {
        try {
            List<String> ids = new ArrayList<>();
            JSONArray idsArray = listObject.getJSONArray("lista");
            for (int i = 0; i < idsArray.length(); i++) {
                ids.add(idsArray.getString(i));
            }
            return ids;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
