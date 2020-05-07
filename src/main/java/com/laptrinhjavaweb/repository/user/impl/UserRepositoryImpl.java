package com.laptrinhjavaweb.repository.user.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.repository.user.UserRepositoryCustom;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<?> findAll(UserDTO userDTO, Pageable pageable) {
		StringBuilder sql = new StringBuilder("FROM UserEntity AS be");

		sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(userDTO.getUserName())) {
            sql.append("AND LOWER(be.userName) LIKE '%" + userDTO.getUserName() + "%'");
        }
        if (StringUtils.isNotBlank(userDTO.getEmail())) {
            sql.append("AND LOWER(be.email) LIKE '%" + userDTO.getEmail() + "%'");
        }
        if (StringUtils.isNotBlank(userDTO.getAddress())) {
        	sql.append("AND LOWER(be.address) LIKE '%" + userDTO.getAddress() + "%'");
        }
        if (StringUtils.isNotBlank(userDTO.getPhone())) {
        	sql.append("AND LOWER(be.phone) LIKE '%" + userDTO.getPhone() + "%'");
        }
		 /*
        if(StringUtils.isNotBlank(bookDTO.getSortBy())) {
        	sql.append("ORDER BY " + bookDTO.getSortBy() + " DESC");
        }
        */
        
        Query query = entityManager.createQuery(sql.toString());
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
	}

	@Override
	public Long getTotalItem(UserDTO userDTO) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM UserEntity AS be");
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(userDTO.getUserName())) {
            sql.append("AND LOWER(be.userName) LIKE '%" + userDTO.getUserName() + "%'");
        }
        if (StringUtils.isNotBlank(userDTO.getEmail())) {
            sql.append("AND LOWER(be.email) LIKE '%" + userDTO.getEmail() + "%'");
        }
        if (StringUtils.isNotBlank(userDTO.getAddress())) {
        	sql.append("AND LOWER(be.address) LIKE '%" + userDTO.getAddress() + "%'");
        }
        if (StringUtils.isNotBlank(userDTO.getPhone())) {
        	sql.append("AND LOWER(be.phone) LIKE '%" + userDTO.getPhone() + "%'");
        }

        Query query = entityManager.createQuery(sql.toString());
		/* System.out.println(sql.toString()); */
        return (Long) query.getSingleResult();
	}

}
