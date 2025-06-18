package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.ComplaintDTO;

import java.util.List;

public interface AdminDAO {
    List<ComplaintDTO> getAllComplaints();
    List<ComplaintDTO> getComplaintsByStatus(String status);
}
