package lk.ijse.gdse.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.gdse.dao.LoginDAO;
import lk.ijse.gdse.dao.impl.LoginDAOImpl;
import lk.ijse.gdse.dto.UserDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ServletContext servletContext = req.getServletContext();
        BasicDataSource bds = (BasicDataSource) servletContext.getAttribute("dataSource");

        String name = req.getParameter("username");
        String password = req.getParameter("password");


        LoginDAO loginDAO = new LoginDAOImpl(bds);

        try {
            UserDTO user = loginDAO.Login(name, password);

            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);

                String role = user.getRole();
                if(role.equalsIgnoreCase("admin")) {
                    session.setAttribute("admin", user);
                   session.setAttribute("isAdmin", true);
                    resp.sendRedirect("Admin");
                }else {
                    session.setAttribute("user", user);
                    resp.sendRedirect("ComplaintServlet");
                    //resp.sendRedirect("UserDashboard.jsp");

                }
            }else {
                resp.sendRedirect("login.jsp?error=true");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
