package io.nlopez.drebin.sample.places;

import revamp.base.BusinessObject;

public class PlacesBO implements BusinessObject {
  private final int mSectionNumber;

  public PlacesBO(int sectionNumber) {
    mSectionNumber = sectionNumber;
  }

  public int getSectionNumber() {
    return mSectionNumber;
  }
}
