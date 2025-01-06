package ru.itis.services.interfaces;

import ru.itis.models.Post;

import java.util.List;

public interface PostsService {
    void addPost(Post post);
    List<Post> getPosts();
    List<Post> getPostByUserId(int id);
}
