package lk.ijse.gdse;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name = "ComplaintServlet", value = "/Complain")
public class ComplaintServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userName = req.getParameter("userName");
        String title = req.getParameter("title");
        String complaint = req.getParameter("complaint");
        String date = req.getParameter("date");


        ServletContext servletContext = req.getServletContext();
        BasicDataSource bds = (BasicDataSource) servletContext.getAttribute("dataSource");

        try{
            Connection conn = bds.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Complaint (id, username, title, complaint,date) VALUES (?,?,?,?,?)");
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, userName);
            ps.setString(3, title);
            ps.setString(4, complaint);
            ps.setString(5, date);

            int result = ps.executeUpdate();

            if(result > 0){
                resp.sendRedirect("UserDashboard.jsp?status=success");
            }else {
                resp.sendRedirect("UserDashboard.jsp?status=fail");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
