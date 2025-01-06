package ru.itis.listener;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.repositories.impl.CharacterRepositoryJdbcImpl;
import ru.itis.repositories.impl.FileRepositoryJdbcImpl;
import ru.itis.repositories.impl.PostsRepositoryJdbcImpl;
import ru.itis.repositories.impl.UserRepositoryJdbcImpl;

import ru.itis.repositories.interfaces.CharacterRepository;
import ru.itis.repositories.interfaces.FilesRepository;
import ru.itis.repositories.interfaces.PostsRepository;
import ru.itis.repositories.interfaces.UserRepository;
import ru.itis.services.impl.*;
import ru.itis.services.interfaces.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "123456";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/SemWorkDB";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(DB_DRIVER);
        ds.setUrl(DB_URL);
        ds.setUsername(DB_USERNAME);
        ds.setPassword(DB_PASSWORD);

        UserRepository userRepository = new UserRepositoryJdbcImpl(ds);
        PostsRepository postsRepository = new PostsRepositoryJdbcImpl(ds);
        CharacterRepository characterRepository  = new CharacterRepositoryJdbcImpl(ds);
        FilesRepository filesRepository = new FileRepositoryJdbcImpl(ds);

        servletContext.setAttribute("userRepository", userRepository);

        SignUpService signUpService = new SignUpServiceImpl(userRepository);
        servletContext.setAttribute("signUpService", signUpService);

        LogInService logInService = new LogInServiceIml(userRepository);
        servletContext.setAttribute("logInService", logInService);

        UsersService usersService = new UsersServiceIml(userRepository);
        servletContext.setAttribute("usersService", usersService);

        PostsService postsService = new PostServiceImpl(postsRepository);
        servletContext.setAttribute("postsService", postsService);

        CharacterService characterService = new CharacterServiceImpl(characterRepository);
        servletContext.setAttribute("characterService", characterService);

        FileService fileService = new FileServiceImpl(filesRepository);
        servletContext.setAttribute("fileService", fileService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
