/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braininghub.webapphibernate.servlet;

/**
 *
 * @author kopig
 */
import com.braininghub.webapphibernate.dao.UserDAO;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class UserControllerServlet extends HttpServlet {
     
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        String userName = request.getParameter("userName");
        String password = request.getParameter("password1");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
 
        HttpSession session = request.getSession(true);
        try {
            UserDAO userDAO = new UserDAO();
            userDAO.addUserDetails(userName, password, email, phone, city);
            response.sendRedirect("Success");
        } catch (Exception e) {
 
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
    }
}
