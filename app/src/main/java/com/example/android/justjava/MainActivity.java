/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        if(quantity == 100){
            Toast.makeText(this,"You cannot have more than 100 coffees",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity += 1;
        display(quantity);
    }

    public void decrement(View view) {

        if (quantity == 1) {
            Toast.makeText(this,"You cannot have less than 1 coffees",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);
    }

    private String createOrderSummary(String name,int price, boolean wCream, boolean choco) {
        return "Name:" + name + "\nAdd Whipped Cream? " + wCream + "\nAdd Chocolate? " + choco + "\nQuantity: " + quantity + "\nTotal: $" + price + ".00\n"+getString(R.string.thank_you);
    }

    private int calPrice(boolean wCream,boolean choco) {

        int price = 5;
        if (wCream) {
            price += 1;
        }
        if (choco) {
            price += 2;
        }
        return price * quantity;
    }

    public void submitOrder(View view) {
        //Find the name of the user
        EditText nameEditText = findViewById(R.id.name);
        String name = nameEditText.getText().toString();
        //Check the checkboxes!
        CheckBox whippedCream = findViewById(R.id.whippedextra);
        CheckBox chocolate = findViewById(R.id.chocoextra);
        boolean wCream = whippedCream.isChecked();
        boolean choco = chocolate.isChecked();
        String message = createOrderSummary(name,calPrice(wCream,choco),wCream,choco);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:coffee@gmail.com"));
        //intent.putExtra(Intent.EXTRA_EMAIL, "");
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //for google map intent
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6,-123.3"));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}