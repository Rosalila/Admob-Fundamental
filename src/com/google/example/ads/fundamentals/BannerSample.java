package com.google.example.ads.fundamentals;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * A simple {@link Activity} that embeds an AdView.
 */

public class BannerSample extends Activity {
  /** The view to show the ad. */
  private AdView adView;
  public SoundManager mSoundManager;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mSoundManager = new SoundManager();
    mSoundManager.initSounds(getBaseContext());
    mSoundManager.addSound(1, R.raw.sound);
    
    

    // Create an ad.
    adView = new AdView(this, AdSize.BANNER, "a14fd28a6ed31f2");

    // Add the AdView to the view hierarchy. The view will have no size
    // until the ad is loaded.
    LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
    layout.addView(adView);

    // Create an ad request. Check logcat output for the hashed device ID to
    // get test ads on a physical device.    
    AdRequest adRequest = new AdRequest();
    adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
    //TODO: change Test Device
    adRequest.addTestDevice("DBBDC3E9E4BBAA1CC7CC67720C2AAF30");

    // Start loading the ad in the background.
    adView.loadAd(adRequest);
  }

  /** Called before the activity is destroyed. */
  @Override
  public void onDestroy() {
    // Destroy the AdView.
	  mSoundManager.playSound(1);
    if (adView != null) {
      adView.destroy();
    }

    super.onDestroy();
  }
}
