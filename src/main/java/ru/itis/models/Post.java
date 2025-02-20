package ru.itis.models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private int postId;
    private String title;
    private String content;
    private int userId;
}
