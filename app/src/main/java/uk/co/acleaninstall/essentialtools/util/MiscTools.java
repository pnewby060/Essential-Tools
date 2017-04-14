package uk.co.acleaninstall.essentialtools.util;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.github.lazylibrary.util.FileUtils;
import uk.co.acleaninstall.essentialtools.MyApp;

public class MiscTools {

    public static void setViewElevation(View viewToSet, Float elevationAmount) {
        if (viewToSet != null) {
            ViewCompat.setElevation(viewToSet, elevationAmount);
        }
    }


    // A method to find height of the status bar
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = MyApp.getContext()
            .getResources()
            .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = MyApp.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    public static void startActivity(Context context, Class<?> clazz) {
        context.startActivity(new Intent(context, clazz));
    }


    public static void hideSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
            Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public static boolean hasWhatsAppDirectory() {
        boolean hasDirectory;
        hasDirectory = FileUtils.isFolderExist(Constants.FileConstants.WHATSAPP_DIR);
        return hasDirectory;
    }

}
