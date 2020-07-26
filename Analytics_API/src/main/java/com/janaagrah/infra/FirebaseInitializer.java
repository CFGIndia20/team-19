package com.janaagrah.infra;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Uses the config annotation to inject DatabaseReference Bean
 * into the application scope and connect to the Firestore DB.
 * 
 * @author jimil
 *
 */
@Configuration
public class FirebaseInitializer {


    @Bean
    public DatabaseReference firebaseDatabse() {
        DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
        return firebase;
    }

    @PostConstruct
    public void init() {

//        FileInputStream serviceAccount = null;
//        try {
//            serviceAccount = new FileInputStream("D:\\janaagraha-database.json"); // todo show path of admin sdk config
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setDatabaseUrl("https://cfg-team-19.firebaseio.com")
                .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirebaseApp.initializeApp(options);

    }


}