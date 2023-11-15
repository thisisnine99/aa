package com.korea.test.post;

import com.korea.test.note.Note;
import com.korea.test.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final NoteService noteService;

    @PostMapping("/write")
    public String write(Long targetNoteid) {
        Note targetNote = noteService.findById(targetNoteid);
        postService.writePost(targetNote);
        int size = postService.getAllPost().size() - 1;
        long lastNum = postService.getAllPost().get(size).getId();
        return "redirect:/post/" + lastNum;
    }

    @GetMapping("/{targetPostid}")
    public String detail(Model model, @PathVariable("targetPostid") Long targetPostid) {
        Post targetPost = postService.findById(targetPostid);

        model.addAttribute("noteList", noteService.getList());
        model.addAttribute("targetNote", targetPost.getNote());
        model.addAttribute("postList", targetPost.getNote().getPostList());
        model.addAttribute("targetPost", targetPost);
        return "main";
    }

    @PostMapping("/update")
    public String update(Long targetPostid, String title, String content) {
        if (title.isBlank()) {
            title = "제목없음";
        }
        postService.updatePost(targetPostid, title, content);

        return "redirect:/post/" + targetPostid;
    }

    @PostMapping("/delete")
    public String delete(Long targetPostid) {
        Note targetNote = postService.findById(targetPostid).getNote();
        if (postService.findById(targetPostid).getNote().getPostList().size() != 1) {
            postService.deletePost(targetPostid);
        }
        return "redirect:/note/" + targetNote.getId();
    }

}
