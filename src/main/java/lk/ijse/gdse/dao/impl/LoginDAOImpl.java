package lk.ijse.gdse.dao.impl;

import lk.ijse.gdse.dao.LoginDAO;
import lk.ijse.gdse.dto.UserDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {

    private final BasicDataSource dataSource;

    public LoginDAOImpl(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UserDTO Login(String name, String password) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from User where name=? and password=?");
            ps.setString(1, name);
            ps.setString(2, password);


            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new UserDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
