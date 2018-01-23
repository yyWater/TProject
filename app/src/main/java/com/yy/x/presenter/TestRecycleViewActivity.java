package com.yy.x.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yy.x.R;
import com.yy.x.model.Type1ItemBean;
import com.yy.x.view.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/1/23 18:58 <br/>
 */

public class TestRecycleViewActivity extends Activity {
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_gridview_test);

        gridLayoutManager = new GridLayoutManager(this, 3);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_switch);
        recyclerView.setLayoutManager(gridLayoutManager);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getAllItemList(), this);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    private List<Type1ItemBean> getAllItemList() {

        List<Type1ItemBean> list = new ArrayList<>();
        list.add(new Type1ItemBean("空调"));
        list.add(new Type1ItemBean("空调"));
        list.add(new Type1ItemBean("空调"));
        list.add(new Type1ItemBean("窗帘"));
        list.add(new Type1ItemBean("空调"));
        list.add(new Type1ItemBean("空调"));

        return list;
    }
}
