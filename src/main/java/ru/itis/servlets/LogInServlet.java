package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.dto.LogInForm;
import ru.itis.services.interfaces.LogInService;
import ru.itis.services.interfaces.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/login")
public class LogInServlet extends HttpServlet {

    private LogInService logInService;
    public UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        logInService = (LogInService) config.getServletContext().getAttribute("logInService");
        usersService = (UsersService) config.getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/LoginPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LogInForm logInForm = LogInForm.builder()
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        try {
            if (logInService.logIn(logInForm)) {
                HttpSession session = req.getSession(true );
                session.setAttribute("authenticated", true);
                session.setAttribute("user_id", logInService.userId(logInForm));
                session.setAttribute("user_role", usersService.checkUserRole(logInForm));
                out.print("{\"success\": true}");
            }else {
                out.print("{\"success\": false}");
            }
            out.flush();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
