package com.namclu.android.bloquery.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.namclu.android.bloquery.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddInputDialogListener} interface
 * to handle interaction events.
 * Use the {@link AddInputDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddInputDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {

    private EditText mEditText;

    // Listener interface with a method passing back data result.
    public interface AddInputDialogListener {
        void onFinishAddInput(String inputText);
    }

    public AddInputDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    public static AddInputDialogFragment newInstance(String question) {
        AddInputDialogFragment fragment = new AddInputDialogFragment();
        Bundle args = new Bundle();
        args.putString("question", question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_input_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get field from view
        mEditText = (EditText) view.findViewById(R.id.field_add_input);
        // Fetch arguments from bundle and set question
        String title = getArguments().getString("question", "Enter question");
        getDialog().setTitle(title);
        // Show soft kb automatically and request focus to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        );

        // Setup a callback when the "Done" button is pressed on keyboard
        mEditText.setOnEditorActionListener(this);
    }

    // Fires when the "Done" button is pressed
    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text back to activity through the implemented listener
            AddInputDialogListener listener = (AddInputDialogListener) getActivity();
            listener.onFinishAddInput(mEditText.getText().toString());

            // Close the dialog and return back to the parent activity
            dismiss();
            return true;
        }
        return false;
    }
}
