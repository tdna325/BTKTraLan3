package com.example.apphighland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphighland.adapter.GioHangAdapter;
import com.example.apphighland.model.GioHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GioHangActivity extends AppCompatActivity {
    public static TextView textView;
    public static TextView tongTien;
    BottomNavigationView bottomNavigationView;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        listView = findViewById(R.id.lv_gioHang);
        textView = findViewById(R.id.khongsp);
        tongTien =findViewById(R.id.tongtien);
        bottomNavigationView = findViewById(R.id.bottom_menu);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.ic_home:
                        intent =new Intent(GioHangActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                        break;
                    case R.id.ic_giohang:
                        break;
                    case R.id.ic_me:
                        intent =new Intent(GioHangActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
                        break;
                }
                return false;
            }
        });
        //tiep tuc....

        if(MainActivity.hangArrayList.size()>0) {
            textView.setText("");
            MainActivity.gioHangAdapter = new GioHangAdapter(this, R.layout.giohang_row_product, MainActivity.hangArrayList);
            listView.setAdapter(MainActivity.gioHangAdapter);
            MainActivity.gioHangAdapter.notifyDataSetChanged();
            TinhTongTien();
        }
        else {
            textView.setText("Chưa có sản phẩm nào!");
            tongTien.setText("0 VND");
        }



    }
    public static void TinhTongTien() {
        int thanhtien=0;
        for (int i=0;i<MainActivity.hangArrayList.size();i++)
        {
            thanhtien = thanhtien + MainActivity.hangArrayList.get(i).getTongTien();
        }
        tongTien.setText(thanhtien+",000 VND");
    }
}