package jellyloment.smarthouse.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import jellyloment.smarthouse.R;

public class LoginActivity extends AppCompatActivity {
    private Button btnIn, btnReg;
    private EditText txtcorreo,txtcontra;
    private String txtMail,txtPass;

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIn = findViewById(R.id.btnIniciar);
        btnReg = findViewById(R.id.btnReg);
        txtcorreo = findViewById(R.id.edtUsuario);
        txtcontra = findViewById(R.id.edtContrasena);


        btnIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    ReadFromDatabase2();
                ReadFromDatabase();
                }
                catch(Exception e)
                {

                  Toast.makeText(getApplicationContext(),"Huvo un problema con la base de datos",Toast.LENGTH_LONG).show();
                }

            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
            }
        });
    }

    private void ReadFromDatabase2() {
        mDatabase2 = FirebaseDatabase.getInstance().getReference("password");
        mDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                txtPass = dataSnapshot1.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError1) {

            }
        });
    }


    private void ReadFromDatabase() {

        mDatabase = FirebaseDatabase.getInstance().getReference("user");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.getValue().toString();


                if (txtcorreo.getText().toString().equals(name)) {

                    //Toast.makeText(LoginActivity.this.getApplicationContext(),name, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(LoginActivity.this.getApplicationContext(),txtPass, Toast.LENGTH_SHORT).show();
                    if (txtcontra.getText().toString().equals(txtPass)) {

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        //txtcorreo.setText("");
                        txtcontra.setText("");
                    } else {
                        Toast.makeText(LoginActivity.this.getApplicationContext(), "Favor de insertar datos en ambos campos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this.getApplicationContext(), "Favor de insertar datos en ambos campos", Toast.LENGTH_SHORT).show();

                }
            }
                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }
            });
        }
    }
