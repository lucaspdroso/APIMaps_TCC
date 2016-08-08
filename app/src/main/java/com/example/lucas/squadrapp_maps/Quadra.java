package com.example.lucas.squadrapp_maps;

import android.graphics.Color;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/**
 * Created by Lucas on 29/07/2016.
 */
public class Quadra {

    private double lng;
    private String titulo;
    private String proxEvento;
    private String tipoEvento;
    private String statusEvento;
    private float  cor;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    private double lat;

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getProxEvento() {
        return proxEvento;
    }

    public void setProxEvento(String proxEvento) {
        this.proxEvento = proxEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getStatusEvento() {
        return statusEvento;
    }

    public void setStatusEvento(String statusEvento) {
        this.statusEvento = statusEvento;
    }

    public float getCor() {
        return cor;
    }

    public void setCor(float cor) {
        this.cor = cor;
    }

    public Quadra(double lat, double lng, String titulo, String proxEvento, String tipoEvento, String statusEvento)
    {
        this.lat = lat;
        this.lng = lng;
        this.titulo = titulo;
        this.proxEvento = proxEvento;
        this.tipoEvento = tipoEvento;

        if (statusEvento.equals("patrocinado"))
            this.cor = BitmapDescriptorFactory.HUE_YELLOW;
        else
            if (statusEvento.equals("acontecendo"))
                this.cor = BitmapDescriptorFactory.HUE_GREEN;
            else
                if (statusEvento.equals("proximo"))
                    this.cor = BitmapDescriptorFactory.HUE_AZURE;
                else
                    this.cor = BitmapDescriptorFactory.HUE_RED;
    }
}
