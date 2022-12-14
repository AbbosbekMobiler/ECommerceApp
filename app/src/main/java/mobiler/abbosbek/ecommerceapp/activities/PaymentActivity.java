package mobiler.abbosbek.ecommerceapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mobiler.abbosbek.ecommerceapp.R;

public class PaymentActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView subTotal,disCount,shipping,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        toolbar = findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        double amount = 0.0;
        amount = getIntent().getDoubleExtra("amount",0.0);

        subTotal = findViewById(R.id.sub_total);
        disCount = findViewById(R.id.textView17);
        shipping = findViewById(R.id.textView18);
        total = findViewById(R.id.total_amt);

        subTotal.setText(amount+"$");


    }
}