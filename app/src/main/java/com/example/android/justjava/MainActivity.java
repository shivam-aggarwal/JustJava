/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        quantity  +=1;
        display(quantity);
    }
    public void decrement(View view) {
        quantity--;
        if(quantity < 0){
            quantity = 0;
        }
        display(quantity);
    }

    private String createOrderSummary(int price){
        return "Name:Shivam\n"+"Quantity: "+quantity+"\nTotal: $"+price+".00\nThank you!";
    }

    private int calPrice(){
        return quantity*5;
    }
    public void submitOrder(View view) {
        displayMessage(createOrderSummary(calPrice()));
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}