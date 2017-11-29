package com.zly.www.simple.customdecoration;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.zly.www.easyrecyclerview.EasyRecyclerView;
import com.zly.www.easyrecyclerview.decoration.StickItemDecoration;
import com.zly.www.easyrecyclerview.listener.OnLoadListener;
import com.zly.www.easyrecyclerview.listener.OnRefreshListener;
import com.zly.www.easyrecyclerview.ptrlib.header.MaterialHeader;
import com.zly.www.simple.DisplayUtil;
import com.zly.www.simple.R;
import com.zly.www.simple.adapter.CustomAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 条目吸附demo
 * Created by zly on 2016/11/28 0028.
 */

public class CustomDecorationActivity extends AppCompatActivity  implements OnRefreshListener, OnLoadListener {

    @BindView(R.id.erv)
    EasyRecyclerView erv;

    private CustomDecorationAdapter mAdapter;
    private Handler handler = new Handler();
    private boolean isFail = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_stick);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        MaterialHeader materialHeader = new MaterialHeader(this);
        materialHeader.setPadding(0, DisplayUtil.dip2px(20),0,DisplayUtil.dip2px(20));
        erv.setHeaderView(materialHeader);
        erv.setPinContent(true);
//        erv.setAdapter(mAdapter = new CustomAdapter());
        erv.setLastUpdateTimeRelateObject(this);
        erv.setOnRefreshListener(this);
        erv.setOnLoadListener(this);

        erv.setLayoutManager(new LinearLayoutManager(this));
        erv.setAdapter(mAdapter = new CustomDecorationAdapter());
        erv.addItemDecoration(new StickItemDecoration(this,mAdapter.getData()) {
            @Override
            public String getTag(int position) {
                return mAdapter.getItem(position).tag;
            }

        });
    }
    
    private void initData() {
        mAdapter.add(new CityBean("a","安庆"));
        mAdapter.add(new CityBean("a","安徽"));
        mAdapter.add(new CityBean("a","鞍山"));
        mAdapter.add(new CityBean("a","澳门"));
        mAdapter.add(new CityBean("b","白城"));
        mAdapter.add(new CityBean("b","白沙"));
        mAdapter.add(new CityBean("b","北海"));
        mAdapter.add(new CityBean("b","北京"));
        mAdapter.add(new CityBean("b","保定"));
        mAdapter.add(new CityBean("b","宝鸡"));
        mAdapter.add(new CityBean("c","长沙"));
        mAdapter.add(new CityBean("c","成都"));
        mAdapter.add(new CityBean("c","常德"));
        mAdapter.add(new CityBean("d","大理"));
        mAdapter.add(new CityBean("d","大连"));
        mAdapter.add(new CityBean("d","大庆"));
        mAdapter.add(new CityBean("e","鄂州"));
        mAdapter.add(new CityBean("h","哈尔滨"));
        mAdapter.add(new CityBean("w","万宁"));
        mAdapter.add(new CityBean("w","潍坊"));
        mAdapter.add(new CityBean("w","威海"));
    }

    @Override
    public void onLoadListener() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    mAdapter.insert(new CityBean("d","onLoadListener"),0);
                }
            }
        }, 3000);
        Toast.makeText(this, "onLoadListener", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefreshListener() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (mAdapter.getItemCount() > 20 && !isFail) {
//                    isFail = true;
//                    erv.loadFail();
//                } else if (mAdapter.getItemCount() > 30) {
//                    erv.noMore();
//                } else {
//                    for (int i = 0; i < 10; i++) {
//                        mAdapter.add(new CityBean("xxx","xixixixixixx"));
//                    }
//                }

                for (int i = 0; i < 10; i++) {
                    mAdapter.add(new CityBean("xxx","xixixixixixx"));
                }


            }
        }, 3000);
        Toast.makeText(this, "onRefreshListener", Toast.LENGTH_SHORT).show();
    }
}
