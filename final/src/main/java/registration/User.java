package registration;

import java.io.Serializable;
import java.sql.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.UserDao;

@ManagedBean
@SessionScoped
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String password;
	private String login;
	private String mail;
	private String surname;
	private Date reg_date;
	private int login_count;
	private Date last_login;
	private int is_active;
	private String newName;
	private String newSurname;
	private String newMail;
	private String newPassword;
	private int man_id;

	@Inject
	UserDao ud;

	public User(String name, String password, String login, String mail, String surname, Date reg_date, int login_count,
			Date last_login, int is_active, int man_id) {
		super();
		this.name = name;
		this.password = password;
		this.login = login;
		this.mail = mail;
		this.surname = surname;
		this.reg_date = reg_date;
		this.login_count = login_count;
		this.last_login = last_login;
		this.is_active = is_active;
		this.man_id = man_id;

	}

	public User() {

	}

	public int getMan_id() {
		return man_id;
	}

	public void setMan_id(int man_id) {
		this.man_id = man_id;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNewSurname() {
		return newSurname;
	}

	public void setNewSurname(String newSurname) {
		this.newSurname = newSurname;
	}

	public String getNewMail() {
		return newMail;
	}

	public void setNewMail(String newMail) {
		this.newMail = newMail;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public int getLogin_count() {
		return login_count;
	}

	public void setLogin_count(int login_count) {
		this.login_count = login_count;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public int isIs_active() {
		return is_active;
	}

	public void setIs_active(int is_active) {
		this.is_active = 1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String saveUser() {

		try {
			// UserDao ud = new UserDao();
			if (login.matches("[a-z]{4,10}") && password.matches("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,})")
					&& mail.matches(".+\\@.+\\..+") && name.matches("[A-Z]{1}.*") && surname.matches("[A-Z]{1}.*")
					&& man_id >= 0) {
				return ud.saveUser(login, password, mail, name, surname, login_count, is_active, man_id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "register.xhtml";

	}

	public String existUser() {
		// UserDao ud = new UserDao();
		try {
			String s = ud.loginUser(this, login, password);
			System.out.println(this.getName() + "," + this.getSurname());
			return s;

		} catch (Exception e) {
			e.printStackTrace();
			return "login.xhtml";
		}

	}

	public String editProperties() {
		// UserDao ud = new UserDao();
		try {
			System.out.println("==============================" + login);
			ud.updateUser(login, "name", newName);
			ud.updateUser(login, "surname", newSurname);
			ud.updateUser(login, "mail", newMail);
			ud.updateUser(login, "password", newPassword);
			return "welcome.xhtml";
		} catch (Exception e) {
			return "edit.xhtml";
		}
	}

	public User getUser() {
		return this;
	}

}
