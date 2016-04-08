package com.fourall.exercicioselecaomobile.helpers;

/**
 * Created by julianonardon on 31/12/14.
 */
public class GlobalHelper {

    // Retorna string necessaria para trazer imagem do mapa pelo Google Maps
    public static String getUrlMapa(double latitude, double longitude) {
        // Não é preciso resolucao diferente para cada tela, pois o picasso já faz download da resolucao necessaria
        return "https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=16&size=1200x400";
    }
}
