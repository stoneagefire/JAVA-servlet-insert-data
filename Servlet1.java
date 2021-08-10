/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class Servlet1 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/root";
        String user1 = "istar";
        String password = "12345";
        Connection con = null;
        PreparedStatement ps = null;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String user = request.getParameter("uname");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        String course = request.getParameter("course");
        String check1 = request.getParameter("chk1");

        if (check1 != null) {
            if (check1.equals("checked")) {
                if (user.isEmpty() || pass.isEmpty()) {
                    out.println("Enter all details!");
                }
            } else {

                try {
                    Class.forName(driver);
                    con = DriverManager.getConnection(url, user1, password);
                    String qry = "insert into info values(?,?,?,?,?)";

                    ps = con.prepareStatement(qry);
                    ps.setString(1, name);
                    ps.setString(2, user);
                    ps.setString(3, pass);
                    ps.setString(4, email);
                    ps.setString(5, course);

                    int n = ps.executeUpdate();
                    if (n > 0) {
                        out.println("Registration Successful");
                        con.close();
                    }

                } catch (Exception e) {
                    out.print(e);
                }
            }
        } else {
            out.println("<h1>Kindly accept the terms and contion for further process");
        }
    }
}
