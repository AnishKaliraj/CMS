package com.drona.staff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.drona.cms.R;
import com.drona.common.CommonObjects;

public class ClassActivity extends Activity{
	
	ListView list;
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	
	Object listitemselection;
	String listitem;
	
	Dialog loginDialog = null;
	boolean hasLoggedIn;
	
	static final String[] semster = new String[] { "1st year ECE B","2nd year CSE A","3rd year IT B",
	"4th year CSE C" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class);
		
		list = (ListView) findViewById(R.id.listView1);
		settings = getSharedPreferences("shared_pref", 0);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, semster);
		
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				listitemselection = list.getItemAtPosition(arg2);
				listitem = listitemselection.toString();
				
				Intent i = new Intent(ClassActivity.this,StudAttendance.class);
				startActivity(i);

				Toast.makeText(getApplicationContext(), "asfasf"+listitem,3000).show();

			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		hasLoggedIn = settings.getBoolean("hasLoggedIn",false);
		if(!hasLoggedIn){
			loadloginScreen();	
		}
	}
	
	private void loadloginScreen() {
		// TODO Auto-generated method stub
		

		if (null == loginDialog) {
			loginDialog = new Dialog(this,
					android.R.style.Theme_Translucent_NoTitleBar);
			loginDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			loginDialog.setContentView(R.layout.login);
			loginDialog.setCancelable(false);

			loginDialog.setCanceledOnTouchOutside(false);
			WindowManager.LayoutParams WMLP = loginDialog.getWindow()
					.getAttributes();

			WMLP.gravity = Gravity.CENTER;

			loginDialog.getWindow().setAttributes(WMLP);
			((EditText) loginDialog.findViewById(R.id.login_txt_username))
					.setText("staff123");
			((EditText) loginDialog.findViewById(R.id.login_txt_password))
					.setText("staff123");

			Button loginbtn = (Button) loginDialog
					.findViewById(R.id.alertLoginBtn);
			loginbtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					try {

							hideSoftKeyBoardOnTabClicked(((EditText) ((View) v
									.getParent())
									.findViewById(R.id.login_txt_password)));
							EditText username = (EditText) ((View) v
									.getParent())
									.findViewById(R.id.login_txt_username);
							EditText password = (EditText) ((View) v
									.getParent())
									.findViewById(R.id.login_txt_password);
							if (username.length() > 0) {
								if (password.length() > 0) 
								{
									String[] value = new String[] {
											username.getText().toString(),
											password.getText().toString() };
									editor = settings.edit();
									editor.putBoolean("hasLoggedIn", true);
									editor.commit();
									Toast.makeText(getApplicationContext(), "asfgasg", 3000).show();
								} else {
									// TODO change toast
									Toast.makeText(getApplicationContext(), "Failed", 3000).show();
									loadloginScreen();
								}
							} else {
								// TODO change toast
								Toast.makeText(getApplicationContext(), "Failed", 3000).show();
								loadloginScreen();
							}
							loginDialog.dismiss();
							loginDialog = null;

						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Toast.makeText(getApplicationContext(), "Failed", 3000).show();
					}
				}

			});

			loginDialog.show();
		}
	
		
	}
	
	private void hideSoftKeyBoardOnTabClicked(View v) {
		if (v != null) {
			InputMethodManager imm = (InputMethodManager) this
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(v.getApplicationWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
	
	
	
	

}
