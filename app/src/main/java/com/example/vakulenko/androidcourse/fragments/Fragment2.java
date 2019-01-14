package com.example.vakulenko.androidcourse.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.vakulenko.androidcourse.R;

/**
 * Фрагмента с кнопкой для отображения еще одного фрагмента
 * @see Fragment3
 */
public class Fragment2 extends Fragment implements View.OnClickListener {

    private Fragment3 fragment3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment2, container, false);
        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment3 fragment3 = new Fragment3();
        transaction.replace(R.id.container, fragment3);
        transaction.commit();
    }

    public String getText() {
        return fragment3 != null ? fragment3.getText() : "";
    }
}
