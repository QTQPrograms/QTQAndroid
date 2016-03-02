package com.qtq.qtqandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FunFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.tab_fun, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		View view=	getView();

		Button btn_fun_xundian=(Button)view.findViewById(R.id.btn_fun_xundian);
		btn_fun_xundian.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(getActivity(), "dddd", Toast.LENGTH_LONG).show();

				Intent intent=new Intent();
				intent.setClass(getView().getContext() ,XunDianMainActivity.class);
				startActivity(intent);

			}
		});

		Button btn_fun_chenliejiancha=(Button)view.findViewById(R.id.btn_fun_chenliejiancha);
		btn_fun_chenliejiancha.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "dddd", Toast.LENGTH_LONG).show();
			}
		});

		Button btn_fun_jingpinshangbao=(Button)view.findViewById(R.id.btn_fun_jingpinshangbao);
		btn_fun_jingpinshangbao.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "dddd", Toast.LENGTH_LONG).show();
			}
		});



	}


}
