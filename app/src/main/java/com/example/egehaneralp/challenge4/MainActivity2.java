package com.example.egehaneralp.challenge4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    TextView kul1skor,kul2skor,textKazanan;
    Button buttonTas,buttonKagit,buttonMakas,buttonReset;

    Random random;
    int count1,count2;

    Kullanici k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("kullanicilar");

        Kullanici kullanici1=new Kullanici();
        Kullanici kullanici2=new Kullanici();

        kul1skor=findViewById(R.id.kul1skor);
        kul2skor=findViewById(R.id.kul2skor);
        textKazanan=findViewById(R.id.kazanan);

        buttonTas=findViewById(R.id.buttonTas1);
        buttonKagit=findViewById(R.id.buttonKagit1);
        buttonMakas=findViewById(R.id.buttonMakas1);
        buttonReset=findViewById(R.id.buttonReset1);

        count1=0;count2=0;

        Intent i =getIntent();
        String isim = i.getStringExtra("ad");
        k=new Kullanici(isim);

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count1=0;count2=0;
                kul1skor.setText("1.Kullanıcı: "+count1);
                kul2skor.setText("2.Kullanıcı: "+count2);
            }
        });


        buttonTas.setOnClickListener(new View.OnClickListener() { //TAŞ ->0
            @Override
            public void onClick(View v) {

                //GİRİŞ YAPILAN NESNENİN VERİLERİNİ DEĞİŞTİRMEK!!!
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map<String,Object> bilgiler =new HashMap<>();
                        bilgiler.put("secim",1);
                        myRef.child(k.getAd()).updateChildren(bilgiler);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                random=new Random();
                int a= random.nextInt(3);// 0 - 1 -2 verir

                if(a==0){//berabere
                    Toast.makeText(MainActivity2.this, "Bot TAŞ Seçti",Toast.LENGTH_LONG).show();
                    textKazanan.setText("Berabere");
                }
                else if(a==1){ //kağıt yendi, 2.kullanici yendi
                    count2++;
                    Toast.makeText(MainActivity2.this, "Bot KAĞIT Seçti",Toast.LENGTH_LONG).show();
                    kul2skor.setText("2.Kullanici: "+count2);
                    textKazanan.setText("2.Kullanici Kazandı");
                }
                else if(a==2){ //taş yendi, 1.kullanıcı yendi
                    count1++;
                    Toast.makeText(MainActivity2.this, "Bot MAKAS Seçti",Toast.LENGTH_LONG).show();
                    kul1skor.setText("1.Kullanici :"+count1);
                    textKazanan.setText("1.Kullanici Kazandı");
                }

            }
        });

        buttonKagit.setOnClickListener(new View.OnClickListener() { //KAĞIT ->1
            @Override
            public void onClick(View v) {
                random=new Random();
                int a= random.nextInt(3);// 0 - 1 -2 verir

                if(a==0){//kağıt yendi, 1.kullanici kazandı
                    count1++;
                    Toast.makeText(MainActivity2.this, "Bot TAŞ Seçti",Toast.LENGTH_LONG).show();
                    kul1skor.setText("1.Kullanici :"+count1);
                    textKazanan.setText("1.Kullanici Kazandı");
                }
                else if(a==1){ //berabere
                    Toast.makeText(MainActivity2.this, "Bot KAĞIT Seçti",Toast.LENGTH_LONG).show();
                    textKazanan.setText("Berabere");
                }
                else if(a==2){ //makas yendi, 2.kullanıcı yendi
                    count2++;
                    Toast.makeText(MainActivity2.this, "Bot MAKAS Seçti",Toast.LENGTH_LONG).show();
                    kul2skor.setText("2.Kullanici :"+count2);
                    textKazanan.setText("2.Kullanici Kazandı");
                }
            }
        });

        buttonMakas.setOnClickListener(new View.OnClickListener() { //MAKAS ->2
            @Override
            public void onClick(View v) {
                random=new Random();
                int a= random.nextInt(3);// 0 - 1 -2 verir

                if(a==0){//taş yendi, 2. kullanıcı yendi
                    Toast.makeText(MainActivity2.this, "Bot TAŞ Seçti",Toast.LENGTH_LONG).show();
                    count2++;
                    kul2skor.setText("2.Kullanıcı: "+count2);
                    textKazanan.setText("2.Kullanici Kazandı");

                }
                else if(a==1){//makas yendi, 1. kullanıcı yendi
                    Toast.makeText(MainActivity2.this, "Bot KAĞIT Seçti",Toast.LENGTH_LONG).show();
                    count1++;
                    kul1skor.setText("1.Kullanici :"+count1);
                    textKazanan.setText("1.Kullanici Kazandı");

                }
                else if(a==2){//berabere
                    Toast.makeText(MainActivity2.this, "Bot MAKAS Seçti",Toast.LENGTH_LONG).show();
                    textKazanan.setText("Berabere");

                }
            }
        });

    }
}
