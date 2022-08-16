package mobiler.abbosbek.ecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import mobiler.abbosbek.ecommerceapp.R;
import mobiler.abbosbek.ecommerceapp.adapter.MyCartAdapter;
import mobiler.abbosbek.ecommerceapp.models.MyCartModel;

public class CartActivity extends AppCompatActivity {

    TextView overAllAmount;
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<MyCartModel> cartModelList;
    MyCartAdapter myCartAdapter;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.my_cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver,new IntentFilter("MyTotalAmount"));

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        overAllAmount = findViewById(R.id.textView3);

        recyclerView = findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartModelList = new ArrayList<>();
        myCartAdapter = new MyCartAdapter(this,cartModelList);
        recyclerView.setAdapter(myCartAdapter);

        firebaseFirestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                MyCartModel myCartModel = doc.toObject(MyCartModel.class);
                                cartModelList.add(myCartModel);
                                myCartAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill = intent.getIntExtra("totalAmount",0);

            overAllAmount.setText("Total Amount : "+totalBill+"$");
        }
    };
}