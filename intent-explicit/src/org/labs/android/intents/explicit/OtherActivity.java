package org.labs.android.intents.explicit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by miguel on 5/19/13.
 */
public class OtherActivity extends Activity {

    /**
     * Sólo se procesa la primera vez que la actividad es creada
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Bundle extras = getIntent().getExtras();
        if(extras ==  null){
            return;
        }

        String usr = extras.getString("input1");
        String pwd = extras.getString("input2");

        if(usr!=null && pwd!=null){
            EditText tusr = (EditText) findViewById(R.id.tInput1);
            EditText tpwd = (EditText) findViewById(R.id.tInput2);

            tusr.setText(usr);
            tpwd.setText(pwd);
        }
    }

    public void onClick(View view){
        finish();
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("input1.key", "Respuesta KEY 1 desde Activity B");
        intent.putExtra("input2.key", 123456);

        setResult(RESULT_OK, intent);

        super.finish();
    }
}