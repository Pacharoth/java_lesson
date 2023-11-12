import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
@WebServlet("/params")
public class Task3 extends HttpServlet {
    TreeMap<Integer,User> users= new TreeMap<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/web/task_form.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        users.put(users.size()+1, new User(name, password));
        req.getRequestDispatcher("/web/welcome_page.jsp").forward(req, resp);
    }
}
