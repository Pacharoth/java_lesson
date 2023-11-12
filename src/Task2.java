import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class Task2 implements Servlet  {
    ServletConfig config=null;
    @Override
    public void destroy() {
        System.out.println("Servlet is destroyed");
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    public String getServletInfo() {
        return "Page is created";
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config=config;
        System.out.println("Servlet initialize");
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
    //    PrintWriter out= resp.getWriter();
    //    out.print("<html><head><title>Hello world</title></head><body><h1>Hello bruh!</h1></body></html>");
        req.setAttribute("name", "dara");
        req.getRequestDispatcher("/web/task.jsp").forward(req, resp);
    }
    
}
