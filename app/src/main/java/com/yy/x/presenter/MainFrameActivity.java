package com.yy.x.presenter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.yy.base.BaseFragmentActivity;
import com.yy.x.R;

import java.util.HashMap;

/**
 * 主界面
 * @data 2017年4月15日19:33:10
 */
public class MainFrameActivity extends BaseFragmentActivity {
    private View view_tab_news;//新闻
    private View view_tab_q_a;//问答回复
    private View view_tab_ask;//提问
    private View view_tab_activity;//活动
    private View view_tab_user;//我的

    private View [] tabViews = {view_tab_news,view_tab_q_a,view_tab_activity,view_tab_user};
    private int [] tabViews_res_id = {R.id.news_tab,  R.id.answer_tab, R.id.activity_tab, R.id.user_info_tab};
    private int [] tabViews_icon_res_id = {R.drawable.icon_tab_news,  R.drawable.icon_tab_q_a,
                                           R.drawable.icon_tab_activity, R.drawable.icon_tab_user};
    private int [] tabViews_title_res_id = {R.string.tab_news,  R.string.tab_qa, R.string.tab_activity, R.string.tab_mine};

//    private Fragment [] fragments = new Fragment[tabViews.length];

    private HashMap<Integer,Fragment> fragmentHashMap = new HashMap<>(tabViews.length);

    private int currentTabKey; // 当前Tab页面索引

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        //初始化4个fragemnt：新闻，回答回复，活动，我的
        //主界面中底部按钮，关联到fragment的切换加载

        //底部按钮，首先初始化，设置icon和text

        initView();

        //初始化fragment对象
        initFragments();

        //默认显示第一项
        tabViews[0].setSelected(true);
        addFrament(fragmentHashMap.get(R.id.news_tab),R.id.news_tab);
        currentTabKey = R.id.news_tab;

        //modify 2017年7月4日11:28:13 weizg 根据反馈，“取消雅玛克（和盾）赠送”
//        initData();

    }

    private void initFragments(){

/*        fragmentHashMap.put(R.id.news_tab, new NewsFragment());
        fragmentHashMap.put(R.id.answer_tab,new QAFragment());
        fragmentHashMap.put(R.id.activity_tab,new ActvFragment());
        fragmentHashMap.put(R.id.user_info_tab,new MineFragment());

        //提问
        fragmentHashMap.put(R.id.ask_tab,new AskFirstSetpFragment());*/
    }

    private void initView() {
//        view_tab_ask = findViewById(R.id.ask_tab);

        initTabView();

    }

    private void initTabView(){
        int tabSize = tabViews.length;
        OnTabItemClickListener onTabItemClickListener = new OnTabItemClickListener();
        for (int i = 0; i < tabSize; i++) {
            tabViews[i] = findViewById(tabViews_res_id[i]);
            tabViews[i].findViewById(R.id.tab_icon).setBackgroundResource(tabViews_icon_res_id[i]);
            ((TextView) tabViews[i].findViewById(R.id.tab_title)).setText(tabViews_title_res_id[i]);

            tabViews[i].setOnClickListener(onTabItemClickListener);
        }

//        view_tab_ask.setOnClickListener(onTabItemClickListener);
    }

    private class OnTabItemClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //首先设置点击item未选中状态，其他的为非选择状态
            int clickedItemIndex = getTabItemIndexFromResId(v.getId());
            selectTabItem(clickedItemIndex);

            switch (v.getId()){
                case R.id.news_tab://新闻
//                    showBottomMsg(v,"this will show news fragment");
                    switchShowFragment(R.id.news_tab);
                    break;
                case R.id.answer_tab://问答回复
//                    showBottomMsg(v,"this will show QA fragment");
                    switchShowFragment(R.id.answer_tab);
                    break;
                case R.id.activity_tab://活动
//                    showBottomMsg(v,"this will show Activity fragment");
                    switchShowFragment(R.id.activity_tab);
                    break;
                case R.id.user_info_tab://我的
//                    showBottomMsg(v,"this will show userInfo fragment");
                    switchShowFragment(R.id.user_info_tab);
                    break;
            }
        }
    }

    private int getTabItemIndexFromResId(int resId){
        int itemIndex = -1;

        for (int index = 0; index < tabViews.length; index++) {
            if(tabViews_res_id[index] == resId){
                itemIndex = index;
                break;
            }
        }

        return itemIndex;
    }

    private void selectTabItem(int selectItemIndex){
        if(selectItemIndex < 0 || selectItemIndex >= tabViews.length){
            return;
        }

        tabViews[selectItemIndex].setSelected(true);
        for (int i = 0; i < tabViews.length; i++) {
            if(i == selectItemIndex){
                tabViews[i].setSelected(true);
            }else {
                tabViews[i].setSelected(false);
            }
        }

    }


    private void addFrament(Fragment fragment, int fragmentKey){

        if (!fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,fragment,String.valueOf(fragmentKey))
                    .commit();
        }
    }

    private void switchShowFragment(int fragmentKey){
        Fragment fragment = fragmentHashMap.get(fragmentKey);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //首先暂停，并隐藏当前fragment
        getCurrentFragment().onPause();
        fragmentTransaction.hide(getCurrentFragment());

        //如果将要显示的fragment已经add，则resume，show
        if(fragment.isAdded()){
            fragment.onResume();
            fragmentTransaction.show(fragment);
        }else {
            //否则，add
            fragmentTransaction
                    .add(R.id.fragment_container,fragment,String.valueOf(fragmentKey));
        }

        fragmentTransaction.commit();

       currentTabKey = fragmentKey;
    }

    /*private void setCurrentFragment(int fragmentKey) {

    }*/

    private Fragment getCurrentFragment(){
        return fragmentHashMap.get(currentTabKey);
    }



    private DialogInterface.OnClickListener dialogBtnOnclickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    };

}
