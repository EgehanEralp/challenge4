package com.example.egehaneralp.challenge4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    EditText adGiris;
    Button buttonGiris;
    int control=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("kullanicilar");

        adGiris=findViewById(R.id.adGiris1);
        buttonGiris=findViewById(R.id.button);

        buttonGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s=adGiris.getText().toString();
                final Kullanici k = new Kullanici(s);

                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(k.getAd()).exists()){
                            Toast.makeText(MainActivity.this, "Kullanici Mevcut", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(MainActivity.this,MainActivity2.class);
                            i.putExtra("ad",s);
                            startActivity(i);
                        }
                        else{
                            myRef.child(k.getAd()).setValue(k); //CHİLD ADI İÇİNE YAZILIR
                            Toast.makeText(MainActivity.this, "Giriş başarılı", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(MainActivity.this,MainActivity2.class);
                            i.putExtra("ad",s);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                Intent i=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
            }
        });

    }//onCreate sonu


}
