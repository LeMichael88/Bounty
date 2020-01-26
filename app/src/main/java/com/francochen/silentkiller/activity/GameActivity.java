package com.francochen.silentkiller.activity;


import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.francochen.silentkiller.R;

import java.util.ArrayList;

import ca.uol.aig.fftpack.RealDoubleFFT;

public class GameActivity extends AppCompatActivity {

    //vars
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mTime = new ArrayList<>();
    private static final double[] CANCELLED = {100};
    RecordAudio recordTask;
    AudioRecord audioRecord;
    private RealDoubleFFT transformer;
    int blockSize = /*2048;// = */256;
    int mPeakPos;
    double mHighestFreq;
    boolean started = false;
    boolean CANCELLED_FLAG = false;
    double[][] cancelledResult = {{100}};
    int width;
    int height;

    int frequency = 8000; /*44100;*/
    int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        initInfo();

        Button capture = findViewById(R.id.captureButton);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.hz21000);
        capture.setOnClickListener(view -> {
            mp.start();
        });

        start();
    }

    private void initInfo(){

        mName.add("Kevin");
        mTime.add("1 min ago");

        mName.add("Kevin");
        mTime.add("1 min ago");

        mName.add("Kevin");
        mTime.add("1 min ago");

        mName.add("Kevin");
        mTime.add("1 min ago");

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mName, mTime);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private class RecordAudio extends AsyncTask<Void, double[], Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            int bufferSize = AudioRecord.getMinBufferSize(frequency,
                    channelConfiguration, audioEncoding);
            audioRecord = new AudioRecord(
                    MediaRecorder.AudioSource.DEFAULT, frequency,
                    channelConfiguration, audioEncoding, bufferSize);
            int bufferReadResult;
            short[] buffer = new short[blockSize];
            double[] toTransform = new double[blockSize];
            try {
                audioRecord.startRecording();
            } catch (IllegalStateException e) {
                Log.e("Recording failed", e.toString());

            }
            while (started) {

                if (isCancelled() || (CANCELLED_FLAG == true)) {

                    started = false;
                    publishProgress(cancelledResult);
                    Log.d("doInBackground", "Cancelling the RecordTask");
                    break;
                } else {
                    bufferReadResult = audioRecord.read(buffer, 0, blockSize);

                    for (int i = 0; i < blockSize && i < bufferReadResult; i++) {
                        toTransform[i] = (double) buffer[i] / 32768.0; // signed 16 bit
                    }

                    transformer.ft(toTransform);

                    publishProgress(toTransform);

                }

            }
            return true;
        }
        @Override
        protected void onProgressUpdate(double[]...progress) {
            Log.e("RecordingProgress", "Displaying in progress");
            double mMaxFFTSample = 150.0;

            Log.d("Test:", Integer.toString(progress[0].length));
            if(progress[0].length == 1 ){

                Log.d("FFTSpectrumAnalyzer", "onProgressUpdate: Blackening the screen");

            }

            else {
                if (width > 512) {
                    for (int i = 0; i < progress[0].length; i++) {
                        int x = 2 * i;
                        int downy = (int) (150 - (progress[0][i] * 10));
                        int upy = 150;
                        if(downy < mMaxFFTSample)
                        {
                            mMaxFFTSample = downy;
                            //mMag = mMaxFFTSample;
                            mPeakPos = i;
                        }

                    }

                } else {
                    for (int i = 0; i < progress[0].length; i++) {
                        int x = i;
                        int downy = (int) (150 - (progress[0][i] * 10));
                        int upy = 150;
                        if(downy < mMaxFFTSample)
                        {
                            mMaxFFTSample = downy;
                            //mMag = mMaxFFTSample;
                            mPeakPos = i;
                        }
                    }
                }
            }


        }
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            try{
                audioRecord.stop();
            }
            catch(IllegalStateException e){
                Log.e("Stop failed", e.toString());
            }
        }
    }

    protected void onCancelled(Boolean result){

        try{
            audioRecord.stop();
        }
        catch(IllegalStateException e){
            Log.e("Stop failed", e.toString());

        }
        //recordTask.cancel(true);

        Log.d("FFTSpectrumAnalyzer","onCancelled: New Screen");
    }

    public void start() {
        if (started == true) {
            //started = false;
            CANCELLED_FLAG = true;
            //recordTask.cancel(true);
            try{
                audioRecord.stop();
            }
            catch(IllegalStateException e){
                Log.e("Stop failed", e.toString());

            }

            //show the frequency that has the highest amplitude...
            mHighestFreq = (((1.0 * frequency) / (1.0 * blockSize)) * mPeakPos)/2;
            String str = "Frequency for Highest amplitude: " + mHighestFreq;
        }

        else {
            started = true;
            CANCELLED_FLAG = false;
            recordTask = new RecordAudio();
            recordTask.execute();
        }

    }

    public void onStop(){
        super.onStop();
        	/*started = false;
            startStopButton.setText("Start");*/
        //if(recordTask != null){
        recordTask.cancel(true);
        //}
    }
}


