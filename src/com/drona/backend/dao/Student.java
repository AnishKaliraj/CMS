package com.drona.backend.dao;


public class Student {
	   
	 String code = null;
	 String name = null;
	 boolean selected = false;
	   
	 public Student(String name, boolean selected) {
	  super();
	  this.code = code;
	  this.name = name;
	  this.selected = selected;
	 }
	   
	 public String getCode() {
	  return code;
	 }
	 public void setCode(String code) {
	  this.code = code;
	 }
	 public String getName() {
	  return name;
	 }
	 public void setName(String name) {
	  this.name = name;
	 }
	  
	 public boolean isSelected() {
	  return selected;
	 }
	 public void setSelected(boolean selected) {
	  this.selected = selected;
	 }
	   
	}