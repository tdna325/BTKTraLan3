package com.example.apphighland;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Insets;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphighland.adapter.DanhGiaAdapter;
import com.example.apphighland.model.DanhGia;
import com.example.apphighland.model.GioHang;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DetailProductActivity extends AppCompatActivity {
    TextView textView_name;
    TextView textView_Price;
    TextView textView_sao;
    ImageView imageView;
    int ID_Product;
    ImageButton button;
    ImageView imageView_back;
    ListView listView;
    DanhGiaAdapter adapter;
    ArrayList<DanhGia> arrayList;
    Button button_xemthem;
    Button button_danhgia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        listView = findViewById(R.id.danhgia_lv);
        Intent intent = new Intent(getIntent());
        button = findViewById(R.id.add_product);
        textView_sao = findViewById(R.id.sao_avg);
        imageView_back = findViewById(R.id.igchitietmilkback);
        ID_Product=intent.getIntExtra("ID",0);
        Cursor cursor= MainActivity.database.rawQuery("Select *  from Products where ID="+ID_Product +";",null);
        cursor.moveToFirst();
        textView_name = findViewById(R.id.tv_product);
        button_danhgia = findViewById(R.id.danhgiaproduct);
        textView_Price = findViewById(R.id.tv_gia);
        imageView = findViewById(R.id.image_poster);
        textView_name.setText(cursor.getString(1));
        textView_Price.setText(cursor.getInt(3)+",000 VND");
        byte[] hinhAnh = cursor.getBlob(5);
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        imageView.setImageBitmap(bitmap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kt=0;
                if (MainActivity.hangArrayList.size()>0)
                {
                    for (int i=0;i<MainActivity.hangArrayList.size();i++)
                    {
                        if(MainActivity.hangArrayList.get(i).getID()==ID_Product)
                        {
                            MainActivity.hangArrayList.get(i).setSL(MainActivity.hangArrayList.get(i).getSL()+1);
                            Toast.makeText(DetailProductActivity.this,"Đã thêm một "+ cursor.getString(1) +" vào trong giỏ hàng",Toast.LENGTH_SHORT).show();
                            kt = 1;
                        }
                    }
                    if(kt!=1)
                    {

                        GioHang gioHang = new GioHang(ID_Product,cursor.getString(1),cursor.getInt(3),cursor.getBlob(5),1);
                        MainActivity.hangArrayList.add(gioHang);
                        Toast.makeText(DetailProductActivity.this,"Đã thêm một "+cursor.getString(1) +" vào trong giỏ hàng",Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    GioHang gioHang = new GioHang(ID_Product,cursor.getString(1),cursor.getInt(3),cursor.getBlob(5),1);
                    MainActivity.hangArrayList.add(gioHang);
                    Toast.makeText(DetailProductActivity.this,"Đã thêm một "+cursor.getString(1) +" vào trong giỏ hàng",Toast.LENGTH_SHORT).show();

                }


            }
        });
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       AnhXa("Select *  from BinhLuan where ID_Product="+ID_Product+" LIMIT 5;");
        button_xemthem = findViewById(R.id.btn_xemthem);
        if(arrayList.size()<5)
        {
            button_xemthem.setVisibility(View.GONE);
        }
        else {
            button_xemthem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AnhXa("Select *  from BinhLuan where ID_Product="+ID_Product+";");
                    button_xemthem.setVisibility(View.GONE);
                }
            });
        }
        button_danhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.UserName=="")
                {
                    AlertDialog.Builder builder_dangnhap = new AlertDialog.Builder(DetailProductActivity.this);
                    builder_dangnhap.setTitle("Thông báo! ");
                    builder_dangnhap.setMessage("Vui lòng đăng nhập để có thể bình luận");
                    builder_dangnhap.setPositiveButton("Đăng Nhập", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.DangNhap(DetailProductActivity.this);
                        }
                    });
                    builder_dangnhap.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder_dangnhap.show();
                }
                else {
                    DialogDanhGia();
                }

            }
        });



    }

    private void DialogDanhGia() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.binhluan_danhgia_product);
        dialog.setTitle("Thêm bình luận, đánh giá");
        EditText et_sao = dialog.findViewById(R.id.getsao);
        EditText et_noidung= dialog.findViewById(R.id.getcmt);
        Button bt_submit = dialog.findViewById(R.id.btn_submit);
        Button bt_cancel =dialog.findViewById(R.id.btn_cancel);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                ContentValues contentValues =new ContentValues();
                contentValues.put("ID_Product",ID_Product);
                contentValues.put("Account",MainActivity.TenUser);
                contentValues.put("BinhLuan",et_noidung.getText().toString());
                contentValues.put("Sao",et_sao.getText().toString());
                //thu

                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = df.format(c);

                contentValues.put("NgayDanhGia", formattedDate);
                MainActivity.database.insert("BinhLuan",null,contentValues);
                AnhXa("Select *  from BinhLuan where ID_Product="+ID_Product+";");
                button_xemthem.setVisibility(View.GONE);
                dialog.cancel();
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void AnhXa(String sql){
        arrayList = new ArrayList<>();
        Cursor cursor2 = MainActivity.database.rawQuery(sql,null);
        while (cursor2.moveToNext())
        {

            DanhGia danhGia = new DanhGia(cursor2.getInt(0),cursor2.getInt(1),cursor2.getString(2),cursor2.getString(3),cursor2.getInt(4),cursor2.getString(5));
            arrayList.add(danhGia);
        }
        adapter= new DanhGiaAdapter(this,R.layout.danhgia_row_product,arrayList);
        listView.setAdapter(adapter);
        Cursor cursor3 = MainActivity.database.rawQuery("Select avg(Sao)  from BinhLuan where ID_Product="+ID_Product+";",null);
        cursor3.moveToFirst();
        DecimalFormat df = new DecimalFormat("#.#");
        textView_sao.setText(df.format(cursor3.getDouble(0))+"");
    }
}