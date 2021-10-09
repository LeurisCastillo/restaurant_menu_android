package com.example.interfacesdecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Locale;

public class FoodListActivity extends AppCompatActivity {

    TextView txtFoodLabel;
    ListView lstFood;
    ImageView imgFoodCategory;

    // Arrays of foods per category
    String[] breakfastFoods = {"Eggs", "Coffee", "Bread", "Salmon", "Juice"};
    String[] mealFoods = {"Rice", "Steak", "Salad", "Fish", "Potatoes"};
    String[] afternoonSnackFoods = {"Candy", "Coffee", "Bread and Jam", "Dark chocolate", "Cheese"};
    String[] dinnerFoods = {"Macaroni and Cheese", "Chicken", "Lasagna", "Soup", "Mangu"};
    String[] dessertFoods = {"Cake", "Cupcake", "Tiramisu", "Apple cinnamon custard cake", "Ice cream"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        lstFood = findViewById(R.id.lst_foods);
        imgFoodCategory = findViewById(R.id.img_food_category);
        txtFoodLabel = findViewById(R.id.txt_lst_food_label);

        Intent intent = getIntent();
        String category = intent.getStringExtra("Food category");

        setCategoryImageInformation(category);
        showFoodsForCategory(category);

        lstFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sendSelectedFood(lstFood.getItemAtPosition(i).toString());
            }
        });
    }

    public void showFoodsForCategory(String category) {

        switch (category) {

            case "Breakfast":
                txtFoodLabel.append(" for breakfast");
                setLstFoods(breakfastFoods);
                break;

            case "Meal":
                txtFoodLabel.append(" for meal");
                setLstFoods(mealFoods);
                break;

            case "Afternoon snack":
                txtFoodLabel.append(" for afternoon snack");
                setLstFoods(afternoonSnackFoods);
                break;

            case "Dinner":
                txtFoodLabel.append(" for dinner");
                setLstFoods(dinnerFoods);
                break;

            case "Dessert":
                txtFoodLabel.append(" for dessert");
                setLstFoods(dessertFoods);
                break;

        }
    }

    public void setLstFoods(String[] foods ) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foods);
        lstFood.setAdapter(adapter);
    }

    public void sendSelectedFood(String selectedFood) {
        Intent intent = new Intent(this, SelectedFoodActivity.class);
        intent.putExtra("Selected food", selectedFood);
        startActivity(intent);
    }

    public void setCategoryImageInformation(String category) {

        String formatFoodName = category.replaceAll(" ", "_").toLowerCase(Locale.ROOT);
        int drawableImageId = getResources().getIdentifier(formatFoodName, "drawable", getPackageName());
        imgFoodCategory.setImageResource(drawableImageId);
    }
}