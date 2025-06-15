package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.ComplaintDTO;

import java.util.List;

public interface ComplaintDAO {
    boolean saveComplaint(ComplaintDTO complaintDTO, String uid);
    boolean updateComplaint(ComplaintDTO complaintDTO);
    boolean deleteComplaint(String uid);
    List<ComplaintDTO> getAllComplaint(String uid);
    ComplaintDTO getComplaintById(String id);
}
