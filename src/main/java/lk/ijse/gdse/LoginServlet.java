package lk.ijse.gdse;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.gdse.dto.UserDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ServletContext servletContext = req.getServletContext();
        BasicDataSource bds = (BasicDataSource) servletContext.getAttribute("dataSource");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        try {
            Connection conn = bds.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from User where name=? and password=? and role=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            ResultSet rs=ps.executeQuery();

            if(rs.next()) {
                String id = rs.getString("id");
                HttpSession session = req.getSession();
                session.setAttribute("user", new UserDTO(id,username,password,role));

                if(role.equalsIgnoreCase("admin")) {
                    session.setAttribute("admin", new UserDTO(id,username,password,role));
                    resp.sendRedirect("AdminDashboard.jsp");
                }else {
                    session.setAttribute("user", new UserDTO(id,username,password,role));
                    resp.sendRedirect("UserDashboard.jsp");
                }
            }else {
                resp.sendRedirect("Login.jsp? error=true");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
