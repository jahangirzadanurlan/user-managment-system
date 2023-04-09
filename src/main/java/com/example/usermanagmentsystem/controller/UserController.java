package com.example.usermanagmentsystem.controller;

import com.example.usermanagmentsystem.dao.UserDao;
import com.example.usermanagmentsystem.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@WebServlet("/")
public class UserController extends HttpServlet {
    private final UserDao userDao;
    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(req, resp);
                break;
            case "/insert":
                insertUser(req, resp);
                break;
            case "/delete":
                deleteUser(req, resp);
                break;
            case "/edit":
                editUser(req, resp);
                break;
            case "/update":
                updateUser(req,resp);
                break;
            default:
                listUser(req,resp);
                break;
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
    private void showNewForm(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("user-form.jsp");
        requestDispatcher.forward(req,resp);
    }
    private void insertUser(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String country= req.getParameter("country");

        User user=User.builder()
                .name(name)
                .email(email)
                .country(country)
                .build();
        userDao.registerUser(user);
        resp.sendRedirect("list");
    }
    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id=Long.parseLong(req.getParameter("id"));
        userDao.deleteUser(id);

        resp.sendRedirect("list");
    }
    private void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id=Long.parseLong(req.getParameter("id"));

        User existingUser;

        existingUser=userDao.selectUser(id);
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("user-form.jsp");
        req.setAttribute("user",existingUser);
        requestDispatcher.forward(req,resp);

    }
    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id=Long.parseLong(req.getParameter("id"));

        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String country=req.getParameter("country");

        User user=User.builder()
                .name(name)
                .email(email)
                .country(country)
                .build();
        userDao.updateUser(user);
        resp.sendRedirect("list");
    }
    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList=userDao.showUser();
        req.setAttribute("listUser",userList);
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("user-list.jsp");
        requestDispatcher.forward(req,resp);

    }

}
