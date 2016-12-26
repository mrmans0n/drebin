package io.nlopez.drebin.sample.util;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

public class Toaster {

  @Inject Context mContext;

  @Inject
  public Toaster() {

  }

  public void show(String message) {
    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
  }
}
