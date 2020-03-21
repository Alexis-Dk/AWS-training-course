package com.aws.db.dagger;

import com.aws.db.dagger.module.DaoModule;
import com.aws.db.repository.IDao;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = { DaoModule.class })
public interface DaoComponent {

	IDao aggregate();
}
