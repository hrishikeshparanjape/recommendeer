package com.rishi.app.repositories.post;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rishi.app.models.GenericUserItemDataSet;

@Repository
public class GenericUserItemDataSetRepositoryImpl implements GenericUserItemDataSetRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public GenericUserItemDataSet create(GenericUserItemDataSet p) {
		em.persist(p);
		return p;
	}

	@Override
	@Transactional
	public GenericUserItemDataSet delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public GenericUserItemDataSet find(Long id) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<GenericUserItemDataSet> criteriaQuery = criteriaBuilder.createQuery(GenericUserItemDataSet.class);
		Root<GenericUserItemDataSet> root = criteriaQuery.from( GenericUserItemDataSet.class );
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
		List <GenericUserItemDataSet> result = em.createQuery( criteriaQuery ).getResultList();
		if (result.size() == 1) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public GenericUserItemDataSet update(GenericUserItemDataSet p) {
		em.merge(p);
		return p;
	}
}
