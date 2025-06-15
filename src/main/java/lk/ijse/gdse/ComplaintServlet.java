package lk.ijse.gdse;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.dao.ComplaintDAO;
import lk.ijse.gdse.dao.impl.ComplaintDAOImpl;
import lk.ijse.gdse.dto.ComplaintDTO;
import lk.ijse.gdse.dto.UserDTO;
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
        ServletContext servletContext = req.getServletContext();
        BasicDataSource bds = (BasicDataSource) servletContext.getAttribute("dataSource");

        ComplaintDAO complaintDAO = new ComplaintDAOImpl(bds);

        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String userName = req.getParameter("userName");
        String title = req.getParameter("title");
        String complaint = req.getParameter("complaint");
        String date = req.getParameter("date");




        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        req.getSession().getAttribute("admin");


        ComplaintDTO complaintDTO = new ComplaintDTO();
        complaintDTO.setId(id);
        complaintDTO.setUserName(userName);
        complaintDTO.setTitle(title);
        complaintDTO.setComplaint(complaint);
        complaintDTO.setDate(date);

        try{
            boolean result = false;

            switch (action) {
                case "save":
                    result = complaintDAO.saveComplaint(complaintDTO, userDTO.getId());
                    break;
                case "update":
                    result = complaintDAO.updateComplaint(complaintDTO);
                    break;
                case "delete":
                    result = complaintDAO.deleteComplaint(id);
                    break;
            }
            if(result){
                resp.sendRedirect("Complain?status=success");
            }else {
                resp.sendRedirect("UserDashboard.jsp?status=fail");
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ServletContext servletContext = req.getServletContext();
        BasicDataSource bds = (BasicDataSource) servletContext.getAttribute("dataSource");

        ComplaintDAO complaintDAO = new ComplaintDAOImpl(bds);

        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        req.getSession().getAttribute("admin");
        try{
            List<ComplaintDTO> complaints = complaintDAO.getAllComplaint(userDTO.getId());
            req.setAttribute("complaints", complaints);
            req.getRequestDispatcher("UserDashboard.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("UserDashboard.jsp?status=error");
        }
    }


}
