/*
 * Copyright (C) 2015 crDroid Android
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.settings.axxion;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;

import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class About extends SettingsPreferenceFragment {

    public static final String TAG = "About";

    private static final String KEY_AXXION_SHARE = "share";

    Preference mSourceUrl;
    Preference mGoogleUrl;
    Preference mDonateUrl;
    Preference mDownloadUrl;
	PreferenceScreen mSira;
	PreferenceScreen mIvan;
	PreferenceScreen mArt;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.axxion_about);

        mSourceUrl = findPreference("axxion_source");
        mGoogleUrl = findPreference("axxion_google_plus");
        mDonateUrl = findPreference("axxion_donate");
        mDownloadUrl = findPreference("axxion_download");
        mSira =  (PreferenceScreen) findPreference("sira");
        mIvan =  (PreferenceScreen) findPreference("ivan");
        mArt = (PreferenceScreen) findPreference("art");
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
		if (preference == mSourceUrl) {
            launchUrl("https://github.com/Axxion-Team");
        } else if (preference == mGoogleUrl) {
            launchUrl("https://plus.google.com/communities/106623648877858799989");
        } else if (preference == mDonateUrl) {
            launchUrl("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=96JY2KDZDEB32"); 
        } else if (preference == mDownloadUrl) {
            launchUrl("https://basketbuild.com/devs/AxxionTeam");
        } else if (preference.getKey().equals(KEY_AXXION_SHARE)) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, String.format(
                getActivity().getString(R.string.share_message), Build.MODEL));
        startActivity(Intent.createChooser(intent, getActivity().getString(R.string.share_chooser_title)));
        } else if (preference == mSira) {
            launchUrl("https://plus.google.com/u/0/+SiraCuervo");
        } else if (preference == mIvan) {
            launchUrl("https://plus.google.com/u/0/+IvanMartinezxda");
        } else if (preference == mArt) {
            launchUrl("https://plus.google.com/u/0/105642490289203630300");   
        }         
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    private void launchUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent donate = new Intent(Intent.ACTION_VIEW, uriUrl);
        getActivity().startActivity(donate);
    }
}
