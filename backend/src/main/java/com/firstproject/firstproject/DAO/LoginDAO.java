package com.firstproject.firstproject.DAO;

import lombok.Data;

@Data
public class LoginDAO {
	    private String username;
	    private String password;
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
}
