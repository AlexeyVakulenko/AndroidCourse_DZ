package com.example.vakulenko.androidcourse;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment3 extends Fragment {

    private Listener listener;

    public interface Listener {
        String getTextToView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment3, container, false);
        TextView textView = rootView.findViewById(R.id.text_view_2);
        String text = listener.getTextToView();
        Toast.makeText(getActivity(), String.format("Текст для вывода %s", text), Toast.LENGTH_SHORT).show();
        textView.setText(text);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Listener) context;
    }
}
