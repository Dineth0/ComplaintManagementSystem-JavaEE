package lk.ijse.gdse.controller;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.dao.AdminDAO;
import lk.ijse.gdse.dao.ComplaintDAO;
import lk.ijse.gdse.dao.impl.AdminDAOImpl;
import lk.ijse.gdse.dao.impl.ComplaintDAOImpl;
import lk.ijse.gdse.dto.ComplaintDTO;
import lk.ijse.gdse.dto.UserDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/Admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        BasicDataSource bds = (BasicDataSource) servletContext.getAttribute("dataSource");

        AdminDAO adminDAO = new AdminDAOImpl(bds);
        List<ComplaintDTO> complaints;
        try{
            String status = req.getParameter("search");
            if(status != null && (status.equals("success") || status.equals("fail") || status.equals("error"))) {
                complaints = adminDAO.getAllComplaints();
                req.setAttribute("status", status);
            } else if (status != null && !status.isEmpty()) {
                complaints = adminDAO.getComplaintsByStatus(status);
            } else {
                complaints = adminDAO.getAllComplaints();
            }
            req.setAttribute("complaints", complaints);
            req.getRequestDispatcher("AdminDashboard.jsp").forward(req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
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




        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("");



        ComplaintDTO complaintDTO = new ComplaintDTO();
        complaintDTO.setId(id);
        complaintDTO.setUserName(userName);
        complaintDTO.setTitle(title);
        complaintDTO.setComplaint(complaint);
        complaintDTO.setDate(date);
        complaintDTO.setStatus(status);
        complaintDTO.setRemark(remark);

        try{
            boolean result = false;

            switch (action) {

                case "update":
                    result = complaintDAO.updateComplaint(complaintDTO);
                    if(result){
                        resp.sendRedirect("Admin?status=success");
                    }else {
                        resp.sendRedirect("AdminDashboard.jsp?status=fail");
                    }
                    break;
                case "delete":
                    result = complaintDAO.deleteComplaint(id);
                    if(result){
                        resp.sendRedirect("Admin?status=success");
                    }else {
                        resp.sendRedirect("AdminDashboard.jsp?status=fail");
                    }
                    break;
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}