package com.havayi.weekdays.fragments;


import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.support.design.widget.Snackbar;

import com.havayi.weekdays.R;

public class NotifyFragment extends Fragment {

    private static final String NOTIFY_TEXT = "seekbar value equals ";
    private static final String SEEKBAR_POSITION_TAG = "seekbar";

    private Button notifyButton;
    private Button toastButton;
    private Button snackbarButton;
    private SeekBar mSeekBar;

    public NotifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notify, container, false);
        notifyButton = (Button) rootView.findViewById(R.id.button_notification);
        toastButton = (Button) rootView.findViewById(R.id.button_toast);
        snackbarButton = (Button) rootView.findViewById(R.id.button_snackbar);
        mSeekBar = (SeekBar) rootView.findViewById(R.id.seek_bar);
        if(savedInstanceState != null)
            mSeekBar.setProgress(savedInstanceState.getInt(SEEKBAR_POSITION_TAG));
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), NOTIFY_TEXT + mSeekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

        snackbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, NOTIFY_TEXT + mSeekBar.getProgress(), Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationManager nm = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(getString(R.string.notification_title))
                        .setContentText(NOTIFY_TEXT + mSeekBar.getProgress())
                        .setAutoCancel(true)
                        .setVibrate(new long[]{1000 , 1000 , 1000})
                        .setSound(soundUri);

                nm.notify(R.id.button_notification, mBuilder.build());
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SEEKBAR_POSITION_TAG, mSeekBar.getProgress());
    }
}
