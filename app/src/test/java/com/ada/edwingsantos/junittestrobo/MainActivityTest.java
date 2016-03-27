package com.ada.edwingsantos.junittestrobo;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
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

    @Test
    @Config(qualifiers = "es")
    public void localizedSpanish(){
        TextView textView = (TextView) activity.findViewById(R.id.tv_spanish);
        assertEquals(textView.getText().toString(), "Hola Mundo");

        /*

            org.junit.ComparisonFailure:
            Expected :Hola Mundo
            Actual   :Hola mMundo

         */
    }
    @Test
    @Config(qualifiers = "fr")
    public void localizedFrenchHelloWorld() {
        TextView tvHelloWorld = (TextView)activity.findViewById(R.id.tv_spanish);
        assertEquals(tvHelloWorld.getText().toString(), "Bonjour le monde!");
    }
}


/*
https://github.com/codepath/android_guides/wiki/Unit-Testing-with-Robolectric
 */