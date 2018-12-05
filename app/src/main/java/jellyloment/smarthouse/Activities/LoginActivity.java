package jellyloment.smarthouse.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import jellyloment.smarthouse.R;

public class LoginActivity extends AppCompatActivity {
    private Button btnIn, btnReg;
    private EditText txtcorreo,txtcontra;

    private DatabaseReference mDatabase;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private String usuario = "";

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

    private void ReadFromDatabase() {
        mDatabase = FirebaseDatabase.getInstance().getReference(txtcorreo.getText().toString()).child("password");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    String valor = dataSnapshot.getValue().toString();
                    if (txtcontra.getText().toString().equals(valor)) {
                        try {
                            usuario = txtcorreo.getText().toString();
                            OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("usuario_iniciado.txt", Context.MODE_PRIVATE));

                            fout.write(usuario);
                            fout.close();
                        } catch (Exception ex) {
                            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
                        }
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                } catch (Exception ex2) {
                    Toast.makeText(LoginActivity.this.getApplicationContext(), "Error en credenciales", Toast.LENGTH_SHORT).show();
                }
            }
                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }
            });
        }
    }
