package com.example.metaldetector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
//import java.util.Locale;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView t;
    private SensorManager s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s = (SensorManager) getSystemService(SENSOR_SERVICE);
        t = (TextView) findViewById(R.id.t);


    }


    @Override
    protected void onResume() {
        super.onResume();
        s.registerListener(this, s.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), s.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        s.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float magX = event.values[0];
            float magY = event.values[1];
            float magZ = event.values[2];
            double magnitude = Math.sqrt((magX * magX) + (magY * magY) + (magZ * magZ));
            Log.d("output",String.valueOf(magnitude));
            t.setText(String.valueOf(magnitude));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
