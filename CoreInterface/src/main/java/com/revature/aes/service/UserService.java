package com.revature.aes.service;

import java.util.List;

import com.revature.aes.beans.User;

public interface UserService {
	public User findUserByEmail(String email);
	public User createCandidate(User candidate, String recruiterEmail);
	public List<User> findAllUsers();
	public List<User> findUsersByRecruiter(String email);
	public User findUserById(int id);
	public User findUserByIndex(int index, String email);
	public User updateCandidate(User candidate, String email, int index);
	public void removeCandidate(String email, int index);
}
