package ru.itis.repositories.impl;

import ru.itis.models.Post;
import ru.itis.repositories.interfaces.PostsRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostsRepositoryJdbcImpl implements PostsRepository {

    private final DataSource dataSource;


    private static final String SQL_SAVE_POST = "INSERT INTO posts (title, content, user_id) VALUES (?,?,?)";
    private static final String SQL_GET_ALL_POSTS = "SELECT * FROM posts";
    private static final String SQL_UPDATE_POST = "UPDATE posts SET title = ?, content = ?, user_id = & WHERE post_id = ?";
    private static final String SQL_DELETE_POST = "DELETE FROM posts WHERE post_id = ?";
    private static final String SQL_SELECT_BY_USER_ID = "SELECT * FROM posts WHERE user_id = ?";

    public PostsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Post> findAll() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_POSTS);
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                Post post = Post.builder()
                        .title(resultSet.getString("title"))
                        .content(resultSet.getString("content"))
                        .userId(resultSet.getInt("user_id"))
                        .build();
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void save(Post post) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_POST);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setInt(3, post.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(Post post) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_POST);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setInt(3, post.getUserId());
            ps.setInt(4, post.getPostId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void remove(Post post) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_POST);
            ps.setInt(1, post.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Post> findAllByUserId(int userId) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BY_USER_ID);
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            List<Post> userPosts = new ArrayList<>();
            while (resultSet.next()) {
                Post post = Post.builder()
                        .title(resultSet.getString("title"))
                        .content(resultSet.getString("content"))
                        .userId(resultSet.getInt("user_id"))
                        .build();
                userPosts.add(post);
            }
            return userPosts;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
