package io.nlopez.drebin.sample;

import java.util.List;

import javax.inject.Inject;

import io.nlopez.drebin.sample.util.DataGenerator;
import revamp.base.BusinessObject;

public class MainBO implements BusinessObject {

  @Inject DataGenerator mDataGenerator;

  @Inject
  public MainBO() {
  }

  public List getElements() {
    return mDataGenerator.generateMix(200);
  }
}
