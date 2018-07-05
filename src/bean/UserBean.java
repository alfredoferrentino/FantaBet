package bean;

import java.io.Serializable;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	String email;
	String username;
	String nick;
	String password;
	String ruolo;

	public UserBean() {
		email="";
		username="";
		nick="";
		password="";
		ruolo="";
		
	}




	public String getEmail() {
		return email;
	}




	public String getRuolo() {
		return ruolo;
	}




	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getNick() {
		return nick;
	}




	public void setNick(String nick) {
		this.nick = nick;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public String toString() {
		return "UserBean [email=" + email + ", username=" + username + ", nick=" + nick + ", password=" + password + ", ruolo = " + ruolo +"]";
	}




	





}


