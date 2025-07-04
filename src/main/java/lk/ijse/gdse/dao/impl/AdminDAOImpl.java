package lk.ijse.gdse.dao.impl;

import lk.ijse.gdse.dao.AdminDAO;
import lk.ijse.gdse.dto.ComplaintDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    private final BasicDataSource dataSource;
    public AdminDAOImpl(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<ComplaintDTO> getAllComplaints() {
        List<ComplaintDTO> complaints = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Complaint");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ComplaintDTO complaintDTO = new ComplaintDTO(
                rs.getString("id"),
                        rs.getString("uid"),
                rs.getString("username"),
                rs.getString("title"),
                rs.getString("complaint"),
                rs.getString("date"),
                        rs.getString("status"),
                        rs.getString("remark")

                );
                complaints.add(complaintDTO);
            }
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return complaints;
    }

    @Override
    public List<ComplaintDTO> getComplaintsByStatus(String status) {
        List<ComplaintDTO> complaintDTOList = new ArrayList<>();

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Complaint WHERE status = ?");
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ComplaintDTO complaintDTO = new ComplaintDTO(
                        rs.getString("id"),
                        rs.getString("uid"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getString("complaint"),
                        rs.getString("date"),
                        rs.getString("status"),
                        rs.getString("remark")
                );
                complaintDTOList.add(complaintDTO);
            }
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return complaintDTOList;
    }
}
