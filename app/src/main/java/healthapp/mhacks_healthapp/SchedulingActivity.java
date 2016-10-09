package healthapp.mhacks_healthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SchedulingActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    private TextView textViewScheduling;
    private EditText editMedicationName, editDosesAday, editStartDate, editTotalDoses;
    private Button buttonSchedule;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        editMedicationName = (EditText) findViewById(R.id.medicationName);
        editDosesAday = (EditText) findViewById(R.id.dosesADay);
        editStartDate = (EditText) findViewById(R.id.startDate);
        editTotalDoses = (EditText) findViewById(R.id.totalDoses);
        buttonSchedule = (Button) findViewById(R.id.buttonSchedule);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonSchedule.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
    }

    private void saveUserInformation() {
        String medicationName = editMedicationName.getText().toString().trim();
        String dosesADay = editDosesAday.getText().toString().trim();
        String startDate = editStartDate.getText().toString().trim();
        String totalDoses = editTotalDoses.getText().toString().trim();

        UserInformation userInformation = new UserInformation(medicationName, dosesADay, startDate, totalDoses);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this, "Scheduled!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        if (view == buttonSchedule){
            saveUserInformation();
            editMedicationName.setText("");
            editDosesAday.setText("");
            editStartDate.setText("");
            editTotalDoses.setText("");
        }
        if (view == buttonBack){
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }
}
