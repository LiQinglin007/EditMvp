package com.example.lql.editmvp.basics.ui.fragment.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.basics.base.BaseFragment;
import com.example.lql.editmvp.basics.base.BasePreserent;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {

    private static MeFragment meFragment;

    public static  MeFragment getInstance(){
        if(meFragment==null){
            meFragment=new MeFragment();
        }
        return  meFragment;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.accordion:
//                break;
        }
    }

    @Override
    protected BasePreserent createPresenter() {
        return null;
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {

    }
}
