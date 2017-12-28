/*
 * Copyright 2010, Alvin J. Alexander, http://devdaily.com.
 * 
 * This software is released under the terms of the
 * GNU LGPL license. See http://www.gnu.org/licenses/lgpl.html
 * for more information.
 * 
 * Usage:   java LicenseServer.jar [baseFilename]
 * 
 *          This results in reading in a file named "baseFilename.dat",
 *          and writing a filename named "baseFilename.lic".
 *          
 *          baseFilename will be in a format like "firstName-lastName-mmddhhmmss"
 * 
 */
package com.tvo.pms.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tvo.pms.model.User;

/**
 * This is implement user data access object
 * 
 * @author hungbadboy
 *
 */

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SqlSession sqlSession;

	
	/**
	 * {@inheritDoc}
	 */
	public User findUserByEmail(String email) {
		return this.sqlSession.selectOne("selectUserByUserName", email);

	}

	/**
	 * {@inheritDoc}
	 */
	public User findUserRoleByEmail(String email) {
		return this.sqlSession.selectOne("selectUserRoleByUserName", email);

	}

	/**
	 * {@inheritDoc}
	 */
	public void saveUser(User user) {

	}
}
