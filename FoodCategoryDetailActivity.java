package com.example.beathunger1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FoodCategoryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category_detail);

        Intent intent = getIntent();
        TextView tvTitle = findViewById(R.id.tv_foodcategory_name);
        TextView tvDescription = findViewById(R.id.tv_foodcategory_desciption);
        tvTitle.setText(intent.getStringExtra("foodCategoryName"));
        tvDescription.setText(intent.getStringExtra("foodCategoryDetail"));

    }
}