package com.drona.staff;

import java.util.ArrayList;

import com.drona.backend.dao.Student;
import com.drona.cms.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

public class StudAttendance extends Activity{
	
	MyCustomAdapter dataAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.studattendance);
		
		displayListView();
		  
		checkButtonClick();
	}

	private void checkButtonClick() {
		// TODO Auto-generated method stub
				Button myButton = (Button) findViewById(R.id.findSelected);
				myButton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						StringBuffer responseText = new StringBuffer();
					    responseText.append("The following were selected...\n");
					    ArrayList<Student> studentList = dataAdapter.studentList;
					    for (int i = 0; i<studentList.size(); i++){
					    	Student student = studentList.get(i);
					    	 if(student.isSelected()){
					    	      responseText.append("\n" + student.getName());
					    	     }
					    }
					    
					    Toast.makeText(getApplicationContext(), responseText, 5000).show();
					}
				});
	}

	private void displayListView() {
		// TODO Auto-generated method stub
		ArrayList<Student> studentList = new ArrayList<Student>();
		Student student = new Student("Afghanistan",false);
		studentList.add(student);
		student = new Student("Albania", true);
		studentList.add(student);
		student = new Student("Algeria", false);
		studentList.add(student);
		student = new Student("American Samoa", true);
		studentList.add(student);
		student = new Student("Andorra", true);
		studentList.add(student);
		student = new Student("Angola", false);
		studentList.add(student);
		student = new Student("Anguilla", false);
		studentList.add(student);
		
		dataAdapter = new MyCustomAdapter(this,
			    R.layout.stud_attendance_info, studentList);
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		// Assign adapter to ListView
		  listView.setAdapter(dataAdapter);
	}
	
	public class MyCustomAdapter extends ArrayAdapter<Student>{
		
		private ArrayList<Student> studentList;
		
		public MyCustomAdapter(Context context, int textViewResourceId,
			    ArrayList<Student> studentList) {
			   super(context, textViewResourceId, studentList);
			   this.studentList = new ArrayList<Student>();
			   this.studentList.addAll(studentList);
			  }
		
		 private class ViewHolder {
			   CheckBox name;
			  }
		 @Override
		  public View getView(int position, View convertView, ViewGroup parent) {
		  
		   ViewHolder holder = null;
		   Log.v("ConvertView", String.valueOf(position));
		  
		   if (convertView == null) {
		   LayoutInflater vi = (LayoutInflater)getSystemService(
		     Context.LAYOUT_INFLATER_SERVICE);
		   convertView = vi.inflate(R.layout.stud_attendance_info, null);
		  
		   holder = new ViewHolder();
		   holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
		   convertView.setTag(holder);
		  
		    holder.name.setOnClickListener( new View.OnClickListener() {
		     public void onClick(View v) {
		      CheckBox cb = (CheckBox) v ;
		      Student student = (Student) cb.getTag();
		      Toast.makeText(getApplicationContext(),
		       "Clicked on Checkbox: " + cb.getText() +
		       " is " + cb.isChecked(),
		       Toast.LENGTH_LONG).show();
		      student.setSelected(cb.isChecked());
		     }
		    });
		   }
		   else {
		    holder = (ViewHolder) convertView.getTag();
		   }
		  
		   Student student = studentList.get(position);
		   holder.name.setText(student.getName());
		   holder.name.setChecked(student.isSelected());
		   holder.name.setTag(student);
		  
		   return convertView;
		  
		  }
	}

}
