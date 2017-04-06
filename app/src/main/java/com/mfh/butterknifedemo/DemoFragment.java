package com.mfh.butterknifedemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends Fragment {


    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userPwd)
    TextView userPwd;
    Unbinder unbinder;

    public DemoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demo, container, false);
        unbinder = ButterKnife.bind(this, view);
        userName.setText(getArguments().getString("user"));
        userPwd.setText(getArguments().getString("pwd"));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.userName, R.id.userPwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.userName:
                Toast.makeText(getContext(), "你干啥", Toast.LENGTH_SHORT).show();
                break;
            case R.id.userPwd:
                Toast.makeText(getContext(), "你戳啥", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
