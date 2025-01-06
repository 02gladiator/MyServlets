package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.models.Character;
import ru.itis.services.interfaces.CharacterService;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/characters")
@MultipartConfig
public class CharactersServlet extends HttpServlet {

    private CharacterService characterService;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        characterService = (CharacterService) config.getServletContext().getAttribute("characterService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/CharactersPage.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Character> characters = characterService.getCharacters();
        String jsonChars = mapper.writeValueAsString(characters);
        resp.setContentType("application/json");
        resp.getWriter().println(jsonChars);

    }
}