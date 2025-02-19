package iut.dam.powerhomme2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResumeActivity extends AppCompatActivity {

    private TextView textViewResume;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        textViewResume = findViewById(R.id.textViewResume);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        // Récupération des données
        String firstName = bundle.getString("FIRST_NAME");
        String lastName = bundle.getString("LAST_NAME");
        String email = bundle.getString("EMAIL");
        String phone = bundle.getString("PHONE");
        String password = bundle.getString("PASSWORD");

        // Affichage des informations
        textViewResume.setText("Nom : " + lastName + "\n" +
                "Prénom : " + firstName + "\n" +
                "Email : " + email + "\n" +
                "Téléphone : " + phone + "\n" +
                "Mot de passe : " + password);
    }
}