package ru.itis.servlets;

import ru.itis.models.Character;

import ru.itis.services.interfaces.CharacterService;
import ru.itis.services.interfaces.FileService;


import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;



@WebServlet("/addcharacters")
@MultipartConfig
public class AddNewCharServlet extends HttpServlet {

    private CharacterService characterService;
    private FileService fileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        characterService = (CharacterService) config.getServletContext().getAttribute("characterService");
        fileService = (FileService) config.getServletContext().getAttribute("fileService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/CharactersPage.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Character character = Character.builder()
                .characterName(req.getParameter("character-name"))
                .element(req.getParameter("character-element"))
                .weapon(req.getParameter("character-weapon"))
                .characterDescription(req.getParameter("character-description"))
                .region(req.getParameter("character-region"))
                .build();
        Part part = req.getPart("character-icon");
        characterService.addCharacter(character);
        int char_id = characterService.getCharId(req.getParameter("character-name"));
        fileService.saveCharIcon(part.getInputStream(),part.getSubmittedFileName(),part.getContentType(),part.getSize(),char_id);
        resp.sendRedirect("/admin");


    }
}