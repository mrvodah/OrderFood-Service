package com.example.vietvan.androideatitserver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.vietvan.androideatitserver.Common.Common;
import com.example.vietvan.androideatitserver.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignIn extends AppCompatActivity {

    private static final String TAG = "TAG";
    @BindView(R.id.edtPhone)
    MaterialEditText edtPhone;
    @BindView(R.id.edtPassword)
    MaterialEditText edtPassword;

    FirebaseDatabase database;
    DatabaseReference signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        signIn = database.getReference("User");

    }

    @OnClick(R.id.si_btnSignIn)
    public void onViewClicked() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please waiting ...");
        progressDialog.show();

        final String phone = edtPhone.getText().toString();
        final String password = edtPassword.getText().toString();
        signIn.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(phone).exists()){
                    User user = dataSnapshot.child(phone).getValue(User.class);
                    user.setPhone(phone);

                    if(Boolean.parseBoolean(user.getIsStaff())){
                        if(user.getPassword().equals(password)){
                            Common.currentUser = user;
                            Log.d(TAG, "onDataChange: " + Common.currentUser);
                            startActivity(new Intent(SignIn.this, Home.class));
                            finish();
                        }else {
                            Toast.makeText(SignIn.this, "Sign in failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(SignIn.this, "User not exists", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SignIn.this, "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
