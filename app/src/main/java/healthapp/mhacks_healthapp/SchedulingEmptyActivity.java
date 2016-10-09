package healthapp.mhacks_healthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SchedulingEmptyActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonSchedule;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling_empty);

        buttonSchedule = (Button) findViewById(R.id.buttonSchedule);
        buttonBack = (Button) findViewById(R.id.buttonBack);

        buttonSchedule.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSchedule){
            Toast.makeText(this, "Scheduled!", Toast.LENGTH_SHORT).show();

        }
        if(view == buttonBack){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
