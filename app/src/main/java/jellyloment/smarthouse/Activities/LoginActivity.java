package jellyloment.smarthouse.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import jellyloment.smarthouse.R;

public class LoginActivity extends AppCompatActivity {
    private Button btnIn, btnReg;
    private EditText txtcorreo,txtcontra;
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

               ChecarUsuario();
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistroActivity.class));
            }
        });


        }
    public void ChecarUsuario(){
        if(txtcorreo.getText().toString().equals("") && txtcontra.getText().toString().equals(""))
        {
           Toast.makeText(this,"Favor de insertar datos en ambos campos",Toast.LENGTH_SHORT).show();
        }
        else {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }
}
