package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.UserDTO;

public interface LoginDAO {
    public UserDTO Login(String name, String password);
}
