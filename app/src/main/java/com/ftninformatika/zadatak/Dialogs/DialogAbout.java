package com.ftninformatika.zadatak.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;


public class DialogAbout extends AlertDialog.Builder {

    public DialogAbout(@NonNull Context context) {
        super(context);
        setTitle("Jelovnik");
        setMessage("Autor : Kisker");
        setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    public AlertDialog prepareDialog() {
        AlertDialog dialog = create();
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
}