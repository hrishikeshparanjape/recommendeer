package com.rishi.app.repositories.post;

import com.rishi.app.models.GenericUserItemDataSet;

public interface GenericUserItemDataSetRepository {

	GenericUserItemDataSet create(GenericUserItemDataSet p);
	GenericUserItemDataSet update(GenericUserItemDataSet p);
	GenericUserItemDataSet delete(Long id);
	GenericUserItemDataSet find(Long id);
}

