package br.com.rf.purpledeckschallenge.util;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class GUIUtils {

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
