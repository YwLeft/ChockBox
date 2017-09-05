package com.example.asus.rikao04;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.asus.rikao04.adapter.Myadapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.asus.rikao04.adapter.Myadapter.getIsSelected;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button_quan)
    Button buttonQuan;
    @BindView(R.id.button_fan)
    Button buttonFan;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.button_conut)
    Button tv_show;// 用于显示选中的条目数量

    private int checkNum; // 记录选中的条目数量


    private List<String> mlist = new ArrayList<>();
    private Myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        for (int i = 0; i < 30; i++) {
            mlist.add("商品" + i);
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        myadapter = new Myadapter(this, mlist);
        recyclerview.setAdapter(myadapter);

        buttonQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // 遍历list的长度，将MyAdapter中的map值全部设为true
                for (int i = 0; i < mlist.size(); i++) {
                    getIsSelected().put(i, true);
                }
                // 数量设为list的长度
                checkNum = mlist.size();
                // 刷新listview和TextView的显示
                dataChanged();
            }
        });

        // 反选按钮的回调接口
        buttonFan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 遍历list的长度，将已选的设为未选，未选的设为已选
                for (int i = 0; i < mlist.size(); i++) {
                    if (getIsSelected().get(i)) {
                        Myadapter.getIsSelected().put(i, false);
                        checkNum--;
                    } else {
                        Myadapter.getIsSelected().put(i, true);
                        checkNum++;
                    }

                }
                // 刷新listview和TextView的显示
                dataChanged();
            }
        });

        myadapter.setFindSelectBox(new Myadapter.FindSelectBox() {
            @Override
            public void findBox(int count) {
                tv_show.setText("已选中" + count + "项");
            }
        });


    }

    private void dataChanged() {
        myadapter.notifyDataSetChanged();
        // TextView显示最新的选中数目
        tv_show.setText("已选中" + checkNum + "项");
    }


}
