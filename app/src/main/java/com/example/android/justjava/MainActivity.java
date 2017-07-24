package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    private static final String TAG = "JustJava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        Log.d(TAG, "Посчитаем общую стоимость");
        int price = 5;
        displayPrice(quantity*price);
        }
    /*
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        Log.d(TAG, "Увеличиваем на 1 единицу кол-во  чашек");
        quantity= quantity+1;
        display(quantity);
        }
    /**
     * This method displays the given quantity value on the screen.
     */
      /*
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        Log.d(TAG, "Уменьшаем на 1 единицу кол-во чашек");
        quantity= quantity-1;
        display(quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        Log.d(TAG,"Отобразим общее кол-во чашек");
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        Log.d(TAG, "Отобразим общую стоимость");
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
        Toast.makeText(this,"Пожалуйста, уплатите указанную сумму", Toast.LENGTH_SHORT).show();
    }
}
