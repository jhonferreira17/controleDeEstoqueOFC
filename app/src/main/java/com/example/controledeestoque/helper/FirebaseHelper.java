package com.example.controledeestoque.helper;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseHelper {


    private static FirebaseAuth auth;

    public static FirebaseAuth getAuth(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
