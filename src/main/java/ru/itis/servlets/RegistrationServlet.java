package ru.itis.servlets;

import ru.itis.dto.SignUpForm;
import ru.itis.services.interfaces.SignUpService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signUpService = (SignUpService) config.getServletContext().getAttribute("signUpService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/RegistrationPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpForm signUpForm = SignUpForm.builder()
                .nickname(req.getParameter("nickname"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        try {
            if (signUpService.validate(req.getParameter("email"))) {
                out.print("{\"success\": true}");
                resp.sendRedirect("/registration");
            } else {
                out.print("{\"success\": false}");
                signUpService.signUp(signUpForm);
                resp.sendRedirect("/login");
            }
            out.flush();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
