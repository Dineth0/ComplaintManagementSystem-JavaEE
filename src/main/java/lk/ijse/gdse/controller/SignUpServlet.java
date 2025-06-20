package lk.ijse.gdse.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lk.ijse.gdse.dao.LoginDAO;
import lk.ijse.gdse.dao.SignUpDAO;
import lk.ijse.gdse.dao.impl.LoginDAOImpl;
import lk.ijse.gdse.dao.impl.SignUpDAOImpl;
import lk.ijse.gdse.dto.UserDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;

@WebServlet("/singup")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServletContext servletContext = req.getServletContext();
        BasicDataSource bds = (BasicDataSource) servletContext.getAttribute("dataSource");

        String id = req.getParameter("id");
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");


        SignUpDAO signUpDAO = new SignUpDAOImpl(bds);
        if(signUpDAO.isUsernameExists(name)){
            req.setAttribute("errorMessage","Name is Already Exists");
            try {
                req.getRequestDispatcher("SignUp.jsp").forward(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return;
        }
        UserDTO userDTO = new UserDTO(id,name,password,role);


        boolean isSaved = signUpDAO.saveUser(userDTO);

        if (isSaved) {
            resp.sendRedirect("login.jsp");
        }else {
            req.getRequestDispatcher("SignUp.jsp").forward(req, resp);
        }

    }
}
