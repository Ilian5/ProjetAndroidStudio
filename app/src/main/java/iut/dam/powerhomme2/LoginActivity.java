package iut.dam.powerhomme2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private Button btnCreateAccount;
    private Spinner spinnerCountryCode;
    private ImageView backArrow;
    private Button btnConnect;
    private EditText editEmail, editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Gestion de l'orientation
        changeOrientation(getResources().getConfiguration().orientation);

        // Initialisation des composants
        initializeViews();

        //Gestion des buttons
        checkButton();

        // Configuration du Spinner pour les indicatifs téléphoniques
        setupCountryCodeSpinner();
    }

    private void initializeViews() {
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        spinnerCountryCode = findViewById(R.id.spinnerCountryCode);
        backArrow = findViewById(R.id.backArrow);
        btnConnect = findViewById(R.id.btnConnect);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
    }

    private void setupCountryCodeSpinner() {
        if (spinnerCountryCode != null) {
            String[] countryCodes = {"+33", "+34", "+49", "+44", "+1"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countryCodes);
            spinnerCountryCode.setAdapter(adapter);
        }
    }

    private void changeOrientation(int orientation) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_login_landscape);
        } else {
            setContentView(R.layout.activity_login);
        }
    }

    private void checkButton() {
        // button créer un compte
        if (btnCreateAccount != null) {
            btnCreateAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish(); // Terminer l'activité actuelle et revenir en arrière
                }
            });
        }

        if(backArrow != null) {
            backArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish(); // Terminer l'activité actuelle et revenir en arrière
                }
            });
        }

        if(btnConnect != null) {
            btnConnect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(validateFields()) {
                        Snackbar.make(v, "Connexion réussie !", Snackbar.LENGTH_LONG).show();

                        new Handler().postDelayed(() -> {
                            Intent mainIntent = new Intent(LoginActivity.this, ResumeActivity.class);

                            // Transfert des données
                            mainIntent.putExtra("EMAIL", editEmail.getText().toString());
                            mainIntent.putExtra("PASSWORD", editPassword.getText().toString());

                            startActivity(mainIntent);
                            finish();
                        }, 1000);
                    }
                }
            });
        }
    }


    private boolean validateFields() {
        boolean isValid = true;

        // Vérification du mot de passe
        if (!editPassword.getText().toString().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$")) {
            editPassword.setError("Le mot de passe doit contenir au moins 8 caractères, des lettres, des chiffres et un symbole !");
            isValid = false;
        } else {
            editPassword.setError(null);
        }

        // Vérification de l'email
        if (!editEmail.getText().toString().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            editEmail.setError("Veuillez entrer une adresse e-mail valide !");
            isValid = false;
        } else {
            editEmail.setError(null);
        }
        return isValid;
    }


}
