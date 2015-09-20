package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import registration.User;

@SessionScoped
@ManagedBean
public class UserDao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url = "jdbc:mysql://localhost:3306/petpetro";
	private String name = "root";
	private String pass = "root";
	@Inject
	User user;

	public int createTableIfNotExist() {

		String create_table_statement = "create table if not exists user(Id int(10) unsigned auto_increment primary key,"
				+ "login varchar(255) unique not null," + "password varchar(255) not null,"
				+ "mail varchar(255) unique not null," + "name varchar(255) not null,"
				+ "surname  varchar(255) not null," + "registration_date datetime not null,"
				+ "login_count int(10) unsigned not null," + "last_login datetime not null,"
				+ "is_active boolean not null," + "manager_id int(10) unsigned not null) ";
		try {
			Connection connection = connect();
			Statement stm = connection.createStatement();
			return stm.executeUpdate(create_table_statement);
		} catch (Exception e) {
			System.out.println("Exception occured during create database " + e.getMessage());
		}

		return 1;

	}

	public Connection connect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			return DriverManager.getConnection(url, name, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String saveUser(String login, String password, String mail, String name2, String surname, int login_count,
			int is_active, int man_id) {

		try {
			createTableIfNotExist();
		} catch (Exception e) {
			// TODO: handle exception
		}

		String insert_Statement = "insert into user (login,password,mail,name,surname,registration_date,login_count,last_login,is_active,manager_id)"
				+ "values('" + login + "','" + password + "','" + mail + "','" + name2 + "','" + surname + "','"
				+ new java.sql.Timestamp(new java.util.Date().getTime()) + "','" + login_count + "','"
				+ new java.sql.Timestamp(new Date().getTime()) + "','" + is_active + "'," + man_id + ");";

		try {
			Connection connection = connect();
			Statement stm = connection.createStatement();
			stm.executeUpdate(insert_Statement);
			return "welcome.xhtml";
		} catch (Exception e) {
			System.out.println("Exception occured during create database " + e.getMessage());
		}
		return "register.xhtml";
	}

	public String loginUser(User user, String login, String password) {
		String select_user = "select * from user where login='" + login + "' and password='" + password + "';";

		try {
			Connection connection = connect();
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(select_user);
			if (rs.next()) {

				user.setLogin(rs.getString(2));
				System.out.println(user.getLogin());
				user.setPassword(rs.getString(3));
				user.setMail(rs.getString(4));
				user.setName(rs.getString(5));
				user.setSurname(rs.getString(6));
				return "welcome";
			}
		} catch (Exception e) {
			System.out.println("Exception occured during select database " + e.getMessage());

		}
		return "login";
	}

	public void updateUser(String login, String property, String value) {
		String update_user = "UPDATE user SET" + property + "='" + value + "' WHERE login='" + login + "';";
		try {
			Connection connection = connect();
			Statement stm = connection.createStatement();
			stm.executeUpdate(update_user);
		} catch (Exception e) {
			System.out.println("Exception occured during update database " + e.getMessage());

		}
	}

	public List selectManagerIds() {
		List ids = new ArrayList<>();
		String select_ids = "SELECT distinct manager_id FROM user order by manager_id;";

		try {
			Connection connection = connect();
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(select_ids);

			while (rs.next()) {
				int id = rs.getInt(1);
				ids.add(id);
			}

			return ids;
		} catch (Exception e) {
			System.out.println("Exception occured during select from database " + e.getMessage());
			return null;
		}

	}

	public String getSpaces(int space) {
		String s = "";

		for (int i = 0; i < space; i++) {
			s = s + "________";
		}

		return s;

	}

	public List<SelectedUser> selectUsers() {

		List<SelectedUser> users1 = new ArrayList<>();

		String select_users = "SELECT id,name,surname,manager_id FROM user order by manager_id;";

		try {
			Connection connection = connect();
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(select_users);

			while (rs.next()) {
				SelectedUser su = new SelectedUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), 0);
				users1.add(su);
			}

			return users1;
		} catch (Exception e) {
			System.out.println("Exception occured during select from database " + e.getMessage());
			return null;
		}

	}

	public List<SelectedUser> orderList(int id, int level) {
		List<SelectedUser> users = selectUsers();
		List<SelectedUser> orderedUsers = new ArrayList<>();

		for (SelectedUser s : users) {
			if (s.getSel_man_id() == id) {
				s.setLevel(level);
				orderedUsers.add(s);
				++level;
				orderedUsers.addAll(orderList(s.getSel_id(), level));
				--level;
			}
		}
		return orderedUsers;
	}

	public static class SelectedUser {
		private String sel_name;
		private String sel_surname;
		private int sel_man_id;
		private int sel_id;
		private int level;

		public SelectedUser(int sel_id, String name, String surname, int man_id, int level) {
			this.sel_name = name;
			this.sel_surname = surname;
			this.sel_man_id = man_id;
			this.sel_id = sel_id;
			this.level = level;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public int getSel_id() {
			return sel_id;
		}

		public void setSel_id(int sel_id) {
			this.sel_id = sel_id;
		}

		public String getSel_name() {
			return sel_name;
		}

		public void setSel_name(String sel_name) {
			this.sel_name = sel_name;
		}

		public String getSel_surname() {
			return sel_surname;
		}

		public void setSel_surname(String sel_surname) {
			this.sel_surname = sel_surname;
		}

		public int getSel_man_id() {
			return sel_man_id;
		}

		public void setSel_man_id(int sel_man_id) {
			this.sel_man_id = sel_man_id;
		}

	}

}