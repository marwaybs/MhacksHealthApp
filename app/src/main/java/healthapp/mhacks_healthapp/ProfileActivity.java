package healthapp.mhacks_healthapp;

import android.content.Intent;
import android.provider.ContactsContract;
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

    private TextView textViewCurrentMedication;
    private Button buttonLogout;

    private DatabaseReference databaseReference;

    private Button buttonRetrieve;
    private Button buttonSchedule;





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

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome " + user.getEmail());
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        textViewCurrentMedication = (TextView) findViewById(R.id.textViewCurrentMedication);

        buttonRetrieve = (Button) findViewById(R.id.buttonRetrieve);
        buttonSchedule = (Button) findViewById(R.id.buttonSchedule);

        buttonLogout.setOnClickListener(this);
        buttonRetrieve.setOnClickListener(this);
        buttonSchedule.setOnClickListener(this);


    }


    private void retrieveUserInformation(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //UserInformation update = dataSnapshot.getValue(UserInformation.class);
                //System.out.println(update.getMedicationName());

                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    String name = (String) messageSnapshot.child("medicationName").getValue();
                    String doses = (String) messageSnapshot.child("dosesADay").getValue();
                    String start = (String) messageSnapshot.child("startDate").getValue();
                    String total = (String) messageSnapshot.child("totalDoses").getValue();
                    textViewCurrentMedication.setText("Medication Name: " + name + "\n Doses a day: " +
                            doses +"\n Start date: " + start +"\n Total doses: " + total);
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
        if (view == buttonRetrieve){
            retrieveUserInformation();
        }
        if (view == buttonSchedule){
            finish();
            startActivity(new Intent(this, SchedulingActivity.class));
        }
    }
}