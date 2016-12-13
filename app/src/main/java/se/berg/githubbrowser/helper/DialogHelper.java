package se.berg.githubbrowser.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import se.berg.githubbrowser.R;

/**
 * Created by olleberg on 2016-12-12.
 */

public class DialogHelper {
    private static DialogHelper instance;

    public static DialogHelper getInstance() {
        if (instance == null)
            instance = new DialogHelper();
        return instance;
    }

    public MaterialDialog createOpenLinkDialog(final Context context, final String url) {
        return new MaterialDialog.Builder(context)
                .content(R.string.repository_dialog_content)
                .title(R.string.repository_dialog_title)
                .positiveText(R.string.repository_dialog_positive_text)
                .negativeText(R.string.repository_dialog_negative_text)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        context.startActivity(browserIntent);
                    }
                }).build();
    }
}
