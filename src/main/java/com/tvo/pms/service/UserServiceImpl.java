/*
 * Copyright 2017-2099 the original author or authors.
 *
 * Licensed under the TVO License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.tvo.com.vn/licenses/LICENSE-1.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tvo.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tvo.pms.dao.UserDAO;
import com.tvo.pms.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * {@inheritDoc}
	 */
	public User findUserByEmail(String email) {
		return userDao.findUserRoleByEmail(email);
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		// Role userRole = roleRepository.findByRole("ADMIN");
		// user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userDao.saveUser(user);
	}
}
