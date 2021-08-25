package com.dio.businesscard.core.di


import android.app.Application
import com.dio.businesscard.domain.di.DomainModule
import com.dio.businesscard.core.App
import com.dio.businesscard.data.di.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule


import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        MainActivityModuleBuilder::class,
        ContextModule::class,
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application):Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}