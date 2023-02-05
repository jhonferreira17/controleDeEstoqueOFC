package com.example.controledeestoque.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {


    private static FirebaseAuth auth;
    private static DatabaseReference databaseReference;

  public static String getIdFirebase(){
        return getAuth().getUid();
    }
    public static DatabaseReference getDatabaseReference(){
        if(databaseReference == null){
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return databaseReference;

    }

    public static FirebaseAuth getAuth(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
    public static boolean getAutenticado(){
        return getAuth().getCurrentUser() != null;
    }

}
