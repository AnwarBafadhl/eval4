package com.example.eval4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_add, btn_viewAll;
    EditText et_name, et_age;
    Switch sw_active;
    ListView lv_customerList;

    ArrayAdapter customerArrayAdbter;
    DataBaseHealper dataBaseHealper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        sw_active = findViewById(R.id.sw_active);
        lv_customerList = findViewById(R.id.lv_customerList);

        ShowCustomersOnListView(dataBaseHealper);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomerModel customerModel;

                try{
                customerModel = new CustomerModel(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_active.isChecked());
                Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
            }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error creating Customer", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "error", 0, false);

                }

                DataBaseHealper dataBaseHealper = new DataBaseHealper(MainActivity.this);
                boolean success = dataBaseHealper.addOne(customerModel);

                Toast.makeText(MainActivity.this, "Success" + success, Toast.LENGTH_SHORT).show();


        }});

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dataBaseHealper = new DataBaseHealper(MainActivity.this);
                ShowCustomersOnListView(dataBaseHealper);


                //Toast.makeText(MainActivity.this, "View All Button", Toast.LENGTH_SHORT).show();

                }

            });
        }

        private void ShowCustomersOnListView(DataBaseHealper dataBaseHealper2) {
        customerArrayAdbter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHealper2.getEveryone());
            lv_customerList.setAdapter(customerArrayAdbter);
        }
    }

