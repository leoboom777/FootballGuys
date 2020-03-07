package com.android.footballguys;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.android.footballguys.base.BaseFragment;
import com.android.footballguys.fragment.CartFragment;
import com.android.footballguys.fragment.FavoriteFragment;
import com.android.footballguys.fragment.MineFragment;
import com.android.footballguys.fragment.ShopFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private RadioGroup mRadioGroup;
    private ArrayList<BaseFragment> mBaseFragments;
    private int position;
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioGroup = findViewById(R.id.bottom_button_group);
        mRadioGroup.check(R.id.bottom_shop_button);

        mBaseFragments = new ArrayList<>();
        mBaseFragments.add(new ShopFragment());
        mBaseFragments.add(new FavoriteFragment());
        mBaseFragments.add(new CartFragment());
        mBaseFragments.add(new MineFragment());

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.bottom_shop_button:
                        position = 0;
                        break;
                    case R.id.bottom_favorite_button:
                        position = 1;
                        break;
                    case R.id.bottom_cart_button:
                        position = 2;
                        break;
                    case R.id.bottom_mine_button:
                        position = 3;
                        break;
                    default:
                        position = 0;
                        break;
                }
                //根据位置得到对应的Fragment
                BaseFragment to = getFragment();
                //替换
                switchFragment(mContent,to);
            }
        });
    }
    /**
     * 根据位置得到对应的Fragment
     * @return fragment 要切换的fragment
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragments.get(position);
        return fragment;
    }
    /**
     *
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to 马上要切换到的Fragment，一会要显示
     */
    private void switchFragment(Fragment from, Fragment to) {
        if(from != to){
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if(!to.isAdded()){
                //to没有被添加
                //from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //添加to
                if(to != null){
                    ft.add(R.id.fl_content,to).commit();
                }
            }else{
                //to已经被添加
                // from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //显示to
                if(to != null){
                    ft.show(to).commit();
                }
            }
        }

    }
}
