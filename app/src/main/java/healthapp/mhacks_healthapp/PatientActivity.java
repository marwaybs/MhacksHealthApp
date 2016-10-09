package healthapp.mhacks_healthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view == buttonSubmit){
            finish();
            startActivity(new Intent(this, SchedulingEmptyActivity.class));
        }

    }
}