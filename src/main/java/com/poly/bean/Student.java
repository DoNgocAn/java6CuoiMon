package com.poly.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	private String id;
	private String password;
	private String fullname;
	private String email;
	private Boolean admin;
	
}
