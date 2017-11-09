package dev.uublabs.hw14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class EmiActivity extends AppCompatActivity
{
    private static final String TAG = "EmiActivity";

    private TextView tvLoan;
    private TextView tvRate;
    private TextView tvDuration;
    private TextView tvEmi;
    private SeekBar sbLoan;
    private SeekBar sbRate;
    private SeekBar sbDuration;
    private double emi, r, p, n;
    private float rate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi);

        tvLoan = findViewById(R.id.tvAmount);
        tvRate = findViewById(R.id.tvRate);
        tvDuration = findViewById(R.id.tvDuration);
        tvEmi = findViewById(R.id.tvEmi);
        sbLoan = findViewById(R.id.loanBar);
        sbRate = findViewById(R.id.rateBar);
        sbDuration = findViewById(R.id.durationBar);

        tvLoan.setText(sbLoan.getProgress()+"");
        tvRate.setText(sbRate.getProgress() + "");
        tvDuration.setText(sbDuration.getProgress() + "");
        emi = 0;
        p = sbLoan.getProgress();
        r = sbRate.getProgress();
        n = sbDuration.getProgress();
        calculateEMI();

        sbLoan.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            int progressValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                progressValue = progress;
                p = progressValue*1000.0;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                tvLoan.setText(progressValue+"k");
                calculateEMI();
            }
        });
        sbRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            int progressValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                progressValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                tvRate.setText(progressValue+"%");
                r = (progressValue/(1200.0));
                Log.d(TAG, "onStopTrackingTouch: "+r);
                calculateEMI();
            }
        });
        sbDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            int progressValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                progressValue = progress;
                n = progressValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                tvDuration.setText(progressValue+" Months");
                calculateEMI();
            }
        });

    }

    private void calculateEMI()
    {
        //[P x R x (1+R)^N]/[(1+R)^N-1]
        double x = p*r;
        double y = 1+r;
        double z = x* Math.pow(y, n);
        emi = z/(Math.pow(y,n)-1);
        emi=Math.round(emi *100);
        emi/=100;
        tvEmi.setText("Monthly EMI:  $"+emi);
    }
}
