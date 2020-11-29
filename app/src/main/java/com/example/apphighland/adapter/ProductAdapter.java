package com.example.apphighland.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apphighland.DetailProductActivity;
import com.example.apphighland.MainActivity;
import com.example.apphighland.R;
import com.example.apphighland.model.GioHang;
import com.example.apphighland.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    Context context;
    int resource;
    ArrayList<Product> arrayList;
    int layout;
    public ProductAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> objects,int layout) {
        super(context, resource, objects);
        this.context =context;
        this.resource =resource;
        this.arrayList =objects;
        this.layout=layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(layout,parent,false);
        ImageView img_lv = convertView.findViewById(R.id.img_product);
        TextView name_lv = convertView.findViewById(R.id.lv_name_product);
        TextView price_lv = convertView.findViewById(R.id.price_product);
        Product  product = arrayList.get(position);
        byte[] hinhAnh = product.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        img_lv.setImageBitmap(bitmap);
        name_lv.setText(product.getpName());
        price_lv.setText(product.getPrice()+",000 VND");
        ImageButton button =convertView.findViewById(R.id.add_product);
        LinearLayout linearLayout= convertView.findViewById(R.id.product_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.putExtra("ID",product.getID());
                context.startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kt=0;
                if (MainActivity.hangArrayList.size()>0)
                {
                    for (int i=0;i<MainActivity.hangArrayList.size();i++)
                    {
                        if(MainActivity.hangArrayList.get(i).getID()==product.getID())
                        {
                            MainActivity.hangArrayList.get(i).setSL(MainActivity.hangArrayList.get(i).getSL()+1);
                            Toast.makeText(context,"Đã thêm một "+product.getpName() +" vào trong giỏ hàng",Toast.LENGTH_SHORT).show();
                            kt = 1;
                        }
                    }
                    if(kt!=1)
                    {

                        GioHang gioHang = new GioHang(product.getID(),product.getpName(),product.getPrice(),product.getImg(),1);
                        MainActivity.hangArrayList.add(gioHang);
                        Toast.makeText(context,"Đã thêm một "+product.getpName() +" vào trong giỏ hàng",Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    GioHang gioHang = new GioHang(product.getID(),product.getpName(),product.getPrice(),product.getImg(),1);
                    MainActivity.hangArrayList.add(gioHang);
                    Toast.makeText(context,"Đã thêm một "+product.getpName() +" vào trong giỏ hàng",Toast.LENGTH_SHORT).show();

                }


            }
        });
        return convertView;
    }
}
