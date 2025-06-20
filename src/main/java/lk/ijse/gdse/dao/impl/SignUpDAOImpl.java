package lk.ijse.gdse.dao.impl;

import lk.ijse.gdse.dao.SignUpDAO;
import lk.ijse.gdse.dto.UserDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SignUpDAOImpl implements SignUpDAO {
    private final BasicDataSource basicDataSource;
    public SignUpDAOImpl(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        try {
            Connection conn = basicDataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO User (id, name, password, role) VALUES (?,?,?,?)");
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, userDTO.getName());
            ps.setString(3, userDTO.getPassword());
            ps.setString(4, userDTO.getRole());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isUsernameExists(String name) {
        try {
            Connection conn = basicDataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM User WHERE name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
