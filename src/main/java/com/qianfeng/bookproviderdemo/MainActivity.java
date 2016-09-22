package com.qianfeng.bookproviderdemo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button insertBtn;
    private ListView lvBook;
    private MyCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertBtn = (Button) findViewById(R.id.btn_insert);
        lvBook = (ListView) findViewById(R.id.lv_book_info);
        insertBtn.setOnClickListener(this);
        Uri uri  = Uri.parse("content://com.qianfeng.bookproviderdemo.mycontentprovider");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        adapter = new MyCursorAdapter(this,cursor,true);
        lvBook.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
                Intent intent = new Intent(this,InsertBookActivity.class);
                startActivity(intent);
    }

    class MyCursorAdapter extends CursorAdapter{

        public MyCursorAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.listview_item_book, null);
            ViewHolder holder = new ViewHolder(view);
            view.setTag(holder);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            ViewHolder holder = (ViewHolder) view.getTag();
            final int id = cursor.getInt(cursor.getColumnIndex("_id"));
            final String name = cursor.getString(cursor.getColumnIndex("book_name"));
            final String author = cursor.getString(cursor.getColumnIndex("book_author"));
            final float price = cursor.getFloat(cursor.getColumnIndex("book_price"));
            holder.tvBookName.setText(name);
            holder.tvBookAuthor.setText(author);
            holder.tvBookPrice.setText(""+price);
            holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri  = Uri.parse("content://com.qianfeng.bookproviderdemo.mycontentprovider");
//                    getContentResolver().delete(uri,"book_name=?",new String[]{name});
                    getContentResolver().delete(uri,"_id=?",new String[]{""+id});
                    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                    adapter.changeCursor(cursor);
                }
            });
            holder.updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,UpdateBookActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("book_name",name);
                    intent.putExtra("book_author",author);
                    intent.putExtra("book_price",price);
                    startActivity(intent);
                }
            });
        }
        class ViewHolder{
            public TextView tvBookName;
            public TextView tvBookAuthor;
            public TextView tvBookPrice;
            public Button deleteBtn;
            public Button updateBtn;
            public ViewHolder(View view) {
                tvBookName = (TextView) view.findViewById(R.id.tv_book_name);
                tvBookAuthor = (TextView) view.findViewById(R.id.tv_book_author);
                tvBookPrice = (TextView) view.findViewById(R.id.tv_book_price);
                deleteBtn = (Button) view.findViewById(R.id.btn_delete);
                updateBtn = (Button) view.findViewById(R.id.btn_update);
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Uri uri  = Uri.parse("content://com.qianfeng.bookproviderdemo.mycontentprovider");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        adapter.changeCursor(cursor);
    }
}
