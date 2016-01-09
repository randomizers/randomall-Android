package com.example.hackathon.random.views;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hackathon.random.R;

/**
 * Created by eastagile on 1/27/15.
 */
public class SaveListDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private ListSaveDialogCallback mCallback;
    private EditText mNameEditText;

    public SaveListDialog(Context context, ListSaveDialogCallback callback) {
        super(context);
        mContext = context;
        mCallback = callback;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_list_save);

        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        getWindow().setLayout((6 * width) / 7, ViewGroup.LayoutParams.WRAP_CONTENT);

        mNameEditText = (EditText) findViewById(R.id.list_save_dialog_name);

        findViewById(R.id.list_save_dialog_save).setOnClickListener(this);
        findViewById(R.id.list_save_dialog_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_save_dialog_save:
                if (TextUtils.isEmpty(mNameEditText.getText().toString())) {
                    Toast.makeText(mContext, R.string.error_name_empty, Toast.LENGTH_SHORT).show();
                    return;
                }
                mCallback.onSaveSelected(mNameEditText.getText().toString());
                cancel();
                break;
            case R.id.list_save_dialog_cancel:
                cancel();
                break;
        }
    }

    public interface ListSaveDialogCallback {
        void onSaveSelected(String title);
    }
}
