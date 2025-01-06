package ru.itis.servlets;


import ru.itis.models.FileInfo;
import ru.itis.services.interfaces.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/charicon")
public class IconsOutputServlet extends HttpServlet {
    private FileService fileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.fileService = (FileService) config.getServletContext().getAttribute("fileService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        int fileId = Integer.parseInt(req.getParameter("char_id"));

        FileInfo fileInfo = fileService.getFileInfo(fileId);

        response.setContentType(fileInfo.getType());

        response.setContentLength(fileInfo.getSize().intValue());

        response.setHeader("Content-Disposition", "filename=\"" + fileInfo.getOriginalFileName() + "\"");
        // записываем данные самого файла в ответ
        fileService.writeCharIconFromStorage(fileId, response.getOutputStream());
        response.flushBuffer();
    }
}
