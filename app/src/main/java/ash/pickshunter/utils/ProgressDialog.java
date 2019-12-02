package ash.pickshunter.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import ash.pickshunter.R;

public class ProgressDialog {

    private static Dialog mDialog;

    public static void show(Context context, boolean isCancelable) {
        if (mDialog != null && mDialog.isShowing())
            return;
        mDialog = new Dialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.setCancelable(false);
        mDialog.setContentView(R.layout.loading_layout);
        mDialog.show();
    }

    public static void dismiss() {
        try {
            if (mDialog != null) {
                // if (mProgressDialog.isShowing()) {
                mDialog.dismiss();
                mDialog = null;
                // }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
