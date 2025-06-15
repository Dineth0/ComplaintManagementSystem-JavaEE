package lk.ijse.gdse;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.gdse.dao.ComplaintDAO;
import lk.ijse.gdse.dao.impl.ComplaintDAOImpl;
import lk.ijse.gdse.dto.ComplaintDTO;
import lk.ijse.gdse.dto.UserDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;

@WebServlet(name="ViewComplaintServlet", value = "/ComplainView")
public class ViewComplaintServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
//        ComplaintDTO dto = new ComplaintDTO();
//        dto.setId(req.getParameter("id"));
//
//        dto.setUserName(req.getParameter("userName"));
//        dto.setTitle(req.getParameter("title"));
//        dto.setComplaint(req.getParameter("complaint"));
//        dto.setDate(req.getParameter("date"));
//
//        req.setAttribute("complaint", dto);
//        HttpSession session = req.getSession();
//        UserDTO adminDTO = (UserDTO) session.getAttribute("admin");
//        System.out.println(adminDTO);
//        req.getRequestDispatcher("ViewComplaint.jsp").forward(req, resp);
//    }
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        ServletContext servletContext = req.getServletContext();
//        BasicDataSource bds = (BasicDataSource) servletContext.getAttribute("dataSource");
//
//        ComplaintDAO complaintDAO = new ComplaintDAOImpl(bds);
//
//        String action = req.getParameter("action");
//        String id = req.getParameter("id");
//        String userName = req.getParameter("userName");
//        String title = req.getParameter("title");
//        String complaint = req.getParameter("complaint");
//        String date = req.getParameter("date");
//
//
//
//
//        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("admin");
//
//
//
//        ComplaintDTO complaintDTO = new ComplaintDTO();
//        complaintDTO.setId(id);
//        complaintDTO.setUserName(userName);
//        complaintDTO.setTitle(title);
//        complaintDTO.setComplaint(complaint);
//        complaintDTO.setDate(date);
//
//        try{
//            boolean result = false;
//
//            switch (action) {
//                case "save":
//                    result = complaintDAO.saveComplaint(complaintDTO, userDTO.getId());
//                    break;
//                case "update":
//                    result = complaintDAO.updateComplaint(complaintDTO);
//                    break;
//                case "delete":
//                    result = complaintDAO.deleteComplaint(id);
//                    break;
//            }
//            if(result){
//                resp.sendRedirect("Complain?status=success");
//            }else {
//                resp.sendRedirect("ViewComplaint.jsp?status=fail");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }

}
