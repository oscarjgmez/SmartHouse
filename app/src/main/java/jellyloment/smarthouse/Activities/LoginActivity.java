package jellyloment.smarthouse.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jellyloment.smarthouse.R;

public class LoginActivity extends AppCompatActivity {
    private Button btnIn, btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIn = findViewById(R.id.btnIniciar);
        btnReg = findViewById(R.id.btnReg);

        btnIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistroActivity.class));
            }
        });
    }
}
