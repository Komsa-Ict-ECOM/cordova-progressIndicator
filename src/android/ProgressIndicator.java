package org.apache.cordova.progressindicator;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.ProgressDialog;
import android.widget.ProgressBar;

public class ProgressIndicator extends CordovaPlugin {

    private ProgressDialog progressIndicator = null;
    private static final String LOG_TAG = "ProgressIndicator";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals("show")) {
            show();
            callbackContext.success();
            return true;
        } else if (action.equals("showSimple")) {
            show();
            callbackContext.success();
            return true;
        } else if (action.equals("showSimpleWithLabel")) {
            String text = args.getString(1);
            show("", text, false);
            callbackContext.success();
            return true;
        } else if (action.equals("showSimpleWithLabelDetail")) {
            String title = args.getString(1);
            String text = args.getString(2);
            show(title, text, true);
            callbackContext.success();
            return true;
        } else if (action.equals("showText")) {
            String title = args.getString(1);
            String text = args.getString(2);
            show(title, text, false);
            callbackContext.success();
            return true;
        } else if (action.equals("hide")) {
            hide();
            callbackContext.success();
            return true;
        } else {
            callbackContext.error("Not supported call. On Android we only support show, showSimple, showSimpleWithLabel and showSimpleWithLabelDetail");
        }

        return false;
    }

    /**
     * This shows the ProgressDialog
     */
    public void show() {
        progressIndicator = ProgressDialog.show(cordova.getActivity(), null, null, true, false);
        progressIndicator.setContentView(new ProgressBar(cordova.getActivity()));
    }

    /**
     * This shows the ProgressDialog
     */
    public void show(String text) {
        progressIndicator = ProgressDialog.show(cordova.getActivity(), text, null, true, false);
    }

    /**
     * This shows the ProgressDialog
     */
    public void show(String title, String detail, Boolean withTitle) {
        if(withTitle) {
            progressIndicator = ProgressDialog.show(cordova.getActivity(), title, detail, true, false);
        }
        else {
            progressIndicator = ProgressDialog.show(cordova.getActivity(), null, detail, true, false);
        }
    }

    /**
     * This hides the ProgressDialog
     */
    public void hide() {
        if (progressIndicator != null) {
            progressIndicator.dismiss();
            progressIndicator = null;
        }
    }
}
