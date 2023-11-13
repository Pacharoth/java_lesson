package controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Todo;
import utils.TodoMessage;

@WebServlet("/todo")
public class TaskController extends HttpServlet {
    TreeMap<Integer, Todo> todos = new TreeMap<>(Collections.reverseOrder());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TreeMap<Integer, Todo> mapTodos = new TreeMap<Integer, Todo>();
        // HttpSession user =  req.getSession();
        // user.getAttribute("");
        // user.setAttribute(LEGACY_DO_HEAD, user);
        String search = req.getParameter("search");
        boolean conditionSearch = search != null && search != "";
        if (conditionSearch) {
            mapTodos = Todo.search(todos, search);
        } else {
            mapTodos = todos;
        }
        if (mapTodos.size() == 0 && conditionSearch) {
            mapTodos.put(1, new Todo(TodoMessage.noResult, new Date(), new Date()));
        } else if (mapTodos.size() == 0) {
            mapTodos.put(1, new Todo(TodoMessage.noMessage, new Date(), new Date()));
        } else {
            if (mapTodos.size() > 1) {
                Map.Entry<Integer, Todo> deleteNoMessage = Todo.search(mapTodos, TodoMessage.noMessage).firstEntry();
                Map.Entry<Integer, Todo> deleteNoResult = Todo.search(mapTodos, TodoMessage.noResult).firstEntry();
                if (deleteNoMessage != null) {
                    mapTodos.remove(deleteNoMessage.getKey());
                }
                if (deleteNoResult != null) {
                    mapTodos.remove(deleteNoResult.getKey());
                }
            }
        }
        req.setAttribute("todos", mapTodos);
        req.getRequestDispatcher("hello.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String todo = req.getParameter("task");
        todos.put(todos.size() == 0 ? todos.size() + 1 : todos.firstKey() + 1, new Todo(todo, new Date(), new Date()));
        resp.sendRedirect(req.getContextPath() + "/todo");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = req.getReader().readLine();
        Integer id = Integer.parseInt(Todo.toMap(data).get("id"));
        todos.remove(id);
    }

}
