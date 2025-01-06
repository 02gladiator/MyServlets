package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.models.Post;
import ru.itis.services.interfaces.PostsService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/main")
public class MainPageServlet extends HttpServlet {

    private PostsService postsService;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        postsService = (PostsService) config.getServletContext().getAttribute("postsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/MainPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        int user_id = (int) session.getAttribute("user_id");

        if(title != null && content != null){
            Post post = Post.builder()
                    .title(title)
                    .content(content)
                    .userId(user_id)
                    .build();
            postsService.addPost(post);
            resp.sendRedirect("/main");
        }

        List<Post> posts = postsService.getPosts();
        String jsonPosts = mapper.writeValueAsString(posts);
        resp.setContentType("application/json");
        resp.getWriter().println(jsonPosts);
    }
}
