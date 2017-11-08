package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
    public class MainActivity extends AppCompatActivity {
        //объявляю глобальные переменные
        int quantity = 0;
        private static final String TAG = "JustJava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //метод для расчета стоимости
    private int calculatePrice () {
        int price = quantity * 5;
        return price;
    }
    //Метод для вывода уведомления
    private String createSummaryOrder (int price){
        String priceMessage = "Name: Vitaliy Pedash";
        priceMessage += "\nQuanty: " + quantity;
        priceMessage += "\nTotal: " +  price;
        priceMessage += "\nThanks for purchase!";
        return priceMessage;
    }
    //Метод, который выводит информацию после клика на кнопку Submit
    public void submitOrder(View view) {
        Log.d(TAG, "Посчитаем общую стоимость");
        int price = calculatePrice();
        String priceMessage = createSummaryOrder(price);
        displayMessage(priceMessage);
      }
    //Метод для увеличения количества на единицу
    public void increment(View view) {
        Log.d(TAG, "Увеличиваем на 1 единицу кол-во  чашек");
        quantity= quantity+1;
        display(quantity);
        Toast toast = Toast.makeText(MainActivity.this, "Нажата кнопка +", Toast.LENGTH_LONG);
        toast.show();
        }
    //Метод для уменьшения количества на единицу
    public void decrement(View view) {
        Log.d(TAG, "Уменьшаем на 1 единицу кол-во чашек");
        quantity= quantity-1;
        display(quantity);
        Toast toast = Toast.makeText(MainActivity.this, "Нажата кнопка -", Toast.LENGTH_LONG);
        toast.show();
    }
    // Метод, который тобржает количество чашек
    private void display(int number) {
        Log.d(TAG,"Отобразим общее кол-во чашек");
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
       //Метод, который выводит окончательный результат
      private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
          orderSummaryTextView.setText(message);
          Toast toast = Toast.makeText(MainActivity.this, "Нажата кнопка Order", Toast.LENGTH_LONG);
          toast.show();
    }
}

