
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import models.User;
import services.UserService;
/**
 *
 * @author Isaac Mhamed
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UserService us = new UserService();
        
        try{
            List<User> user = us.getAll();
            session.setAttribute("user", user);
        }
        catch(Exception ex){
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        String action = (String)request.getParameter("action");
        String Email = (String)request.getParameter("Email");
        
        if(action != null && action.equals("edit")){
            try {
                request.setAttribute("Email", Email);
                User editUser = us.getUser(Email);
                request.setAttribute("editUser", editUser);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("change", "edit");  
        }
        
        if(action != null && action.equals("delete")){
            request.setAttribute("message", "Delete test");
            request.setAttribute("Email", Email);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        
        //add user method
        if(action.equals("add")){
            String email = request.getParameter("email");
            String firstname = request.getParameter("Fname");
            String lastname = request.getParameter("Lname");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
        }
        
        //update user method
        if(action.equals("Update")){
            session.setAttribute("change", "update");
            request.setAttribute("message", "update test");
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        }
        
        //cancle update
        if(action.equals("Cancel")){
            session.setAttribute("change", "canceled");
            request.setAttribute("message", "cancel test");
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        }
        
        //delete user method  
    }

}
