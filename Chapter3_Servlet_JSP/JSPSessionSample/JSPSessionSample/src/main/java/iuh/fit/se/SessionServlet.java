package iuh.fit.se;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "sessionServlet", value = "/session")
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Khi load trang lần đầu
        HttpSession session = req.getSession();
        List<String> todoList = (List<String>) session.getAttribute("todoList");
        if (todoList == null) {
            todoList = new ArrayList<>();
            session.setAttribute("todoList", todoList);
        }
        req.setAttribute("todoList", todoList);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Lấy session
        HttpSession session = req.getSession();

        // Lấy danh sách từ session
        List<String> todoList = (List<String>) session.getAttribute("todoList");
        if (todoList == null) {
            todoList = new ArrayList<>();
            session.setAttribute("todoList", todoList);
        }

        // Lấy item từ request
        String item = req.getParameter("item");
        if (item != null && !item.trim().isEmpty()) {
            todoList.add(item);
        }

        // Gửi lại về JSP
        req.setAttribute("todoList", todoList);
        req.getRequestDispatcher("/index.jsp")
                .forward(req, resp);

    }
}
