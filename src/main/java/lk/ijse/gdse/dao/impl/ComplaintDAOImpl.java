package lk.ijse.gdse.dao.impl;

import lk.ijse.gdse.dao.ComplaintDAO;
import lk.ijse.gdse.dto.ComplaintDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComplaintDAOImpl implements ComplaintDAO {

    private final BasicDataSource basicDataSource;
    public ComplaintDAOImpl(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }
    @Override
    public boolean saveComplaint(ComplaintDTO complaintDTO, String uid) {
        try {
            Connection conn = basicDataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Complaint (id, uid, username, title, complaint,date) VALUES (?,?,?,?,?,?)");
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, uid);
            ps.setString(3, complaintDTO.getUserName());
            ps.setString(4, complaintDTO.getTitle());
            ps.setString(5, complaintDTO.getComplaint());
            ps.setString(6, String.valueOf(complaintDTO.getDate()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateComplaint(ComplaintDTO complaintDTO) {
        try{
            Connection connection = basicDataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE Complaint SET username = ?, title = ?, complaint = ?, date = ? WHERE id = ?");

            ps.setString(1, complaintDTO.getUserName());
            ps.setString(2, complaintDTO.getTitle());
            ps.setString(3, complaintDTO.getComplaint());
            ps.setString(4, String.valueOf(complaintDTO.getDate()));
            ps.setString(5, complaintDTO.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteComplaint(String uid) {
        try{
            Connection connection = basicDataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Complaint WHERE id = ?");
            ps.setString(1, uid);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ComplaintDTO> getAllComplaint(String uid) {
        List<ComplaintDTO> complaintDTOList = new ArrayList<>();
        try {
            Connection connection = basicDataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Complaint WHERE uid = ?");
            ps.setString(1, uid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                ComplaintDTO complaintDTO = new ComplaintDTO();
                complaintDTO.setId(rs.getString("id"));
                complaintDTO.setUserName(rs.getString("username"));
                complaintDTO.setTitle(rs.getString("title"));
                complaintDTO.setComplaint(rs.getString("complaint"));
                complaintDTO.setDate(rs.getString("date"));
                complaintDTOList.add(complaintDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return complaintDTOList;
    }

    @Override
    public ComplaintDTO getComplaintById(String id) {
        try {
            Connection connection = basicDataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Complaint WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new ComplaintDTO(
                        rs.getString("id"),
                        rs.getString("uid"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getString("complaint"),
                        rs.getString("date")

                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
