package com.rishi.app.services;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;

import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishi.app.models.GenericUserItemDataSet;
import com.rishi.app.repositories.post.GenericUserItemDataSetRepository;

@Service
public class GenericUserItemDataSetService {

	@Autowired
	private DataSource dataSource;
	
	private static Logger log = LoggerFactory.getLogger(GenericUserItemDataSetService.class);

	@Autowired
	private GenericUserItemDataSetRepository dataSetRepository;

	public GenericUserItemDataSet createGenericUserItemDataSetEntry(long userId, long itemId, Double preference) {
		Calendar now = Calendar.getInstance();
		GenericUserItemDataSet p = new GenericUserItemDataSet();
		p.setUserId(userId);
		p.setItemId(itemId);
		p.setValue(preference);
		p.setCreateDate(now);
		p.setLastModified(now);
		dataSetRepository.create(p);
		return p;
	}
	
	public List<RecommendedItem> getRecommendations(long x) throws TasteException{
		JDBCDataModel model = new MySQLJDBCDataModel(dataSource,
				"GenericUserItemDataSet", "userId", "itemId", "value", null);
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		List<RecommendedItem> recommendations = recommender.recommend(x, 3);
		for (RecommendedItem recommendation : recommendations) {
		  System.out.println(recommendation);
		}
		return recommendations;
	}
}
