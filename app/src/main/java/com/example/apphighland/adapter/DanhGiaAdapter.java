package com.example.apphighland.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apphighland.R;
import com.example.apphighland.model.DanhGia;
import com.example.apphighland.model.Product;

import java.util.ArrayList;

public class DanhGiaAdapter extends ArrayAdapter<DanhGia> {
    Context context;
    int resource;
    ArrayList<DanhGia> arrayList;
    public DanhGiaAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DanhGia> objects) {
        super(context, resource, objects);
        this.arrayList= objects;
        this.context=context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    convertView = LayoutInflater.from(context).inflate(R.layout.danhgia_row_product,parent,false);
        TextView account = convertView.findViewById(R.id.taikhoan);
        TextView noidung = convertView.findViewById(R.id.noidung);
        TextView sao = convertView.findViewById(R.id.sao);
        TextView ngayDanhGia = convertView.findViewById(R.id.ngaydanhgia);
        DanhGia danhGia= arrayList.get(position);
        account.setText(danhGia.getAccount());
        noidung.setText(danhGia.getNoidung());
        ngayDanhGia.setText(danhGia.getNgay());
        sao.setText(danhGia.getSao()+"");
        return convertView;
    }
}
