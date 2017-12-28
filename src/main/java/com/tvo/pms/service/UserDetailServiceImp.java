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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tvo.pms.dao.UserDAO;
import com.tvo.pms.model.Role;
import com.tvo.pms.model.User;
import com.tvo.pms.model.UserDetailsImpl;

@Service
public class UserDetailServiceImp implements UserDetailsService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserDetailServiceImp.class);

	@Autowired
	private UserDAO usrDAO;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		LOGGER.debug("Authenticating user withdddddd username = {}",
				username.replaceFirst("@.*", "@.***"));
		username = username.replace("'", " ");
		User user = usrDAO.findUserRoleByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		List<GrantedAuthority> authorities = getAuthorities(user.getRole());
		return new UserDetailsImpl(user, authorities);
	}

	/**
	 * This method get roles
	 * 
	 * @param role
	 * @return
	 */
	private List<GrantedAuthority> getAuthorities(List<Role> lstRole) {
		List<GrantedAuthority> authList = new ArrayList<>();
		for (Role role : lstRole) {
			if (role.getRole() != null && role.getRole().trim().length() > 0) {
				if ("Admin".equals(role.getRole())) {
					authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				} else if ("User".equals(role.getRole())) {
					authList.add(new SimpleGrantedAuthority("ROLE_USER"));
				} else {
					authList.add(new SimpleGrantedAuthority("ROLE_GUEST"));
				}
			}
		}
		return authList;
	}

}
