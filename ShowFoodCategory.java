package com.example.beathunger1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beathunger1.adapter.FoodCategoryRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class ShowFoodCategory extends AppCompatActivity {
    LinearLayoutManager linearLayoutManager;
    Button btnDonorDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showfoodcategory);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        btnDonorDetail = findViewById(R.id.btn_donordetail);

        linearLayoutManager = new LinearLayoutManager(ShowFoodCategory.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<FoodCategory> allFoodCategoryInfor = getAllFoodCategoryInfor();
        FoodCategoryRecyclerViewAdapter foodCategoryRecyclerViewAdapter = new FoodCategoryRecyclerViewAdapter(ShowFoodCategory.this,allFoodCategoryInfor);
        recyclerView.setAdapter(foodCategoryRecyclerViewAdapter);

        btnDonorDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowFoodCategory.this, DonorInformationActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<FoodCategory> getAllFoodCategoryInfor() {
        List<FoodCategory> allFoodCategory = new ArrayList<FoodCategory>();

        allFoodCategory.add(new FoodCategory("Halal", R.drawable.halal, "Halal food is food that is permissible for Muslims to consume as outlined in several verses in the Quran. In Islam, all foods and beverages are halal except alcohol and pork products are NOT permitted including pork lard, ham and bacon"));
        allFoodCategory.add(new FoodCategory("Non-Halal", R.drawable.nonhalal, "Non-halal food is food that is permissible for all people except for Muslims. It can be divided into Chinese foods, Western foods and others.The most common example of non-halal food is pork and alcohols."));
        allFoodCategory.add(new FoodCategory("Vegetarian", R.drawable.vegefood, "Vegetarian cuisine is based on food that meets vegetarian standards by not including meat and animal tissue products. For lacto-ovo vegetarianism, eggs and dairy products are permitted."));

        return allFoodCategory;
    }
}