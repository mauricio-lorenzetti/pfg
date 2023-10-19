package com.example.measuredata;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class MainViewModel_Factory implements Factory<MainViewModel> {
  private final Provider<HealthServicesManager> healthServicesManagerProvider;

  public MainViewModel_Factory(Provider<HealthServicesManager> healthServicesManagerProvider) {
    this.healthServicesManagerProvider = healthServicesManagerProvider;
  }

  @Override
  public MainViewModel get() {
    return newInstance(healthServicesManagerProvider.get());
  }

  public static MainViewModel_Factory create(
      Provider<HealthServicesManager> healthServicesManagerProvider) {
    return new MainViewModel_Factory(healthServicesManagerProvider);
  }

  public static MainViewModel newInstance(HealthServicesManager healthServicesManager) {
    return new MainViewModel(healthServicesManager);
  }
}
