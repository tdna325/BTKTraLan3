package com.example.apphighland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.apphighland.adapter.CustomAdapter;
import com.example.apphighland.model.RowModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    ListView listView, listView1;
    Context context;
    ArrayList<RowModel> rowData;
    CustomAdapter customAdapter;
    RowModel rowModel;
    LinearLayout scrollView;
    Button button_dangnhap;
    TextView thongbao;
    TextView tenUser;
    int[] img = {R.drawable.voucher,R.drawable.coins,R.drawable.wallet,R.drawable.person,R.drawable.address,R.drawable.help,R.drawable.shop,R.drawable.gear,R.drawable.information};
    String[] a = {"Ví Voucher", "Shopee Xu" , "Thanh Toán", "Mời bạn bè" , "Địa chỉ", "Trung tâm Trợ giúp",
            "Ứng dụng chủ quán", "Cài đặt" , "Về Now"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        context = this;
        button_dangnhap =findViewById(R.id.bt_logout);
        scrollView= findViewById(R.id.siguped);
        thongbao =findViewById(R.id.chuadangnhap);
        tenUser =findViewById(R.id.username);
        if (MainActivity.UserName=="")
        {
            scrollView.setVisibility(View.GONE);
            button_dangnhap.setText("Đăng Nhập");
            thongbao.setVisibility(View.VISIBLE);

        }
        else {
            tenUser.setText(MainActivity.TenUser);
            scrollView.setVisibility(View.VISIBLE);
            button_dangnhap.setText("Đăng Xuất");
            thongbao.setVisibility(View.GONE);
        }
        button_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.UserName=="")
                {
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
                            Cursor account = MainActivity.database.rawQuery("Select * from Account where Username ='" + et_user.getText().toString().toUpperCase() + "' AND Password='" + et_pass.getText().toString() + "'", null);
                            account.getCount();
                            if (account.getCount() > 0) {
                                account.moveToFirst();
                                MainActivity.UserName=account.getString(0);
                                MainActivity.TenUser = account.getString(2);
                                dialog.cancel();
                                scrollView.setVisibility(View.VISIBLE);
                                button_dangnhap.setText("Đăng Xuất");
                                tenUser.setText(MainActivity.TenUser);
                                thongbao.setVisibility(View.GONE);
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
                else {
                    MainActivity.UserName="";
                    MainActivity.TenUser="";
                    restartActivity();
                }

            }

        });

        listView = findViewById(R.id.listView);
        rowData = new ArrayList<>();
        addRowData();
        customAdapter = new CustomAdapter(context, rowData);
        listView.setAdapter(customAdapter);

        listView1 = findViewById(R.id.listView1);
        rowData = new ArrayList<>();
        addRowData1();
        customAdapter = new CustomAdapter(context, rowData);
        listView1.setAdapter(customAdapter);
        //bottom menu
        bottomNavigationView = findViewById(R.id.bottom_menu);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.ic_home:
                        intent =new Intent(ProfileActivity.this,MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                        break;
                    case R.id.ic_giohang:
                        intent =new Intent(ProfileActivity.this,GioHangActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                        break;
                    case R.id.ic_me:

                        break;
                }
                return false;
            }
        });
    }

    private void addRowData() {
        for (int i = 0 ; i<= a.length-6; i++) {
            rowModel = new RowModel(img[i],a[i]);
            rowData.add(rowModel);
        }

    }
    private void addRowData1() {
        for (int i = 4 ; i<= a.length-1; i++) {
            rowModel = new RowModel(img[i],a[i]);
            rowData.add(rowModel);
        }

    }
    public void restartActivity(){
        Intent mIntent = getIntent();
        finish();
        startActivity(mIntent);
    }
}