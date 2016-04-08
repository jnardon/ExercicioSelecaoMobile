package com.fourall.exercicioselecaomobile.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fourall.exercicioselecaomobile.R;
import com.fourall.exercicioselecaomobile.api.ApiResponses;
import com.fourall.exercicioselecaomobile.helpers.AlertHelper;
import com.fourall.exercicioselecaomobile.helpers.GlobalHelper;
import com.fourall.exercicioselecaomobile.helpers.JsonHelper;
import com.fourall.exercicioselecaomobile.helpers.UrlHelper;
import com.fourall.exercicioselecaomobile.models.Comentario;
import com.fourall.exercicioselecaomobile.models.Elemento;
import com.fourall.exercicioselecaomobile.views.ComentarioView;
import com.fourall.exercicioselecaomobile.views.SpinnerDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class ElementDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Elemento elemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_detail);

        this.loadElementoFromUrl(getIntent().getStringExtra("id"));
    }

    private void loadElementoFromUrl(final String id) {
        final SpinnerDialog dialog = new SpinnerDialog(this);
        // Thread para pegar os dados da api assincronamente
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Ao iniciar thread, aparece loading dialog
                dialog.show();

                // Pega elemento da api
                JSONObject elementoObject = JsonHelper.GET(UrlHelper.LISTA + "/" + id);
                elemento = ApiResponses.getElemento(elementoObject);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadContent();
                    }
                });

                // Quando thread acaba, some loading dialog
                dialog.dismiss();
            }
        });
        thread.start();
    }

    private void loadContent() {
        // Instancia as views
        TextView textViewLocalizacao = (TextView) findViewById(R.id.text_view_localizacao);
        TextView textViewEndereco = (TextView) findViewById(R.id.text_view_endereco);
        TextView textViewTexto = (TextView) findViewById(R.id.text_view_texto);
        TextView textViewTitulo = (TextView) findViewById(R.id.text_view_titulo);
        ImageView imageViewMapa = (ImageView) findViewById(R.id.image_view_mapa);
        ImageView imageViewLogo = (ImageView) findViewById(R.id.image_view_logo);
        LinearLayout comentariosContainer = (LinearLayout) findViewById(R.id.comentario_container);

        // Carrega as fotos em cash
        Picasso.with(this).load(this.elemento.getUrlFoto()).into(imageViewLogo);
        Picasso.with(this).load(GlobalHelper.getUrlMapa(this.elemento.getLatitude(), this.elemento.getLongitude())).into(imageViewMapa);

        // Carrega textos
        textViewLocalizacao.setText(this.elemento.getCidade() + " - " + this.elemento.getBairro());
        textViewEndereco.setText(this.elemento.getEndereco());
        textViewTexto.setText(this.elemento.getTexto());
        textViewTitulo.setText(this.elemento.getTitulo());

        // Infla comentarios
        for (Comentario comentario : elemento.getComentarios()) {
            comentariosContainer.addView(new ComentarioView(this, comentario));
        }

        this.setClicks();
    }

    private void setClicks(){
        findViewById(R.id.layout_ligar).setOnClickListener(this);
        findViewById(R.id.layout_servicos).setOnClickListener(this);
        findViewById(R.id.layout_endereco).setOnClickListener(this);
        findViewById(R.id.layout_comentarios).setOnClickListener(this);
        findViewById(R.id.layout_favoritos).setOnClickListener(this);
        findViewById(R.id.text_view_voltar).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_ligar:
                this.ligarClick();
                break;
            case R.id.layout_servicos:
                this.servicosClick();
                break;
            case R.id.layout_endereco:
                this.enderecoClick();
                break;
            case R.id.layout_comentarios:
                this.comentariosClick();
                break;
            case R.id.layout_favoritos:
                this.favoritosClick();
                break;
            case R.id.text_view_voltar:
                super.onBackPressed();
        }
    }

    // Clicks da barra pricpal
    private void ligarClick() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + elemento.getTelefone()));
        startActivity(callIntent);
    }

    private void servicosClick(){
        Intent intent = new Intent(this, ServicoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void enderecoClick(){
        AlertHelper.getInstance().showAlert(this, elemento.getEndereco());
    }

    private void comentariosClick(){
        ScrollView sv = (ScrollView)findViewById(R.id.scroll_view);
        sv.scrollTo(0, (int) findViewById(R.id.comentario_container).getY());
    }

    private void favoritosClick(){
        // Não faz nada
    }
}
