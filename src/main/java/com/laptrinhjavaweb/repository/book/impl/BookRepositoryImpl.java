package com.laptrinhjavaweb.repository.book.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.dto.BookDTO;
import com.laptrinhjavaweb.repository.book.BookRepositoryCustom;

@Repository
public class BookRepositoryImpl implements BookRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;
    
	@Override
	public List<?> findAll(BookDTO bookDTO, Pageable pageable) {
		StringBuilder sql = new StringBuilder("FROM BookEntity AS be");

		sql.append(" WHERE 1=1 ");
		if(StringUtils.isNotBlank(bookDTO.getCategoryName())) {
			sql.append("AND be.category.id = (SELECT id FROM CategoryEntity AS ce WHERE ce.name  LIKE '%" + bookDTO.getCategoryName() + "%')");
		}
        if (StringUtils.isNotBlank(bookDTO.getTitle())) {
            sql.append("AND LOWER(be.title) LIKE '%" + bookDTO.getTitle() + "%'");
        }
        if (bookDTO.getMinPrice() != 0) {
            sql.append("AND be.price >= " + bookDTO.getMinPrice());
        }
        if (bookDTO.getMaxPrice() != 0) {
            sql.append("AND be.price <= " + bookDTO.getMaxPrice());
        }
		 
        if(StringUtils.isNotBlank(bookDTO.getSortBy())) {
        	sql.append("ORDER BY " + bookDTO.getSortBy() + " DESC");
        }
        
        Query query = entityManager.createQuery(sql.toString());
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
	}

	@Override
	public Long getTotalItem(BookDTO bookDTO) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM BookEntity AS be");
        sql.append(" WHERE 1=1 ");
        if(StringUtils.isNotBlank(bookDTO.getCategoryName())) {
			sql.append("AND be.category.id = (SELECT id FROM CategoryEntity AS ce WHERE ce.name  LIKE '%" + bookDTO.getCategoryName() + "%')");
		}
        if (StringUtils.isNotBlank(bookDTO.getTitle())) {
            sql.append("AND LOWER(be.title) LIKE '%" + bookDTO.getTitle() + "%'");
        }
        if (bookDTO.getMinPrice() != 0) {
            sql.append("AND be.price >= " + bookDTO.getMinPrice());
        }
        if (bookDTO.getMaxPrice() != 0) {
            sql.append("AND be.price <= " + bookDTO.getMaxPrice());
        }

        Query query = entityManager.createQuery(sql.toString());
		/* System.out.println(sql.toString()); */
        return (Long) query.getSingleResult();
	}
}
