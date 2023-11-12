import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class Task implements Servlet{
    ServletConfig config = null;
    public void init(ServletConfig config){
        this.config = config;
        System.out.println("servlet is initialized");
    }
    public void service(ServletRequest req, ServletResponse res ) throws IOException, ServletException{
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println(current);
      
        try {
            File myObj = new File("../index.jsp");
            Scanner reader = new Scanner(myObj);
            ArrayList<String>data= new ArrayList<>();
            while (reader.hasNextLine()) {
                data.add(reader.nextLine());
            }
            for (String string : data) {
                out.print(string);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.print(e);
        }
        try {
            File myObj = new File("../index.jsp");
            Scanner reader = new Scanner(myObj);
            ArrayList<String>data= new ArrayList<>();
            while (reader.hasNextLine()) {
                data.add(reader.nextLine());
            }
            for (String string : data) {
                out.print(string);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.print(e);
        }
    }
    public void destroy(){
        System.out.println("servlet is destroyed");
    }
    public ServletConfig getServletConfig(){
        return config;
    }
    public String getServletInfo(){
        return "copy right 2000-2010";
    }
}
        
