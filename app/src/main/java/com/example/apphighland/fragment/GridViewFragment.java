package com.example.apphighland.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.apphighland.MainActivity;
import com.example.apphighland.R;
import com.example.apphighland.adapter.ProductAdapter;
import com.example.apphighland.adapter.ViewPagerAdapter;
import com.example.apphighland.model.Product;

import java.util.ArrayList;


public class GridViewFragment extends Fragment {
    String where="";
    ArrayList<Product> arrayList;
    ProductAdapter adapter;
    GridView gridView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gridview, container, false);
        gridView = view.findViewById(R.id.GridView_Product);
        arrayList = new ArrayList<>();
        Cursor cursor= MainActivity.database.rawQuery("Select *  from Products "+where+";",null);
        while (cursor.moveToNext())
        {
            byte[] hinhAnh = cursor.getBlob(5);
            Product product =new Product(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),hinhAnh);
            arrayList.add(product);
        }
        adapter = new ProductAdapter(getActivity(),R.layout.gridview_row_product,arrayList,R.layout.gridview_row_product);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}