package com.korea.test.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public List<Note> getList() {
        return noteRepository.findAll();
    }

    public Note findById(Long targetNoteid) {
        return noteRepository.findById(targetNoteid).get();
    }

    public Note createNote() {
        Note note = new Note();
        note.setTitle("새노트");
        note.setCreateDate(LocalDateTime.now());
        noteRepository.save(note);
        return note;
    }

    public List<Note> getAllNote() {
        return noteRepository.findAll();
    }

    public void deleteNote(Long targetNoteid) {
        noteRepository.deleteById(targetNoteid);
    }
}
