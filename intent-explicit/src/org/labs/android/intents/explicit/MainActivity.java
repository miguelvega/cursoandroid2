package org.labs.android.intents.explicit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        Intent intent = new Intent(this, OtherActivity.class);
        intent.putExtra("input1", "Input 1, enviado desde A");
        intent.putExtra("input2", "Input 2, para Activity B");

        // TIP. Se pede definir el 'request code' en cualquier lugar del codigo, asi se puede identificar
        // el 'callback' por medio de este
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            //estamos seguros de que este es el callback al evento de nuestro boton
            if(data.hasExtra("input1.key")){
                Toast.makeText(this, data.getExtras().getString("input1.key"), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Recibiendo un Integer: "+data.getExtras().getInt("input2.key"), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}