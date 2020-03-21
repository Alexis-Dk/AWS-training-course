package com.aws.db.dagger.module;

import com.aws.db.repository.IDao;
import com.aws.db.repository.impl.Dao;
import dagger.Provides;

import javax.inject.Singleton;

@dagger.Module
public class DaoModule {

	@Provides
	@Singleton
	public IDao service() {
		return new Dao();
	}
}
