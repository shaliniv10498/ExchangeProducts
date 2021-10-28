package com.shalini.verma.APIStatusEnums;

public enum StatusEnums {
	
	SUCCESS("Success"),
	FAILURE("Failure"),
	VALID("Valid"),
	INVALID("Invalid");
	
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private  StatusEnums(String status){
		this.status=status;
	}

}
