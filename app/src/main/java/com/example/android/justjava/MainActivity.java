package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;

/**
 * This app displays an order form to order coffee.
 */
    public class MainActivity extends AppCompatActivity {
        //объявляю глобальные переменные
        int quantity = 99;
        private static final String TAG = "JustJava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //метод для расчета стоимости
    private int calculatePrice (boolean hasChocolate, boolean hasWhippedCream) {
        int basePrice = 5;
        if (hasChocolate){
            basePrice = basePrice + 2;
        }
        if (hasWhippedCream) {
            basePrice = basePrice + 1;
        }
            return quantity*basePrice;
    }
    //Метод для вывода уведомления
    private String createSummaryOrder (int price, boolean hasWhippedCream, boolean hasChocolate, String userName){
        String priceMessage = getString(R.string.order_summary_name, userName);
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream, hasWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_chocolate, hasChocolate);
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n" + getString(R.string.order_summary_price, price)+ "$";
        priceMessage += "\n" + getString(R.string.order_summary_message);
        return priceMessage;
    }
    //Метод, который выводит информацию после клика на кнопку Submit
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.notify_me_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();
        EditText userNameView = (EditText) findViewById(R.id.username_view);
        String userName = userNameView.getText().toString();
        int price = calculatePrice(hasChocolate, hasWhippedCream);
        String priceMessage = createSummaryOrder(price, hasWhippedCream, hasChocolate, userName);
        displayMessage(priceMessage);
      }

   //Открываем почтовый клиент для отправки сообщения
    public void sendMail (View view){
        // Получаем данные для отправки
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.notify_me_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();
        EditText userNameView = (EditText) findViewById(R.id.username_view);
        String userName = userNameView.getText().toString();
        int price = calculatePrice(hasChocolate, hasWhippedCream);
        String priceMessage = createSummaryOrder(price, hasWhippedCream, hasChocolate, userName);
        //Вызываем почтовый клиент
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:vpedash@yahoo.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.emailMessage) + " " + userName );
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
        }
    }
    //Устанавливаем напоминание
    public void addAlarm (View view) {
        EditText userNameView = (EditText) findViewById(R.id.username_view);
        String userName = userNameView.getText().toString();
        int hours = new Time(System.currentTimeMillis()).getHours();
        int minutes = new Time(System.currentTimeMillis()).getMinutes();
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
        .putExtra(AlarmClock.EXTRA_MESSAGE, getString(R.string.emailMessage) + " " + userName)
        .putExtra(AlarmClock.EXTRA_HOUR, hours +1 )
        .putExtra(AlarmClock.EXTRA_MINUTES, minutes );
           if (intent.resolveActivity(getPackageManager()) != null) {
           startActivity(intent);
        }
    }
    //Метод для вызова календаря и установки event
    public void addEvent(View view) {
        EditText userNameView = (EditText) findViewById(R.id.username_view);
        String userName = userNameView.getText().toString();
        int hours = new Time(System.currentTimeMillis()).getHours();
        int minutes = new Time(System.currentTimeMillis()).getMinutes();
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, getString(R.string.emailMessage) + " " + userName )
                .putExtra(CalendarContract.Events.EVENT_LOCATION, getString(R.string.shopName))
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, hours + 1)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    //Метод для увеличения количества на единицу
    public void increment(View view) {
        if (quantity == 100){
            Toast toast = Toast.makeText(MainActivity.this, getString(R.string.incrementMessage), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity= quantity+1;
        display(quantity);
        }
    //Метод для уменьшения количества на единицу
    public void decrement(View view) {
        if (quantity == 1) {
            Toast toast = Toast.makeText(MainActivity.this, getString(R.string.decrementMessage), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity= quantity-1;
        display(quantity);
    }
    // Метод, который тобржает количество чашек
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
       //Метод, который выводит окончательный результат
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
          orderSummaryTextView.setText(message);
          }
 }

