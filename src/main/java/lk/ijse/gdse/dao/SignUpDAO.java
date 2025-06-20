package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.ComplaintDTO;
import lk.ijse.gdse.dto.UserDTO;

public interface SignUpDAO {
    boolean saveUser(UserDTO userDTO);
    boolean isUsernameExists(String name);

}
