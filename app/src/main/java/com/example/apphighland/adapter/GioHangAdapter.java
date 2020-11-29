package com.example.apphighland.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.apphighland.GioHangActivity;
import com.example.apphighland.MainActivity;
import com.example.apphighland.R;
import com.example.apphighland.model.GioHang;
import com.example.apphighland.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends ArrayAdapter<GioHang> {
    Context context;
    int resource;
    ArrayList<GioHang> arrayList;
    public GioHangAdapter(@NonNull Context context, int resource, @NonNull ArrayList<GioHang> objects) {
        super(context, resource, objects);
        this.arrayList = objects;
        this.context=context;
        this.resource =resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.giohang_row_product,parent,false);
        ImageView img_lv = convertView.findViewById(R.id.img_product);
        TextView name_lv = convertView.findViewById(R.id.lv_name_product);
        TextView price_lv = convertView.findViewById(R.id.price_product);
        EditText sl = convertView.findViewById(R.id.soluong);
        GioHang gioHang = arrayList.get(position);
        byte[] hinhAnh = gioHang.getImage();
        sl.setText(gioHang.getSL()+"");
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        img_lv.setImageBitmap(bitmap);
        name_lv.setText(gioHang.getName());
        price_lv.setText(gioHang.getPrice()+",000 VND");
        TextView thanhTien = convertView.findViewById(R.id.thanhTien);
        thanhTien.setText(gioHang.getTongTien()+",000 VND");
        ImageButton add = convertView.findViewById(R.id.add_product);
        ImageButton minus = convertView.findViewById(R.id.minus_product);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gioHang.getSL()>=10)
                {
                gioHang.setSL(10);
                }
                else
                {
                    gioHang.setSL((gioHang.getSL()+1));
                    MainActivity.gioHangAdapter.notifyDataSetChanged();
                    GioHangActivity.TinhTongTien();
                }
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gioHang.getSL()<=0)
                {
                    gioHang.setSL(0);
                }
                else {
                    gioHang.setSL((gioHang.getSL()-1));
                    MainActivity.gioHangAdapter.notifyDataSetChanged();
                    GioHangActivity.TinhTongTien();
                }
            }
        });
        LinearLayout linearLayout = convertView.findViewById(R.id.test);
        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                    XacNhanXoa(gioHang);
                return true;
            }
        });
        return convertView;
    }
    public void XacNhanXoa(GioHang gioHang){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông Báo!");
        builder.setMessage("Bạn có chắc là muốn xóa sản phẩm này không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.hangArrayList.remove(gioHang);
                MainActivity.gioHangAdapter.notifyDataSetChanged();
                Toast.makeText(context,"Đã xóa",Toast.LENGTH_SHORT).show();
                GioHangActivity.TinhTongTien();
                 if (MainActivity.hangArrayList.size()==0)
                 {
                    GioHangActivity.textView.setText("Chưa có sản phẩm nào!");
                 }
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"Chưa xóa",Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

}
