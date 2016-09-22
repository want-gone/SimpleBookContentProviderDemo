package com.qianfeng.bookproviderdemo;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateBookActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUBookName;
    private EditText etUBookAuthor;
    private EditText etUBookPrice;
    private Button updateSubmitBtn;
    private String nameOld;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);
        initView();
        Intent intent = getIntent();
        nameOld = intent.getStringExtra("book_name");
        String book_author = intent.getStringExtra("book_author");
        float book_price = intent.getFloatExtra("book_price", 0);
        id = intent.getIntExtra("id",0);
        etUBookName.setText(nameOld);
        etUBookAuthor.setText(book_author);
        etUBookPrice.setText(""+book_price);
        updateSubmitBtn.setOnClickListener(this);

    }

    private void initView() {
        etUBookName = (EditText) findViewById(R.id.et_update_book_name);
        etUBookAuthor = (EditText) findViewById(R.id.et_update_book_author);
        etUBookPrice = (EditText) findViewById(R.id.et_update_book_price);
        updateSubmitBtn = (Button) findViewById(R.id.btn_update_submit);
    }

    @Override
    public void onClick(View v) {
        String name = etUBookName.getText().toString();
        String author = etUBookAuthor.getText().toString();
        Float price = Float.valueOf(etUBookPrice.getText().toString());
        Uri uri  = Uri.parse("content://com.qianfeng.bookproviderdemo.mycontentprovider");

        ContentValues values  = new ContentValues();
        values.put("book_name",name);
        values.put("book_author",author);
        values.put("book_price",price);
//        getContentResolver().update(uri,values,"book_name=?",new String[]{nameOld});
        getContentResolver().update(uri,values,"_id=?",new String[]{""+id});
        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
        finish();
    }
}
