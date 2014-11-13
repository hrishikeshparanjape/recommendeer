package com.rishi.app.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rishi.app.services.GenericUserItemDataSetService;

@Controller
@RequestMapping("/deer")
public class GenericUserItemRecommendationController {
	
	@Autowired GenericUserItemDataSetService dataSetService;
	
	@RequestMapping(value = "/train", method = RequestMethod.POST)
	public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String user = request.getParameter("user");
		String item = request.getParameter("item");
		String preference = request.getParameter("preference");
		if (user!=null && item!=null && preference!=null){
			dataSetService.createGenericUserItemDataSetEntry(Long.parseLong(user), Long.parseLong(item), Double.parseDouble(preference));
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request!");
		}
	}

	@RequestMapping(value = "/recommendations", method = RequestMethod.GET)
	public List<RecommendedItem> edit(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, TasteException {
		String user = request.getParameter("user");
		return dataSetService.getRecommendations(Long.parseLong(user));
	}
	
}
