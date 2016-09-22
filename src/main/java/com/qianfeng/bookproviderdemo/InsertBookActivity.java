package com.qianfeng.bookproviderdemo;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertBookActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etBookName;
    private EditText etBookAuthor;
    private EditText etBookPrice;
    private Button insertSubmitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_book);
        initView();
        insertSubmitBtn.setOnClickListener(this);
    }

    private void initView() {
        etBookName = (EditText) findViewById(R.id.et_book_name);
        etBookAuthor = (EditText) findViewById(R.id.et_book_author);
        etBookPrice = (EditText) findViewById(R.id.et_book_price);
        insertSubmitBtn = (Button) findViewById(R.id.btn_insert_submit);
    }

    @Override
    public void onClick(View v) {
        String book_name = etBookName.getText().toString();
        String book_author = etBookAuthor.getText().toString();
        float book_price = Float.parseFloat(etBookPrice.getText().toString());
        Uri uri  = Uri.parse("content://com.qianfeng.bookproviderdemo.mycontentprovider");
        ContentValues values = new ContentValues();
        values.put("book_name",book_name);
        values.put("book_author",book_author);
        values.put("book_price",book_price);
        getContentResolver().insert(uri, values);
        Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
        finish();
    }
}
