package com.example.apphighland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphighland.adapter.GioHangAdapter;
import com.example.apphighland.adapter.ViewPagerAdapter;
import com.example.apphighland.fragment.ChangeFragment;
import com.example.apphighland.fragment.GridViewFragment;
import com.example.apphighland.fragment.ListViewFragment;
import com.example.apphighland.model.GioHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button bt_lv;
    Switch radioButton;
    public static String UserName = "";
    public static String TenUser = "";
    String DATABASE_NAME = "Product.db";
    public static SQLiteDatabase database;
    public static String string= "";
    Fragment fragment;
    public static ArrayList<GioHang> hangArrayList = new ArrayList<>();
    public static GioHangAdapter gioHangAdapter;
    TextView timkiem;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Database.initDatabase(this,DATABASE_NAME);


        radioButton = findViewById(R.id.lv_gr);
        timkiem = findViewById(R.id.search_btn);
        search =findViewById(R.id.search);
        timkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tukhoa = search.getText().toString();
                String sql = "where Name like '%"+tukhoa+"%'";
                fragment = new ChangeFragment(sql);
                getSupportFragmentManager().beginTransaction().replace(R.id.product,fragment).commit();
            }
        });
        if(radioButton.isChecked()==false)
        {
            fragment = new ListViewFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.product,fragment).commit();
        }
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==false)
                {
                    fragment = new ListViewFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.product,fragment).commit();
                }
                else
                {
                    fragment = new GridViewFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.product,fragment).commit();
                }
            }
        });


        bottomNavigationView = findViewById(R.id.bottom_menu);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.ic_home:

                        break;
                    case R.id.ic_giohang:
                        intent =new Intent(MainActivity.this, GioHangActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
                        break;
                    case R.id.ic_me:
                        intent =new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.silde_out_to_left);
                        break;
                }
                return false;
            }
        });
    }

    public static  void DangNhap(Context context){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dangnhap_dialog);
        dialog.setTitle("Thêm bình luận, đánh giá");
        EditText et_user = dialog.findViewById(R.id.user);
        EditText et_pass= dialog.findViewById(R.id.pass);
        Button bt_submit = dialog.findViewById(R.id.dangnhap);
        Button bt_cancel =dialog.findViewById(R.id.huy);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor account = database.rawQuery("Select * from Account where Username ='" + et_user.getText().toString().toUpperCase() + "' AND Password='" + et_pass.getText().toString() + "'", null);
                account.getCount();
                if (account.getCount() > 0) {
                    account.moveToFirst();
                MainActivity.UserName=account.getString(0);
                MainActivity.TenUser = account.getString(2);
                dialog.cancel();
                } else {
                    AlertDialog.Builder builder_dangnhap = new AlertDialog.Builder(context);
                    builder_dangnhap.setTitle("Thông báo! ");
                    builder_dangnhap.setMessage("Sai Tài Khoản hoặc Mật Khẩu");
                    builder_dangnhap.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder_dangnhap.show();
                }
            }
        });
        dialog.show();
    }
}