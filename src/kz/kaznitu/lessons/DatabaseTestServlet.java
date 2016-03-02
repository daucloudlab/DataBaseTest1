package kz.kaznitu.lessons;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = "/listUsers")
public class DatabaseTestServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter() ;

        try{
            Context initContext = new InitialContext() ;
            Context envContext = (Context)initContext.lookup("java:comp/env") ;
            DataSource ds = (DataSource)envContext.lookup("jdbc/UsersDB") ;
            Connection conn = ds.getConnection() ;

            Statement statement = conn.createStatement();
            String sql = "select username, email from users";
            ResultSet rs = statement.executeQuery(sql);

            int count = 1;
            while (rs.next()) {
                writer.println(String.format("User #%d: %-15s %s", count++,
                        rs.getString("username"), rs.getString("email")));

            }
        }catch (NamingException ex){
            System.err.println(ex);
        }catch (SQLException ex){
            System.err.println(ex) ;
        }
    }
}
