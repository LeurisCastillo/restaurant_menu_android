package com.example.interfacesdecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class SelectedFoodActivity extends AppCompatActivity {

    TextView txtFoodName, txtReceipt, txtFoodDescription;
    Button btnShare, btnOk;
    ImageView imgFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_food);

        // Getting resources from intent
        Intent intent = getIntent();
        String selectedFood = intent.getStringExtra("Selected food");

        // Ids
        txtFoodDescription = findViewById(R.id.txt_food_description);
        txtReceipt = findViewById(R.id.txt_food_receipt);
        txtFoodName = findViewById(R.id.txt_food_name);
        btnShare = findViewById(R.id.btn_share);
        btnOk = findViewById(R.id.btn_ok);
        imgFood = findViewById(R.id.img_food);

        // Giving data to the layout
        setFoodInformation(selectedFood);

        // BtnShare on click listener
        btnShare.setOnClickListener(view -> {
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_STREAM, txtReceipt.getText().toString());
            startActivity(Intent.createChooser(share, "Share via:"));
        });

        btnOk.setOnClickListener(view -> {
            finish();
        });
    }

    public void setFoodInformation(String food) {

        txtFoodName.setText(food);
        String formatFoodName = food.replaceAll(" ", "_").toLowerCase(Locale.ROOT);
        int drawableImageId = getResources().getIdentifier(formatFoodName, "drawable", getPackageName());
        imgFood.setImageResource(drawableImageId);
        txtFoodDescription.setText(R.string.example_description_and_receipt_text);
        txtReceipt.setText(R.string.example_description_and_receipt_text);
    }
}