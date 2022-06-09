package com.example.beathunger1;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FoodDetails extends AppCompatActivity implements View.OnClickListener{
    DatabaseReference databaseDonation= FirebaseDatabase.getInstance("https://donorinformation-369c8-default-rtdb.firebaseio.com/").getReference("donateFood");
    ImageButton imgbtnCall;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        Intent intent = getIntent();

        imgbtnCall = findViewById(R.id.img_btn_call);
        //TextView tvdonorId = findVwById(R.id.tv_food);
        TextView tv_name = findViewById(R.id.tv_name);
        TextView tv_address = findViewById(R.id.tv_address);
        TextView tv_foodname = findViewById(R.id.tv_food);
        TextView tv_foodquantity = findViewById(R.id.tv_quantity);
        TextView tv_foodcategory = findViewById(R.id.tv_category);
        TextView tv_date = findViewById(R.id.tv_date);
        TextView tv_time = findViewById(R.id.tv_time);
        btnConfirm = findViewById(R.id.btn_confirm);
        //TextView tv_phone = findViewById(R.id.tv_food);

        //tv_foodname.setText(intent.getStringExtra("Food"));
        tv_name.setText(intent.getStringExtra("name"));
        tv_address.setText(intent.getStringExtra("address"));
        tv_foodname.setText(intent.getStringExtra("food"));
        tv_foodquantity.setText(intent.getStringExtra("quantity"));
        tv_foodcategory.setText(intent.getStringExtra("category"));
        tv_date.setText(intent.getStringExtra("date"));
        tv_time.setText(intent.getStringExtra("time"));
        //tv_foodname.setText(intent.getStringExtra("Food"));

        imgbtnCall.setOnClickListener(this);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            String foodid = (String)b.get("donorId");
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(FoodDetails.this);
                alert.setTitle("Confirm Booking");
                alert.setMessage("Are you sure to book this food?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("donateFood").child(foodid);
                        dR.removeValue();
                        Toast.makeText(getApplicationContext(),"Food booked successfully",Toast.LENGTH_LONG).show();
                        Intent back = new Intent(FoodDetails.this, Donation.class);
                        startActivity(back);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                alert.show();
            }
        });

        }
    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        Intent callintent = new Intent (Intent.ACTION_DIAL);
        callintent.setData(Uri.parse("tel:" + intent.getStringExtra("phone")));
        startActivity(callintent);

        if(callintent.resolveActivity(getPackageManager())!=null){
            startActivity(callintent);
        }
        else
        {
            Toast.makeText(FoodDetails.this,"Sorry no app can handle this action and data",Toast.LENGTH_SHORT).show();
        }
    }
}
