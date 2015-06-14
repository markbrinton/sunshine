package com.brintsoft.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    private final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    private String mForecastStr = null ;

    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent intent = getActivity().getIntent() ;

        if( intent!=null && intent.hasExtra(Intent.EXTRA_TEXT) ) {
            mForecastStr = intent.getExtras().getString(Intent.EXTRA_TEXT) ;
            Log.d(LOG_TAG, "DetailActivity: extra text = " + mForecastStr) ;

            TextView tv = (TextView)view.findViewById(R.id.detail_text) ;
            tv.setText(mForecastStr);
        }

        return view ;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.detailfragment, menu);

        MenuItem shareItem = menu.findItem(R.id.action_share) ;
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem) ;

        if( shareActionProvider!=null ) {
            shareActionProvider.setShareIntent( createShareIntent() );
        }
    }

    private Intent createShareIntent() {
        String item = mForecastStr + " #SunshineApp" ;
        Log.d(LOG_TAG, "Sharing item: " + item) ;

        Intent shareIntent = new Intent();
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, item) ;


        return shareIntent ;
    }
}
