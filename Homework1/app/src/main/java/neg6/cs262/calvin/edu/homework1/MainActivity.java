package neg6.cs262.calvin.edu.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText Val1;
    private EditText Val2;
    private TextView Result;
    private Spinner op_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        op_spinner = findViewById(R.id.op_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        op_spinner.setAdapter(adapter);
        Val1 = findViewById(R.id.editText_val_1);
        Val2 = findViewById(R.id.editText_val_2);
        Result = findViewById(R.id.text_result);
    }

    public void Calculate(View view) {
        Val1 = findViewById(R.id.editText_val_1);
        Val2 = findViewById(R.id.editText_val_2);
        Result = findViewById(R.id.text_result);
        op_spinner = findViewById(R.id.op_spinner);
        //gets the integer values of both text boxes
        Integer num1;
        Integer num2;
        try {   //Make sure something is entered in the text boxes
            num1 = Integer.parseInt(Val1.getText().toString());
            num2 = Integer.parseInt(Val2.getText().toString());
        } catch (NumberFormatException e) {
            //Do nothing if either text box is empty
            return;
        }
        //Get the operator from the spinner
        String operator = op_spinner.getSelectedItem().toString();
        Integer result = 0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        Result.setText(result.toString());
    }
}
