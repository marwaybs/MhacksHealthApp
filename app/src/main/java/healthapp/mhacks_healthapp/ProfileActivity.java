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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmail;
    private Button buttonLogout;

    private DatabaseReference databaseReference;

    private EditText editMedicationName, editTimesADay;
    private Button buttonSubmit;
    private Button buttonRetrieve;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        editMedicationName = (EditText) findViewById(R.id.medicationName);
        editTimesADay = (EditText) findViewById(R.id.timesADay);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);

        textViewUserEmail.setText("Welcome " + user.getEmail());
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        buttonRetrieve = (Button) findViewById(R.id.buttonRetrieve);

        buttonLogout.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);
        buttonRetrieve.setOnClickListener(this);

    }


    private void saveUserInformation() {
        String medicationName = editMedicationName.getText().toString().trim();
        String timesADay = editTimesADay.getText().toString().trim();
        UserInformation userInformation = new UserInformation(medicationName, timesADay);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this, "Information Save...", Toast.LENGTH_LONG).show();
    }

    private void retrieveUserInformation(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //UserInformation update = dataSnapshot.getValue(UserInformation.class);
                //System.out.println(update.getMedicationName());
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    String name = (String) messageSnapshot.child("medicationName").getValue();
                    String num = (String) messageSnapshot.child("timesADay").getValue();
                    System.out.println(name + " " + num);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (view == buttonSubmit){
            editMedicationName.setText("");
            saveUserInformation();
        }
        if (view == buttonRetrieve){
            retrieveUserInformation();
        }
    }
}