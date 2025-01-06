package ru.itis.repositories.interfaces;

import ru.itis.models.Post;

import java.util.List;

public interface PostsRepository extends CrudRepository<Post>{
    List<Post> findAllByUserId(int userId);
}
