package com.kim.advertise.entity.post;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.kim.advertise.entity.User;

 

@Entity
 
public class Post_Status {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private   EPostStatus  status;
	
	@CreationTimestamp
     private LocalDateTime apply_date;
     
     @ManyToOne
     @JoinColumn(name = "fk_rejectByUser")
     private  User   rejectedByUser;
     
     @Column(length = 10485760)
     private  String  rejectionReason;
     
     private LocalDateTime rejected_date;
    
     @ManyToOne
     @JoinColumn(name = "fk_approvedByUser")
     private  User   approvedByUser;
 
     private LocalDateTime approved_date;
     
     @ManyToOne
     @JoinColumn(name = "fk_DisabledByUser")
     private  User   diasabledByUser;
     
     private LocalDateTime  disabled_date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

 
	public EPostStatus getStatus() {
		return status;
	}

	public void setStatus(EPostStatus status) {
		this.status = status;
	}

	public LocalDateTime getApply_date() {
		return apply_date;
	}

	public void setApply_date(LocalDateTime apply_date) {
		this.apply_date = apply_date;
	}

	public User getRejectedByUser() {
		return rejectedByUser;
	}

	public void setRejectedByUser(User rejectedByUser) {
		this.rejectedByUser = rejectedByUser;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public LocalDateTime getRejected_date() {
		return rejected_date;
	}

	public void setRejected_date(LocalDateTime rejected_date) {
		this.rejected_date = rejected_date;
	}

	public User getApprovedByUser() {
		return approvedByUser;
	}

	public void setApprovedByUser(User approvedByUser) {
		this.approvedByUser = approvedByUser;
	}

	public LocalDateTime getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(LocalDateTime approved_date) {
		this.approved_date = approved_date;
	}

 	public LocalDateTime getDisabled_date() {
		return disabled_date;
	}

	public void setDisabled_date(LocalDateTime disabled_date) {
		this.disabled_date = disabled_date;
	}

	public User getDiasabledByUser() {
		return diasabledByUser;
	}

	public void setDiasabledByUser(User diasabledByUser) {
		this.diasabledByUser = diasabledByUser;
	}
     
	public Post_Status(Long id, EPostStatus status, LocalDateTime apply_date, User rejectedByUser,
			String rejectionReason, LocalDateTime rejected_date, User approvedByUser, LocalDateTime approved_date,
			User diasabledByUser, LocalDateTime disabled_date) {
		super();
		this.id = id;
		this.status = status;
		this.apply_date = apply_date;
		this.rejectedByUser = rejectedByUser;
		this.rejectionReason = rejectionReason;
		this.rejected_date = rejected_date;
		this.approvedByUser = approvedByUser;
		this.approved_date = approved_date;
		this.diasabledByUser = diasabledByUser;
		this.disabled_date = disabled_date;
	}

	public Post_Status() {
		super();
	} 
	
     
    
     
}