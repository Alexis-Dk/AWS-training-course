package com.aws.service.dagger.component;

import com.aws.service.IService;
import com.aws.service.dagger.ServiceModule;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = { ServiceModule.class })
public interface ServiceComponent {

	IService aggregate();
}
