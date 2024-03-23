
package com.example.figmaapp;

import android.app.Activity;
import android.os.Bundle;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

public class login_activity extends Activity {

	
	private View _bg__login_ek2;
	private View _bg__group_329_ek1;
	private ImageView image_landscape;
	private TextView logo;
	private TextView enter_your_username_and_password_to_login;
	private TextView _forgot_password_;
	private TextView forgot_username_;
	private TextView _don_t_have_an_account__register;
	private TextView need_help__visit_our_help_center;
	private TextView or_login_in_with;
	private ImageView path_101;
	private ImageView path_103;
	private TextView password;
	private ImageView rectangle_39;
	private TextView login_ek3;
	private TextView username;
	private ImageView rectangle_128;
	private ImageView rectangle_129;
	private ImageView google;
	private TextView google_ek1;
	private TextView facebook;
	private ImageView facebook_ek1;
	private TextView login_ek4;
	private ImageView rectangle_129_ek1;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		
		_bg__login_ek2 = (View) findViewById(R.id._bg__login_ek2);
		_bg__group_329_ek1 = (View) findViewById(R.id._bg__group_329_ek1);
		image_landscape = (ImageView) findViewById(R.id.image_landscape);
		logo = (TextView) findViewById(R.id.logo);
		enter_your_username_and_password_to_login = (TextView) findViewById(R.id.enter_your_username_and_password_to_login);
		_forgot_password_ = (TextView) findViewById(R.id._forgot_password_);
		forgot_username_ = (TextView) findViewById(R.id.forgot_username_);
		_don_t_have_an_account__register = (TextView) findViewById(R.id._don_t_have_an_account__register);
		need_help__visit_our_help_center = (TextView) findViewById(R.id.need_help__visit_our_help_center);
		or_login_in_with = (TextView) findViewById(R.id.or_login_in_with);
		path_101 = (ImageView) findViewById(R.id.path_101);
		path_103 = (ImageView) findViewById(R.id.path_103);
		password = (TextView) findViewById(R.id.password);
		rectangle_39 = (ImageView) findViewById(R.id.rectangle_39);
		login_ek3 = (TextView) findViewById(R.id.login_ek3);
		username = (TextView) findViewById(R.id.username);
		rectangle_128 = (ImageView) findViewById(R.id.rectangle_128);
		rectangle_129 = (ImageView) findViewById(R.id.rectangle_129);
		google = (ImageView) findViewById(R.id.google);
		google_ek1 = (TextView) findViewById(R.id.google_ek1);
		facebook = (TextView) findViewById(R.id.facebook);
		facebook_ek1 = (ImageView) findViewById(R.id.facebook_ek1);
		login_ek4 = (TextView) findViewById(R.id.login_ek4);
		rectangle_129_ek1 = (ImageView) findViewById(R.id.rectangle_129_ek1);
	
		
		_forgot_password_.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View v) {
				
//				Intent nextScreen = new Intent(getApplicationContext(), forgot_password_activity.class);
//				startActivity(nextScreen);
			
		
			}
		});
		
		
		_don_t_have_an_account__register.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View v) {
				
//				Intent nextScreen = new Intent(getApplicationContext(), register_activity.class);
//				startActivity(nextScreen);
			
		
			}
		});
		
		
		//custom code goes here
	
	}
}
	
	