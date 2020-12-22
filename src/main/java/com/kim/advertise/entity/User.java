package com.kim.advertise.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

 

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	@NotBlank
	@Size(max=120)
	private  String  fullName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	

	
	
	private  boolean   active=false;
	private  boolean   disabledbyAdmin=false;  
    @CreationTimestamp
    public LocalDateTime createdDate;
    @UpdateTimestamp
    public LocalDateTime  updatedTime;
    
    private  String   description;
    
  
    @OneToOne
    @JoinColumn(name = "fk_profilepicture")
    private  PictureUpload  profile_image;

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	
	public void addRole(Role r) {
		 roles.add(r);
	}
	public void removeRole(Role r) {
		 roles.remove(r);
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isDisabledbyAdmin() {
		return disabledbyAdmin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDisabledbyAdmin(boolean disabledbyAdmin) {
		this.disabledbyAdmin = disabledbyAdmin;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public  boolean   passwordEquals(String  newPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(encoder.matches(newPassword, this.password))  return  true;
		else return false;
	}
	public  void   changePassword(String  newPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		 this.password=encoder.encode(newPassword);
	}

	 

	public PictureUpload getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(PictureUpload profile_image) {
		this.profile_image = profile_image;
	}

	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}

	
 
	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}
	

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", roles="
				+ roles + ", active=" + active + ", disabledbyAdmin=" + disabledbyAdmin + ", createdDate=" + createdDate
				+ ", updatedTime=" + updatedTime + ", description=" + description + ", profile_image=" + profile_image
				+ "]";
	}

 
	
	
}
