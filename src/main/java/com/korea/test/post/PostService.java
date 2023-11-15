package com.korea.test.post;

import com.korea.test.note.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public void writePost(Note targetNote) {
        Post post = new Post();
        post.setTitle("new title..");
        post.setContent("");
        post.setCreateDate(LocalDateTime.now());
        post.setNote(targetNote);
        postRepository.save(post);
    }

    public Post findById(Long targetPostid) {
        return postRepository.findById(targetPostid).get();
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public void updatePost(Long targetPostid, String title, String content) {
        Post targetPost = findById(targetPostid);
        targetPost.setTitle(title);
        targetPost.setContent(content);
        targetPost.setUpdateDate(LocalDateTime.now());
        postRepository.save(targetPost);
    }

    public void deletePost(Long targetPostid) {
        postRepository.deleteById(targetPostid);
    }

}
