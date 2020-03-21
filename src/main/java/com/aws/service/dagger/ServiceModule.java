package com.aws.service.dagger;

import com.aws.service.IService;
import com.aws.service.impl.Service;
import dagger.Provides;

import javax.inject.Singleton;

@dagger.Module
public class ServiceModule {

	@Provides
	@Singleton
	public IService service() {
		return new Service();
	}
}
