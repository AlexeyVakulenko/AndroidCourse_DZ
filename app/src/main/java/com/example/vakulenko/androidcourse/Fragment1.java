package com.example.vakulenko.androidcourse;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Fragment1 extends Fragment implements MainActivity.TextProvider {

    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment1, container, false);
        editText = rootView.findViewById(R.id.editText);
        return rootView;
    }

    public String getText() {
        return editText.getText().toString();
    }
}
