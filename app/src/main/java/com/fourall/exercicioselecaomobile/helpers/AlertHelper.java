package com.fourall.exercicioselecaomobile.helpers;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.fourall.exercicioselecaomobile.R;


public final class AlertHelper {

	private static final AlertHelper INSTANCE = new AlertHelper();

	private AlertHelper() {
		super();
	}
	public static AlertHelper getInstance() {
		return INSTANCE;
	}

	/**
	 * 
	 * @param context
	 * @param message
	 * @param okListener
	 */
	public void showAlert(final Context context, final String message, final DialogInterface.OnClickListener okListener) {
		new AlertDialog.Builder(context)
		.setMessage(message)
		.setPositiveButton(context.getString(R.string.ok), okListener)
		.setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int id) {
				dialog.dismiss();
			}
		}).show();
	}

	/**
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param okListener
	 */
	public void showAlert(final Context context, final String title, final String message, final DialogInterface.OnClickListener okListener) {
		new AlertDialog.Builder(context)
		.setTitle(title)
		.setMessage(message)
		.setPositiveButton(context.getString(R.string.yes), okListener)
		.setNegativeButton(context.getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int id) {
                dialog.dismiss();
            }
        }).show();
	}

	/**
	 * 
	 * @param context
	 */
	public void showInternetAlert(final Context context) {
		new AlertDialog.Builder(context)
		.setTitle(context.getString(R.string.alert))
		.setMessage(context.getString(R.string.alert_internet))
		.setCancelable(false)
		.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int id) {
				dialog.dismiss();
			}
		}).show();
	}

	public void showAlert(final Context context, final String title, final String message) {
		new AlertDialog.Builder(context)
				.setTitle(title)
				.setMessage(message)
				.setCancelable(false)
				.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(final DialogInterface dialog, final int id) {
						dialog.dismiss();
					}
				}).show();
	}

	public void showAlert(final Context context, final String message) {
		new AlertDialog.Builder(context)
				.setMessage(message)
				.setCancelable(false)
				.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(final DialogInterface dialog, final int id) {
						dialog.dismiss();
					}
				}).show();
	}

	public void showAlertWithListener(final Context context, final String title, final String message,final DialogInterface.OnClickListener okListener ) {
		new AlertDialog.Builder(context)
				.setTitle(title)
				.setMessage(message)
				.setCancelable(false)
				.setPositiveButton(context.getString(R.string.ok),okListener).show();
	}


	/**
	 * 
	 * @param context
	 * @param title
	 * @param items
	 * @param listener
	 */
	public void showAlertList(final Context context, final String title, final String[] items, final DialogInterface.OnClickListener listener) {
		new AlertDialog.Builder(context)
		.setTitle(title)
		.setItems(items, listener)
		.setCancelable(true)
		.show();
	}

	/**
	 * 
	 * @param context
	 */
	public void showConnectionErrorAlert(final Context context) {
		new AlertDialog.Builder(context)
		.setTitle(context.getString(R.string.error))
		.setMessage(context.getString(R.string.alert_internet))
		.setCancelable(false)
		.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int id) {
				dialog.dismiss();
			}
		})
		.show();
	}

	/**
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @return
	 */
	public ProgressDialog showIndeterminateDialog(final Context context, final String title, final String message) {
		final ProgressDialog ringProgressDialog = new ProgressDialog(context, ProgressDialog.THEME_HOLO_LIGHT);
		ringProgressDialog.setTitle(title);
		ringProgressDialog.setMessage(message);
		ringProgressDialog.setCancelable(false);
		ringProgressDialog.show();
		return ringProgressDialog;
	}

	/**
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param max
	 * @return
	 */
	public ProgressDialog showDialog(final Context context, final String title, final String message, final int max) {
		final ProgressDialog barProgressDialog = new ProgressDialog(context, ProgressDialog.THEME_HOLO_LIGHT);
		barProgressDialog.setTitle(title);
		barProgressDialog.setMessage(message);
		barProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		barProgressDialog.setProgress(0);
		barProgressDialog.setMax(max);
		barProgressDialog.show();
		return barProgressDialog;
	}

}