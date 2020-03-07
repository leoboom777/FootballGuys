package com.android.footballguys.fragment;

import android.view.View;
import android.widget.TextView;

import com.android.footballguys.base.BaseFragment;

public class MineFragment extends BaseFragment {

    private TextView mTextView;

    protected View initView(){
        mTextView = new TextView(mContext);
        return mTextView;
    }

    protected void initData(){
        super.initData();
        mTextView.setText("4");
    }
}
