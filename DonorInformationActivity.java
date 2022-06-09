package com.example.beathunger1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beathunger1.Objects.DonateFood;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;

public class DonorInformationActivity extends AppCompatActivity {
    EditText etName, etPhone, etAddress, etFoodName, etFoodQuantity;
    private Spinner foodCategory;
    private String spinnerItem;
    private Button btnConfirm, btnCancel;
    private int cYear, cMonth, cDay, cHour, cMinute;
    TextView collectionDate, collectionTime;
    RelativeLayout layoutDate, layoutTime;
    private String date, time;
    private ArrayAdapter<String> adapter;

    List<DonateFood> donateFood;
    DatabaseReference databaseDonateFood;

    public DonorInformationActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_information);

        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etAddress = findViewById(R.id.et_address);
        etFoodName = findViewById(R.id.et_foodName);
        etFoodQuantity = findViewById(R.id.et_foodQuantity);
        foodCategory = findViewById(R.id.category);
        btnConfirm = findViewById(R.id.btn_confirm);
        btnCancel = findViewById(R.id.btn_cancel);
        collectionDate = findViewById(R.id.et_date);
        collectionTime = findViewById(R.id.et_time);
        layoutDate = findViewById(R.id.layout_date);
        layoutTime = findViewById(R.id.layout_time);

        final String[] quantites = getResources().getStringArray(R.array.category_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(DonorInformationActivity.this, android.R.layout.simple_spinner_dropdown_item,quantites);
        foodCategory.setAdapter(adapter);

        foodCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerItem = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Calendar c = Calendar.getInstance();
        cYear = c.get(Calendar.YEAR);
        cMonth = c.get(Calendar.MONTH);
        cDay = c.get(Calendar.DAY_OF_MONTH);

        cHour = c.get(Calendar.HOUR);
        cMinute = c.get(Calendar.MINUTE);

         layoutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DonorInformationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                collectionDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, cYear, cMonth, cDay);
                datePickerDialog.show();
            }
        });

       layoutTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(DonorInformationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        time = amPmConverter(hourOfDay);
                        collectionTime.setText(time);
                    }
                },cHour, cMinute, false);
                timePickerDialog.show();
            }

        });

    databaseDonateFood = FirebaseDatabase.getInstance("https://donorinformation-369c8-default-rtdb.firebaseio.com/").getReference("donateFood");

    btnConfirm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(editTextIsEmpty())
                return;
            addFood();
        }
    });

    btnCancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DonorInformationActivity.this);
            alertDialogBuilder.setTitle("Cancel Donation");
            alertDialogBuilder.setMessage("Are you sure you want to cancel your donation?");
            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast(getApplicationContext(), "Action Canceled");
                }
            });
            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(DonorInformationActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            alertDialogBuilder.show();
        }
    });
    }

    public void toast (final Context context, final String text){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean editTextIsEmpty(){
        if(TextUtils.isEmpty(etName.getText().toString())){
            etName.setError("Cannot be Empty");
        }

        if(TextUtils.isEmpty(etPhone.getText().toString())){
            etPhone.setError("Cannot be Empty");
        }

        if(TextUtils.isEmpty(etAddress.getText().toString())){
            etAddress.setError("Cannot be Empty");
        }

        if(TextUtils.isEmpty(etFoodName.getText().toString())){
            etFoodName.setError("Cannot be Empty");
        }

        if(TextUtils.isEmpty(etFoodQuantity.getText().toString())){
            etFoodQuantity.setError("Cannot be Empty");
        }

        //if(TextUtils.isEmpty(collectionDate.getText().toString())){
        //    collectionDate.setError("Cannot be Empty");
        //}

        //if(TextUtils.isEmpty(collectionTime.getText().toString())){
        //    collectionTime.setError("Cannot be Empty");
        //}

        if(TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etPhone.getText().toString()) || TextUtils.isEmpty(etAddress.getText().toString())
                || TextUtils.isEmpty(etFoodName.getText().toString()) || TextUtils.isEmpty(etFoodQuantity.getText().toString())){
            return true;
        }
        else
            return false;
    }

     private void addFood(){
       String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String foodName = etFoodName.getText().toString().trim();
        String quantity = etFoodQuantity.getText().toString().trim();
        String category = spinnerItem;
        String date = collectionDate.getText().toString();
        String time = collectionTime.getText().toString();

        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(phone) || !TextUtils.isEmpty(address)
                || !TextUtils.isEmpty(foodName)|| !TextUtils.isEmpty(quantity)|| !TextUtils.isEmpty(category)
                || !TextUtils.isEmpty(date)|| !TextUtils.isEmpty(time)){

            String donorId = databaseDonateFood.push().getKey();
            DonateFood newFood = new DonateFood(donorId,name,phone,address,foodName,quantity,category,date,time);
            databaseDonateFood.child(donorId).setValue(newFood);
            etName.setText("");
            etPhone.setText("");
            etAddress.setText("");
            etFoodName.setText("");
            etFoodQuantity.setText("");
            collectionDate.setText("");
            collectionTime.setText("");

            Intent intent = new Intent(DonorInformationActivity.this, ThankYouActivity.class);
            startActivity(intent);
            Toast.makeText(DonorInformationActivity.this, "Your donation is successful!", Toast.LENGTH_SHORT).show();
         }

    }
    private String amPmConverter(int hourOfDay) {
        //condition for am & pm
        if(hourOfDay>=0 && hourOfDay<12){
            if (hourOfDay == 0){
                time = 12 + " : " + cMinute + " AM";
            }
            else{
                time = hourOfDay + " : " + cMinute + " AM";
            }
        }
        else {
            if(hourOfDay == 12){
                time = hourOfDay + " : " + cMinute + " PM";
            }
            else{
                hourOfDay = hourOfDay -12;
                time = hourOfDay + " : " + cMinute + " PM";
            }
        }
        return time;
    }



}