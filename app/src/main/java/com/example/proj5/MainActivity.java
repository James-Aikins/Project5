package com.example.proj5;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import app.*;

/**
 * the main menu controller for activity_main.xml
 * @author James Aikins, Michael Radoian
 */
public class MainActivity extends AppCompatActivity {

    private Order currOrder;
    private StoreOrders storeOrders;

    private final int DONUT_REQUEST = 1;
    private final int COFFEE_REQUEST = 2;
    private final int CURR_REQUEST = 3;
    private final int STORE_REQUEST = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currOrder = new Order();
        storeOrders = new StoreOrders();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent donutView = new Intent(this,DonutActivity.class);
        final Button openDonuts = findViewById(R.id.openDonuts);
        openDonuts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(donutView,DONUT_REQUEST);
            }
        });

        final Intent coffeeView = new Intent(this,CoffeeActivity.class);
        final Button openCoffee = findViewById(R.id.openCoffee);
        openCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(coffeeView,COFFEE_REQUEST);
            }
        });

        final Intent currView = new Intent(this,CurrOrderActivity.class);
        final Button openCurrOrder = findViewById(R.id.openCurr);
        openCurrOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currView.putExtra("currOrder",currOrder);
                startActivityForResult(currView,CURR_REQUEST);
            }
        });

        final Intent storeOrderView = new Intent(this,StoreOrderActivity.class);
        final Button openStoreOrder = findViewById(R.id.openStoreOrder);
        openStoreOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeOrderView.putExtra("storeOrders",storeOrders);
                startActivityForResult(storeOrderView,STORE_REQUEST);
            }
        });

        currOrder = new Order();
    }

    /**
     * Recieves the data provided by closed events and updates values accordingly
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DONUT_REQUEST) {
            if(resultCode == RESULT_OK) {
                Order donutOrder = (Order)data.getSerializableExtra("donutOrder");
                for(MenuItem d: donutOrder.getList()){
                    currOrder.add(d);
                }
            }
        }else if(requestCode == COFFEE_REQUEST){
            if(resultCode == RESULT_OK) {
                Order coffeeOrder = (Order)data.getSerializableExtra("coffeeOrder");
                for (MenuItem c: coffeeOrder.getList()){
                    currOrder.add(c);
                }
            }
        }else if(requestCode == CURR_REQUEST){
            if(resultCode == RESULT_OK){
                Order placedOrder = (Order)data.getSerializableExtra("order");
                storeOrders.add(placedOrder);
                currOrder = new Order();
            }
        } else if(requestCode == STORE_REQUEST){
            if(resultCode == RESULT_OK){
                storeOrders = (StoreOrders) data.getSerializableExtra("storeOrder");
            }
        }
    }

}