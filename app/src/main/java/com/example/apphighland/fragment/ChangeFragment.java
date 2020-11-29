package com.example.apphighland.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.apphighland.Database;
import com.example.apphighland.GioHangActivity;
import com.example.apphighland.MainActivity;
import com.example.apphighland.R;
import com.example.apphighland.adapter.ProductAdapter;
import com.example.apphighland.model.Product;

import java.util.ArrayList;


public class ChangeFragment extends Fragment {
    String where="";
    ListView listView;
    ArrayList<Product> arrayList;
    ProductAdapter adapter;

    public ChangeFragment(String where) {
        this.where = where;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_layout, container, false);
        listView = view.findViewById(R.id.listview_product);

        arrayList = new ArrayList<>();
        Cursor cursor= MainActivity.database.rawQuery("Select *  from Products "+where+";",null);
        while (cursor.moveToNext())
        {
            byte[] hinhAnh = cursor.getBlob(5);
            Product product =new Product(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),hinhAnh);
            arrayList.add(product);
        }
        adapter = new ProductAdapter(getActivity(),R.layout.listview_row_product,arrayList,R.layout.listview_row_product);
        listView.setAdapter(adapter);
        return view;

    }
}