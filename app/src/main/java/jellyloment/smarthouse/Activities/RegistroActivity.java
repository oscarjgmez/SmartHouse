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
            }
        });
    }
}
