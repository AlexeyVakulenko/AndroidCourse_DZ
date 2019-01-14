package com.example.vakulenko.androidcourse;

import com.example.vakulenko.androidcourse.fragments.Fragment1;

/**
 * Интерфейс, который должен реализовать поставщик данных
 * @see MainActivity
 * @see Fragment1
 */
public interface DataProvider {
    String getText();
}
