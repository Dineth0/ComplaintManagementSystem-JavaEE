package lk.ijse.gdse.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
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

@WebServlet(name = "ComplaintServlet", value = "/ComplaintServlet")
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
        String status = req.getParameter("status");
        String remark = req.getParameter("remark");




        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");

        ComplaintDTO complaintDTO = new ComplaintDTO();
        complaintDTO.setUid(userDTO.getId());

        complaintDTO.setId(id);
        complaintDTO.setUserName(userName);
        complaintDTO.setTitle(title);
        complaintDTO.setComplaint(complaint);
        complaintDTO.setDate(date);
        complaintDTO.setStatus(status);
        complaintDTO.setRemark(remark);

        try{
            boolean result = false;
            String statusText = "";

            switch (action) {
                case "save":
                    result = complaintDAO.saveComplaint(complaintDTO, userDTO.getId());
                    statusText = "saved";
                    break;
                case "update":
                    result = complaintDAO.updateComplaint(complaintDTO);
                    statusText = "updated";
                    break;
                case "delete":
                    result = complaintDAO.deleteComplaint(id);
                    statusText = "deleted";
                    break;
            }
            HttpSession session = req.getSession();
            if (result) {
                session.setAttribute("status", statusText);
            } else {
                session.setAttribute("status", "fail");
            }
            resp.sendRedirect("ComplaintServlet");


        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ServletContext servletContext = req.getServletContext();
        BasicDataSource bds = (BasicDataSource) servletContext.getAttribute("dataSource");

        ComplaintDAO complaintDAO = new ComplaintDAOImpl(bds);

        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        UserDTO adminDTO = (UserDTO) req.getSession().getAttribute("admin");

        String uid = null;
        if (userDTO != null) {
            uid = userDTO.getId();
        } else if (adminDTO != null) {
            uid = adminDTO.getId();
        } else {
            resp.sendRedirect("login.jsp");
            return;
        }
        try{
            List<ComplaintDTO> complaints = complaintDAO.getAllComplaint(uid);
            req.setAttribute("complaints", complaints);
            HttpSession session = req.getSession();
            String status = (String) session.getAttribute("status");
            if (status != null) {
                req.setAttribute("status", status);
                session.removeAttribute("status");
            }
            Cookie cookie = new Cookie("JSESSIONID", "");
            cookie.setMaxAge(0);  // delete cookie
            cookie.setPath("/");
            resp.addCookie(cookie);
//            resp.sendRedirect("UserDashboard.jsp");
//            resp.sendRedirect("UserDashboard.jsp");

            req.getRequestDispatcher("UserDashboard.jsp").forward(req, resp);

            System.out.println("STATUS: " + status);
            System.out.println("COMPLAINTS SIZE: " + complaints.size());

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("UserDashboard.jsp?status=error");
        }
    }


}