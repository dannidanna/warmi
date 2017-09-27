package app.warmi.rodriguez.danny.warmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnDenuncia;
    Button btnInstMaps;
    Button btnInstInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDenuncia = (Button) findViewById(R.id.btnDenuncia);
        btnDenuncia.setOnClickListener(this);
        btnInstMaps = (Button) findViewById(R.id.btnInstMaps);
        btnInstMaps.setOnClickListener(this);
        btnInstInfo = (Button) findViewById(R.id.btnInstInfo);
        btnInstInfo.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnDenuncia:
                Intent intent = new Intent(MainActivity.this, DenunciaActivity.class);
                startActivity(intent);
                break;
            case R.id.btnInstMaps:
                Intent intent1 = new Intent(MainActivity.this, InstMapsActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnInstInfo:
                Intent intent2 = new Intent(MainActivity.this, InstInfoActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
