package com.daniel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.daniel.model.User;
import com.daniel.util.DbUtil;
import java.net.URISyntaxException;

public class UserDao {

	private Connection connection;

	public UserDao() throws URISyntaxException {
		connection = DbUtil.getConnection();
	}

	public void addUser(User user) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into users(firstname,lastname,dob,email,login,pass) values (?, ?, ?, ?, ?,? )");
			// Parameters start with 1
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
			preparedStatement.setString(4, user.getEmail());
                        preparedStatement.setString(5, user.getLogin());
                        preparedStatement.setString(6, user.getPass());
                        
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(int userId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from users where userid=?");
			// Parameters start with 1
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update users set firstname=?, lastname=?, dob=?, email=?" +
							"where userid=?");
			// Parameters start with 1
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setInt(5, user.getUserid());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		try {
                    	Statement statement = connection.createStatement();
                    	ResultSet rs = statement.executeQuery("select * from users");
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setDob(rs.getDate("dob"));
				user.setEmail(rs.getString("email"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public User getUserById(int userId) {
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from users where userid=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				user.setUserid(rs.getInt("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setDob(rs.getDate("dob"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
        
        
        public User validar(User usuario) {
	    try {
	 	PreparedStatement preparedStatement = null;
                String consulta = "select * from users WHERE login=? AND pass=?";
                preparedStatement = connection.prepareStatement(consulta);
                preparedStatement.setString(1, usuario.getLogin());
                preparedStatement.setString(2, usuario.getPass());
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()){
                   
                   int id = rs.getInt("userid");
                   String login= rs.getString("login");
                   String pass = rs.getString("pass");
                   User sesion = new User();
                   sesion.setLogin(login);
                   sesion.setPass(pass);
                   sesion.setUserid(id);
                   return sesion;
                }else{
                  //Luego realizaremos otro tipo de retorno  
                  return null;
                }

		} catch (SQLException e) {
			e.printStackTrace();
		}
            
            return null;
	}
}
