package com.me.src.pojo;

public enum ConsentType {
	Emergency {
		@Override
		public String toString() {
			return "Emergency";
		}		
	},
	
	ShareInformation {
		@Override
		public String toString() {
			return "Share Information";
		}
	},
	
	Research {
		@Override
		public String toString() {
			return "Research";
		}
	}	
}
