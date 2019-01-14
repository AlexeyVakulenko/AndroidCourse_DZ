package com.example.vakulenko.androidcourse.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.vakulenko.androidcourse.DataProvider;
import com.example.vakulenko.androidcourse.R;

/**
 * Фрагмент с TextView для отображения переданного значения
 */
public class Fragment3 extends Fragment {

    private DataProvider provider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment3, container, false);
        TextView textView = rootView.findViewById(R.id.text_view_2);
        String text = provider.getText();
        textView.setText(text);
        return rootView;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        provider = (DataProvider) context;
    }
}
