package lk.ijse.gdse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.gdse.dto.ComplaintDTO;
import lk.ijse.gdse.dto.UserDTO;

import java.io.IOException;

@WebServlet(name = "ComplaintViewServlet", value = "/ComplaintViewServlet")
public class ComplaintViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        ComplaintDTO dto = new ComplaintDTO();
        dto.setId(req.getParameter("id"));
        dto.setUserName(req.getParameter("userName"));
        dto.setTitle(req.getParameter("title"));
        dto.setComplaint(req.getParameter("complaint"));
        dto.setDate(req.getParameter("date"));

        req.setAttribute("complaint", dto);
        HttpSession session = req.getSession();
        UserDTO adminDTO = (UserDTO) session.getAttribute("admin");
        if (adminDTO != null) {
            // Temporarily set user session as admin to allow access
            session.setAttribute("user", adminDTO);
        }
        req.getRequestDispatcher("UserDashboard.jsp").forward(req, resp);
    }
}
