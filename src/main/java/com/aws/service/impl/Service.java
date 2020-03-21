package com.aws.service.impl;

import com.aws.db.dagger.DaggerDaoComponent;
import com.aws.db.dagger.DaoComponent;
import com.aws.service.IService;

public class Service implements IService {

	private final DaoComponent component;

	public Service() {
		component = DaggerDaoComponent.builder().build();
	}

	public String getTime() {
		return component.aggregate().getTime();
	}

}
