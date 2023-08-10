package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

	@Query("from Post p where p.id like ?1")
	Post findPostById(long id);

	@Modifying
	@Transactional
	@Query("UPDATE Post p SET p.title = :title, p.body = :body WHERE p.id = :id")
	void editPostById(@Param("title") String title,
					  @Param("body") String body,
					  @Param("id") long id);


	@Query("from Post p where p.title like %:term%")
	List<Post> searchByTitleLike(@Param("term") String term);

}
