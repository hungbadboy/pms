/*
 * * Copyright 2017-2099 the original author or authors.
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
package com.tvo.pms.dao;

import java.util.List;

import com.tvo.pms.model.Role;
import com.tvo.pms.model.User;

/**
 * This is an interface user data access object
 * 
 * @author hungbadboy
 *
 */

public interface UserDAO {
	/**
	 * This is a function find user by email
	 * 
	 * @param email
	 * @return UserData
	 */
	public User findUserByEmail(String email);

	/**
	 * This is a function find user by email
	 * 
	 * @param email
	 * @return UserData
	 */
	public User findUserRoleByEmail(String email);
	
	/**
	 * This is a function store user
	 * 
	 * @param user
	 */
	public void saveUser(User user);
}
