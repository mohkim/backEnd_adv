package com.kim.advertise.Repository.post;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.User;
import com.kim.advertise.entity.post.EPostStatus;
import com.kim.advertise.entity.post.Post;

 

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
// common 
 public 	Post[] findByUser(User user);
 
 //for geneal User
 
 @Query("select t from Post t where t.post_status.status=?1 "
	 		+ "   order by  t.post_status.status.approved_date DESC ")
	 public     Post[]  findAllActivePost(EPostStatus status);
 
 @Query("select t from Post t where t.post_status.status=?1 and t.id=?2 "
	 		+ "   order by  t.post_status.status.approved_date DESC ")
 public Post findActivePostById(EPostStatus active, Long id);
 
 @Query("select t from Post t where t.post_status.status=?1 and t.user.id=?2 "
	 		+ "   order by  t.post_status.status.approved_date DESC ")
 public Post[] findActivePostByUser(EPostStatus active, Long id);
 
 //
 
 @Query("select t from Post t where t.post_status.status=?1 "
	 		+ "   order by  t.post_status.status.approved_date DESC ")
	 public     Post[]  findPostByStatusAproved(EPostStatus status);
 @Query("select t from Post t where t.post_status.status=?1 "
	 		+ "   order by  t.post_status.status.apply_date DESC ")
	 public     Post[]  findPostByStatusPending(EPostStatus status);
 @Query("select t from Post t where t.post_status.status=?1 "
	 		+ "   order by  t.post_status.status.rejected_date DESC ")
	 public     Post[]  findPostByStatusError(EPostStatus status);
 @Query("select t from Post t where t.post_status.status=?1 "
	 		+ "   order by  t.post_status.status.disabled_date DESC ")
	 public     Post[]  findPostByStatusDisabled(EPostStatus status);
 
 //for user only 
 @Query("select t from Post t where t.post_status.status=?1 and t.user.id=?2 ")
public Post[] findPostByStatusOfUser(EPostStatus status, Long uid);

 
 // for   managment only
 
 @Query("select t from Post t where t.post_status.status=?1 and t.id=?2 ")
public Post findPostByStatusAndId(EPostStatus status, Long id);

 @Query("select t from Post t where t.post_status.status=?1 and t.post_status.status.rejectedByUser.id=?2 "
 		+ " order by  t.post_status.status.rejected_date DESC ")
public Post[] findPostRejectedByUser(EPostStatus error, Long id);
 
 @Query("select t from Post t where t.post_status.status=?1 and t.post_status.status.approvedByUser.id=?2 "
 		+ " order by  t.post_status.status.approved_date  DESC")
public Post[] findPostAcceptedByUser(EPostStatus accept, Long id);




 
 
 /// for   admin only
 
	 
}
