package jellyloment.smarthouse.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class AlertaActivity extends AppCompatActivity {

    private Button btnSi, btnNo;
    private String usuario = "", colonia = "";
    private DatabaseReference mDatabase;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta);

        btnSi = findViewById(R.id.btnPositivoA);
        btnNo = findViewById(R.id.btnNegativoA);

        try{
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("colonia_usuario.txt")));

            colonia = fin.readLine();
            fin.close();
            BufferedReader fin2 = new BufferedReader(new InputStreamReader(openFileInput("usuario_iniciado.txt")));

            usuario = fin.readLine();
            fin2.close();
        }catch (Exception ex){
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }

        btnSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference("data").child("42").child("value");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue().toString();
                        int valor = Integer.parseInt(value);
                        valor = valor + 1;
                        DatabaseReference myRef = database.getReference("data").child("42").child("value");
                        myRef.setValue(String.valueOf(valor));
                        finish();
                    }
                    @Override
                    public void onCancelled (@NonNull DatabaseError databaseError){

                    }
                });
            }
        });

        btnNo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DatabaseReference myRef = database.getReference(usuario).child("Alarma");
                myRef.setValue("false");
                return false;
            }
        });
    }
}
