package com.ada.edwingsantos.junittestrobo;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.internal.Shadow;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@Config(constants = BuildConfig.class, sdk = 21)

public class MainActivityTest {

    private MainActivity activity;
    @Before
    public void setup(){
        //activity = Robolectric.setupActivity(MainActivity.class);
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
    }
    @Test
    public void validateTextViewContent(){
        /**
         * This test will fail
         */
        TextView textView = (TextView) activity.findViewById(R.id.text_view1);
        assertNotNull("TextView Not Exists", textView);
        assertTrue("TextView ", "HLLO".equals(textView.getText().toString()));
    }
    @Test
    public void logInBtn(){
        /**
         * This test will pass
         */

        Button btn = (Button) activity.findViewById(R.id.btn_login);
        assertNotNull("Button Exits", btn);
        assertNotNull("Button", btn);
        assertTrue("Button", "Logins".equals(btn.getText().toString()));
    }

    @Test
    public void testForBtnClick(){
        /**
         *  android {
         *       useLibrary 'org.apache.http.legacy'
         * }
         *
         *  Test for the Btn to open an Activity
         *
         */

        activity.findViewById(R.id.btn_login).performClick();
        Intent expectedIntent = new Intent(activity, LoginActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}