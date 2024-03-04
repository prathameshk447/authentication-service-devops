package org.dnyanyog.service;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.Optional;

import org.dnyanyog.common.ErrorCode;
import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.encryption.EncryptionUtil;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService  {

	Logger logger = LoggerFactory.getLogger(UserManagementService.class);
	
	@Autowired
	UsersRepository userRepo; 
	
	@Autowired
	AddUserResponse userResponse;
	
	@Autowired
	private List<Long> userIds;
	
	@Autowired
	EncryptionUtil encryptionService;
	
	@Override
	public Optional<AddUserResponse> addUpdateUser(AddUserRequest request) throws Exception {

		Users usersTable = Users.getInstance()
				.setUserId(request.getUserId())
				.setAge(request.getAge())
				.setEmail(request.getEmail())
				.setPassword(encryptionService.encrypt(request.getPassword()))
				.setUsername(request.getUsername());

		usersTable = userRepo.save(usersTable); 
			
		userResponse.setStatus(ErrorCode.USER_FOUND.getStatus());
		userResponse.setMessage(ErrorCode.USER_FOUND.getMessage()); 
		userResponse.getUserData().setUserId(usersTable.getUserId()); 
		
		userResponse.getUserData().setEmail(usersTable.getEmail());
		userResponse.getUserData().setUsername(usersTable.getUsername());
		userResponse.getUserData().setAge(usersTable.getAge());

		return Optional.of(userResponse);
	}
	
	@Override
	public AddUserResponse getSingleUser(Long user_id) throws Exception {

		Optional<Users> receivedData = userRepo.findById(user_id);

		if (receivedData.isEmpty()) {
			userResponse.setStatus(ErrorCode.USER_NOT_FOUND.getStatus());
			userResponse.setMessage(ErrorCode.USER_NOT_FOUND.getMessage());
		} else {
			Users user = receivedData.get();	
					
			userResponse.setStatus(ErrorCode.USER_SEARCH.getStatus());
			userResponse.setMessage(ErrorCode.USER_SEARCH.getMessage());
			userResponse.getUserData().setUserId(user.getUserId());
			userResponse.getUserData().setEmail(user.getEmail());
			userResponse.getUserData().setUsername(user.getUsername());
			userResponse.getUserData().setAge(user.getAge());
		}
		return userResponse;
	}
	
	@Override
	public List<Users> getAllUser() {
		return userRepo.findAll();
	}
	
	@Override
	public List<Long> getAllUserIds() {

		List<Users> users = userRepo.findAll();

		for (Users user : users) {
			if (nonNull(user)) {
				userIds.add(user.getUserId());
			}
		}
		return userIds;
	}
	
}
