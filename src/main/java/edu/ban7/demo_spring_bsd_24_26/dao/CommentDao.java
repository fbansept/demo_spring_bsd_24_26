package edu.ban7.demo_spring_bsd_24_26.dao;


import edu.ban7.demo_spring_bsd_24_26.model.Comment;
import edu.ban7.demo_spring_bsd_24_26.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {
}
