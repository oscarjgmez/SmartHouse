package jellyloment.smarthouse.Activities;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.OutputStreamWriter;

import jellyloment.smarthouse.R;

public class RegistroActivity extends AppCompatActivity {

    private Button btnAceptar;
    private EditText edtUs, edtPass, edtCol;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtUs = findViewById(R.id.edtUsuarioR);
        edtPass = findViewById(R.id.edtContrasenaR);
        edtCol = findViewById(R.id.edtColoniaR);
        btnAceptar = findViewById(R.id.btnAceptarR);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String usuarioNuevo = edtUs.getText().toString();
                DatabaseReference myRef = database.getReference("" + usuarioNuevo).child("password");
                myRef.setValue(edtPass.getText().toString());
                DatabaseReference myRefC = database.getReference("" + usuarioNuevo).child("colonia");
                myRefC.setValue(edtCol.getText().toString());

                DatabaseReference sim = database.getReference(usuarioNuevo).child("Simulacion");
                sim.setValue("0");

                DatabaseReference led1 = database.getReference(usuarioNuevo).child("Focos").child("Foco1");
                DatabaseReference led2 = database.getReference(usuarioNuevo).child("Focos").child("Foco2");
                DatabaseReference led3 = database.getReference(usuarioNuevo).child("Focos").child("Foco3");
                DatabaseReference led4 = database.getReference(usuarioNuevo).child("Focos").child("Foco4");
                led1.setValue("false");
                led2.setValue("false");
                led3.setValue("false");
                led4.setValue("false");

                DatabaseReference coc = database.getReference(usuarioNuevo).child("Sensores").child("Cocina");
                DatabaseReference cua = database.getReference(usuarioNuevo).child("Sensores").child("Cuarto");
                DatabaseReference pue = database.getReference(usuarioNuevo).child("Sensores").child("Puerta");
                DatabaseReference pueIO = database.getReference(usuarioNuevo).child("Sensores").child("PuertaIO");
                DatabaseReference ven = database.getReference(usuarioNuevo).child("Sensores").child("Ventana");
                DatabaseReference venIO = database.getReference(usuarioNuevo).child("Sensores").child("VentanaIO");
                coc.setValue("false");
                cua.setValue("false");
                pue.setValue("false");
                pueIO.setValue("false");
                ven.setValue("false");
                venIO.setValue("false");
            }
        });
    }
}
