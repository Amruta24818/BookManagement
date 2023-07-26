package com.library.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name="UserTable")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(unique = true)
	@NonNull
	private String name;

	@Column(unique = true)
	@NotNull
	private String email;

	@Column
	@NotNull
	private String password;

	@Column
	private String mobNo;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	public User() {
		super();
	}

	public User(Integer userId, String name, @NotNull String email, @NotNull String password, String mobNo,
			UserRole role) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobNo = mobNo;
		this.role = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", mobNo="
				+ mobNo + ", role=" + role + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(userId, user.userId) && name.equals(user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(mobNo, user.mobNo) && role == user.role;
	}
}
