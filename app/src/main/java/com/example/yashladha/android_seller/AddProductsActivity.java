package com.example.yashladha.android_seller;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yashladha.android_seller.fragments.DisplayFrag;

public class AddProductsActivity extends AppCompatActivity {

    ImageView ivAdd;
    TextView tvAddPhoto,tvProductName,tvProDes,tvNewPrice,tvOriginalPrice,tvDiscount,tvExchange;
    EditText etProductName,etProDes,etNewPrice,etOriginalPrice,etDiscount,etExchange;
    Button btDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        ivAdd = (ImageView) findViewById(R.id.ivAdd);

        tvAddPhoto = (TextView)findViewById(R.id.tvAddPhoto);
        tvProductName = (TextView)findViewById(R.id.tvProductName);
        tvProDes = (TextView)findViewById(R.id.tvProDes);
        tvNewPrice = (TextView)findViewById(R.id.tvNewPrice);
        tvOriginalPrice = (TextView)findViewById(R.id.tvOriginalPrice);
        tvDiscount = (TextView)findViewById(R.id.tvDiscount);
        tvExchange = (TextView)findViewById(R.id.tvExchange);

        etProductName = (EditText)findViewById(R.id.etProductName);
        etProDes = (EditText)findViewById(R.id.etProDes);
        etNewPrice = (EditText)findViewById(R.id.etNewPrice);
        etOriginalPrice = (EditText)findViewById(R.id.etOriginalPrice);
        etDiscount = (EditText)findViewById(R.id.etDiscount);
        etExchange = (EditText)findViewById(R.id.etExchange);


        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,0);
            }
        });

        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProductsActivity.this, DisplayFrag.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.ivAdd);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}