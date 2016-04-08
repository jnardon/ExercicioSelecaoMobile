package com.fourall.exercicioselecaomobile.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fourall.exercicioselecaomobile.R;
import com.fourall.exercicioselecaomobile.api.ApiResponses;
import com.fourall.exercicioselecaomobile.helpers.AlertHelper;
import com.fourall.exercicioselecaomobile.helpers.ConnectionHelper;
import com.fourall.exercicioselecaomobile.helpers.JsonHelper;
import com.fourall.exercicioselecaomobile.helpers.UrlHelper;
import com.fourall.exercicioselecaomobile.views.SpinnerDialog;

import org.json.JSONObject;

import java.util.List;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<String> elementoIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.list_activity_toolbar);
        setSupportActionBar(toolbar);

        if(ConnectionHelper.isConnected(this)){
            getListaIds();
        } else {
            try {
                findViewById(R.id.text_view_sem_internet).setVisibility(View.VISIBLE);
                AlertHelper.getInstance().showInternetAlert(this);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        
    }

    private void getListaIds(){
        final SpinnerDialog dialog = new SpinnerDialog(this);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                dialog.show();

                // Pega Lista da api
                JSONObject listaObject = JsonHelper.GET(UrlHelper.LISTA);
                elementoIds = ApiResponses.getElementosIds(listaObject);

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
        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                elementoIds );

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Starta activity do detalhe do elemento, passando como extra o id
        Intent elementoIntent = new Intent(this, ElementDetailActivity.class);
        elementoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        elementoIntent.putExtra("id", elementoIds.get(position));
        startActivity(elementoIntent);
    }
}
