package com.me.src.pojo;

public enum Role {
	GlobalAdmin { @Override public String toString() { return "Global Admin"; }},
	Admin { @Override public String toString() { return "Admin"; }},
	Doctor { @Override public String toString() { return "Doctor"; }},
	Patient { @Override public String toString() { return "Patient"; }}	
}
