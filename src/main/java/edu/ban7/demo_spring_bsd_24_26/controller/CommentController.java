package edu.ban7.demo_spring_bsd_24_26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.demo_spring_bsd_24_26.dao.CommentDao;
import edu.ban7.demo_spring_bsd_24_26.model.Comment;
import edu.ban7.demo_spring_bsd_24_26.view.CommentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    CommentDao commentDao;

    @GetMapping("/comment/list")
    List<Comment> getAll() {
        return commentDao.findAll();
    }

    @GetMapping("/comment/{id}")
    @JsonView(CommentView.class)
    ResponseEntity<Comment> get(@PathVariable int id) {

        Optional<Comment> optionalComment = commentDao.findById(id);

        //si le comment n'existe pas
        if(optionalComment.isEmpty()) {
            //return ResponseEntity.notFound();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //return ResponseEntity.ok(optionalComment.get());
        return new ResponseEntity<>(optionalComment.get(), HttpStatus.OK);

    }

    @PostMapping("/comment")
    ResponseEntity<Comment> create(@RequestBody Comment nouveauComment) {
        
        commentDao.save(nouveauComment);
        
        return new ResponseEntity<>(nouveauComment, HttpStatus.CREATED);
        
    }
    
    @DeleteMapping("/comment/{id}")
    ResponseEntity<Void> delete(@PathVariable int id) {
        
        Optional<Comment> optionalComment = commentDao.findById(id);

        //si le comment n'existe pas
        if(optionalComment.isEmpty()) {
            //return ResponseEntity.notFound();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        commentDao.deleteById(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    }

    @PutMapping("/comment/{id}")
    ResponseEntity<Void> update(
            @RequestBody Comment comment, 
            @PathVariable int id) {
        
        //on force l'id du comment à modifier à être l'id récupéré dans l'url
        comment.setId(id);

        Optional<Comment> optionalComment = commentDao.findById(id);

        //si le comment n'existe pas
        if(optionalComment.isEmpty()) {
            //return ResponseEntity.notFound();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        commentDao.save(comment);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    }

}
