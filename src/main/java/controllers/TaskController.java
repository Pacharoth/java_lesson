package controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Todo;

@WebServlet("/todo")
public class TaskController extends HttpServlet {
    TreeMap<Integer,Todo> todos=new TreeMap<>(Collections.reverseOrder());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("todos", todos);
        req.getRequestDispatcher("hello.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String todo = req.getParameter("task");
        todos.put(todos.size()==0?todos.size()+1:todos.firstKey()+1,new Todo(todo, new Date(), new Date()));
        resp.sendRedirect(req.getContextPath()+"/todo");
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data=req.getReader().readLine();
        Integer id =Integer.parseInt(Todo.toMap(data).get("id")); 
        todos.remove(id);
    }

}
