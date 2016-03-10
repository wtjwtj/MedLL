package softdev1.medll;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by wjwjwt on 31/12/15.
 */
public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("ERROR")
                .setMessage("There was an error")
                .setPositiveButton("OK", null);
AlertDialog dialog = builder.create();
        return dialog;
    }
}
