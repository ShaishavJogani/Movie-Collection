package com.example.shais.moviedatabase.utils;

import java.util.Timer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shais.moviedatabase.R;

/**
 * A class to display loading/progress layout. It will freeze your UI to restrict user's interaction while you are loading data.
 * Created by Shaishav on 3/24/2018.
 */
public class GlobalLoader {

    public static Dialog dialog;
    static Timer t;
    static TextView tv;

    public GlobalLoader(final String PreloaderText, final Context context,
                        final Activity activity) {
        // TODO Auto-generated constructor stub
        dialog = new Dialog(context, R.style.Theme_Transparent);

        dialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        dialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        View v;

        v = activity.getLayoutInflater().inflate(R.layout.loading_layout,
                null, false);
        RelativeLayout rLayout = (RelativeLayout) v
                .findViewById(R.id.progressContainer);

        tv = (TextView) v.findViewById(R.id.progress_text);
        tv.setText(PreloaderText);
        tv.setTextColor(Color.WHITE);

        dialog.setContentView(v);
        dialog.setCancelable(false);

        dialog.show();

    }

    public static void FinishMe() {

        try {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (t != null) {
            t.cancel();
        }
    }

    public static void updateText(String text) {
        try {
            if (dialog != null) {
                if (dialog.isShowing() && tv != null) {
                    tv.setText(text);
                    tv.invalidate();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}