package ru.itis.services.impl;

import ru.itis.models.Post;
import ru.itis.repositories.interfaces.PostsRepository;
import ru.itis.services.interfaces.PostsService;

import java.util.List;

public class PostServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    public PostServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public void addPost(Post post) {
        postsRepository.save(post);
    }

    @Override
    public List<Post> getPosts() {
        return postsRepository.findAll();
    }

    @Override
    public List<Post> getPostByUserId(int id) {
        return postsRepository.findAllByUserId(id);
    }

}
