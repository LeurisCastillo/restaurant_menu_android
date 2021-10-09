package com.example.interfacesdecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spnfoodCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnfoodCategory = findViewById(R.id.spn_food_category);

        String[] kindOfFood = {"Breakfast", "Meal", "Afternoon snack", "Dinner", "Dessert"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kindOfFood);
        spnfoodCategory.setAdapter(adapter);

        spnfoodCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (spnfoodCategory.getItemAtPosition(i).toString()){

                    case "Breakfast":
                        goToFoodList("Breakfast");
                        break;

                    case "Meal":
                        goToFoodList("Meal");
                        break;

                    case "Afternoon snack":
                        goToFoodList("Afternoon snack");
                        break;

                    case "Dinner":
                        goToFoodList("Dinner");
                        break;

                    case "Dessert":
                        goToFoodList("Dessert");
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void goToFoodList (String foodCategory) {
        Intent intent = new Intent(this, FoodListActivity.class);
        intent.putExtra("Food category", foodCategory);
        startActivity(intent);
    }
}