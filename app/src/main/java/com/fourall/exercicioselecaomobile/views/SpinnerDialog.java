package com.fourall.exercicioselecaomobile.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.fourall.exercicioselecaomobile.R;

/**
 * Created by julianonardon on 31/12/14.
 */
public class SpinnerDialog {
    private Dialog dialog;
    private Context context;
    public SpinnerDialog(Context context){
        dialog = new Dialog(context);
        this.context = context;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_spinner);
        dialog.setCanceledOnTouchOutside(false);
    }
    public void show(){
        ((Activity) context).runOnUiThread(new Runnable() {
            public void run() {
                dialog.show();
            }
        });
    }
    public void dismiss(){
        ((Activity) context).runOnUiThread(new Runnable() {
            public void run() {
                dialog.dismiss();
            }
        });
    }
}
