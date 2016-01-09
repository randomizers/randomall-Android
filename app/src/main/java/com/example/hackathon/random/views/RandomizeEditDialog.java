package com.example.hackathon.random.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackathon.random.R;

/**
 * Created by eastagile on 1/27/15.
 */
public class RandomizeEditDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private EditDialogCallback mCallback;
    private EditText mNameEditText;
    private EditText mSeedEditText;

    public RandomizeEditDialog(Context context, String name, String seed, EditDialogCallback callback) {
        super(context);
        mContext = context;
        mCallback = callback;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_participant_edit);

        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        getWindow().setLayout((6 * width) / 7, ViewGroup.LayoutParams.WRAP_CONTENT);

        mNameEditText = (EditText) findViewById(R.id.edit_dialog_name);
        mNameEditText.setText(name);
        mNameEditText.setSelection(mNameEditText.getText().length());

        mSeedEditText = (EditText) findViewById(R.id.edit_dialog_seed);
        mSeedEditText.setText(seed);
        mSeedEditText.setSelection(mSeedEditText.getText().length());

        findViewById(R.id.edit_dialog_save).setOnClickListener(this);
        findViewById(R.id.edit_dialog_delete).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_dialog_save:
                if (TextUtils.isEmpty(mNameEditText.getText().toString())) {
                    Toast.makeText(mContext, R.string.error_name_empty, Toast.LENGTH_SHORT).show();
                    return;
                }
                mCallback.onSaveSelected(mNameEditText.getText().toString(), mSeedEditText.getText().toString());
                cancel();
                break;
            case R.id.edit_dialog_delete:
                mCallback.onDeleteSelected();
                cancel();
                break;
            case R.id.edit_dialog_cancel:
                cancel();
                break;
        }
    }

    public interface EditDialogCallback {
        void onSaveSelected(String name, String seed);
        void onDeleteSelected();
    }
}
