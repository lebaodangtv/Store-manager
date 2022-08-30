package com.websitebanhang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.websitebanhang.entitys.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
	Users findByUsername(String username);
	// lấy ra các user còn tồn tại chưa bị xóa
	List<Users> findByIsDeleted(Boolean isDeleted);
	// delete user
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE users SET isDeleted = 1 WHERE username = ?", nativeQuery = true)
	void deleteLogical(String username);
}
