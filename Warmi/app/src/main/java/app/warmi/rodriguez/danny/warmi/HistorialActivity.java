package app.warmi.rodriguez.danny.warmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HistorialActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        btnAtras = (Button) findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnAtras:
                Intent intent = new Intent(HistorialActivity.this, MainActivity.class);
                startActivity(intent);
                break;
    }}
}
