package com.pj.lib.errorhandler;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;

public class Error {
	public static void handle(final Context context, String app, final Exception e) {
		final String subject = "[" + app+ "] Error"; 
		new AlertDialog.Builder(context)
			.setMessage(context.getResources().getText(R.string.error_handler_message))
			.setPositiveButton(context.getResources().getText(R.string.yes), new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					final Intent emailIntent = new Intent(Intent.ACTION_SEND);
					String msg = e.getMessage() + "\n";
					for(StackTraceElement ste : e.getStackTrace()) {
						msg += ste.toString() + "\n";
					}
					emailIntent.setType("plain/text");
					emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"pjaneczek87@gmail.com"});
					emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
					emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, msg);
					context.startActivity(Intent.createChooser(emailIntent, context.getResources().getText(R.string.error_handler_chooser)));
				}
			})
			.setCancelable(true)
			.setNegativeButton(context.getResources().getText(R.string.no), null)
			.show();

		
	}
}
