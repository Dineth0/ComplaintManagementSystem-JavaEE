package lk.ijse.gdse;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.dto.ComplaintDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ComplaintServlet", value = "/Complain")
public class ComplaintServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String userName = req.getParameter("userName");
        String title = req.getParameter("title");
        String complaint = req.getParameter("complaint");
        String date = req.getParameter("date");


        ServletContext servletContext = req.getServletContext();
        BasicDataSource bds = (BasicDataSource) servletContext.getAttribute("dataSource");

        try{
            Connection conn = bds.getConnection();
            int result = 0;

            if("save".equalsIgnoreCase(action)) {


                PreparedStatement ps = conn.prepareStatement("INSERT INTO Complaint (id, username, title, complaint,date) VALUES (?,?,?,?,?)");
                ps.setString(1, UUID.randomUUID().toString());
                ps.setString(2, userName);
                ps.setString(3, title);
                ps.setString(4, complaint);
                ps.setString(5, date);
                result = ps.executeUpdate();

            }else if("update".equalsIgnoreCase(action)) {
                PreparedStatement ps = conn.prepareStatement("UPDATE Complaint SET username = ?, title = ?, complaint = ?, date = ? WHERE id = ?");

                ps.setString(1, userName);
                ps.setString(2, title);
                ps.setString(3, complaint);
                ps.setString(4, date);
                ps.setString(5, id);
                result = ps.executeUpdate();
            }

            if(result > 0){
                resp.sendRedirect("UserDashboard.jsp?status=success");
            }else {
                resp.sendRedirect("UserDashboard.jsp?status=fail");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());


        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");

        ServletContext servletContext = req.getServletContext();
        BasicDataSource bds = (BasicDataSource) servletContext.getAttribute("dataSource");

        try{
            Connection conn = bds.getConnection();
            List<ComplaintDTO> complaints = new ArrayList<>();
            PreparedStatement preparedStatement;
            if(search != null && !search.trim().isEmpty()){
                preparedStatement = conn.prepareStatement("SELECT * FROM Complaint WHERE username = ?");
                preparedStatement.setString(1, search.trim());
            }else {
                preparedStatement = conn.prepareStatement("SELECT * FROM Complaint");
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ComplaintDTO complaintDTO = new ComplaintDTO();
                complaintDTO.setId(resultSet.getString("id"));
                complaintDTO.setUserName(resultSet.getString("username"));
                complaintDTO.setTitle(resultSet.getString("title"));
                complaintDTO.setComplaint(resultSet.getString("complaint"));
                complaintDTO.setDate(resultSet.getDate("date"));
                complaints.add(complaintDTO);
            }
            req.setAttribute("complaints", complaints);
            req.getRequestDispatcher("UserDashboard.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("UserDashboard.jsp?status=error");
        }
    }


}
