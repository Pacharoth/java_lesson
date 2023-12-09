package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Todo;
import models.TodoPost;
import models.TodoStatus;
import netscape.javascript.JSObject;
@WebServlet("/todo")
public class TodoController extends HttpServlet {
    private String url = "jdbc:mysql://localhost:3306";
    private String db = "todos";
    private String table="todos";
    private String user="root";
    private String password ="" ;
    private String urlConnection(){
        // link url+db
        return String.format("%s/%s", url,db);
    }
    public void initDB(){
       Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password); 
            Statement stm = conn.createStatement();
            stm.executeUpdate(String.format("create database if not exists %s", db));
            stm.executeUpdate("use "+db+";");
            stm.executeUpdate(String.format("""
                create table if not exists %s (
                    id int auto_increment primary key,
                    title varchar(100),
                    description varchar(255),
                    status enum('complete','uncomplete') default 'uncomplete',
                    created_at datetime default current_timestamp,
                    updated_at datetime
                );
            """, table));
            stm.close();
            conn.close();
            System.err.println("Create database and table");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    public Connection useConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(urlConnection(), user, password);     
        } catch (Exception e) {
        }
        return conn;
    }
    @Override
    public void init() throws ServletException {
        //TODO: create table and database
        // todos todos
        super.init();
        initDB();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: listing API
        // uncomplete task
        resp.setContentType("application/json");
        JsonArray jsonArray = new JsonArray();
        PrintWriter out = resp.getWriter();
        try {
            Connection connection  =useConnection();
            Statement stm = connection.createStatement();
            ResultSet res;
            if(req.getParameter("search")!=null&&req.getParameter("search")!=""){
                String search=req.getParameter("search");
                res =stm.executeQuery(String.format("select * from %s where %s like %s or %s like %s", table,"title","'%"+search+"%'","description","'%"+search+"%'"));
            }else if(req.getParameter("status")!=null&&req.getParameter("status")!=""){
                res =stm.executeQuery(String.format("select * from %s where status = '%s' ", table,req.getParameter("status")));
            }
            else{
                res =stm.executeQuery(String.format("select * from %s", table));
            }
            // TODO: filter -> status->complete / uncomplete process and search 
            while (res.next()) {
                Todo todo = new Todo(res.getInt("id"),res.getString("title"),
                res.getString("description"),res.getString("status"),res.getDate("created_at"),
                res.getDate("updated_at"));
                jsonArray.add(new Gson().toJsonTree(todo));
            }   
            stm.close();
            connection.close();         
        } catch (Exception e) {
            System.err.println(e);
        }
        out.println(jsonArray);
        out.flush();

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        //TODO: GSON package for serialize our data
        TodoPost todoPost = new Gson().fromJson(req.getReader(), TodoPost.class);
        //TODO: initialize todo
        Todo todo = new Todo(todoPost.title,todoPost.description);
        //TODO: save to database TodoDAO
        try {
            Connection conn = useConnection();
            String insertRecordQuery = "insert into todos(title,description) values(?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertRecordQuery);
            preparedStatement.setString(1, todoPost.title);
            preparedStatement.setString(2, todoPost.description);
            preparedStatement.executeUpdate();
            Statement stm = conn.createStatement();
            ResultSet res=stm.executeQuery(String.format("select id from %s order by id desc limit 1 ", table));
            while (res.next()) {
                todo.setId(res.getInt("id"));
            }
            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        PrintWriter out = resp.getWriter();
        out.println(Todo.toJson(todo));
        out.flush();
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String idString = req.getParameter("id");
        try {
            Connection connection = useConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("delete from %s where id = %s", table,idString));
            statement.close();
            connection.close();
                    out.println("""
                            {"status":"Delete successful"}
                            """);

        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e);
            out.println("""
                    {"status": false}
                    """);
        }
    }
}
