package com.fourall.exercicioselecaomobile.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fourall.exercicioselecaomobile.R;
import com.fourall.exercicioselecaomobile.models.Comentario;
import com.squareup.picasso.Picasso;

/**
 * Created by julianonardon on 05/04/16.
 */
public class ComentarioView extends LinearLayout {

    private Comentario comentario;
    private LinearLayout layout;

    public ComentarioView(Context context, Comentario comentario) {
        super(context);
        this.comentario = comentario;
        inflate(context);
        loadContent();
    }

    private void inflate(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.layout = (LinearLayout) inflater.inflate(R.layout.view_comentario, this);

    }

    private void loadContent() {
        BezelImageView bezelImageView = (BezelImageView) findViewById(R.id.image_view_usuario);
        TextView textViewNome = (TextView) findViewById(R.id.text_view_nome);
        TextView textViewComentario = (TextView) findViewById(R.id.text_view_comentario);
        TextView textViewTitulo = (TextView) findViewById(R.id.text_view_titulo);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rating_bar);

        textViewNome.setText(comentario.getNome());
        textViewComentario.setText(comentario.getComentario());
        textViewTitulo.setText(comentario.getTitulo());
        ratingBar.setRating((float) comentario.getNota());
        Picasso.with(getContext()).load(this.comentario.getUrlFoto()).into(bezelImageView);
    }
}
