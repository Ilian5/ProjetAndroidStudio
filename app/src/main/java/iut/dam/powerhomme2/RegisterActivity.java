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

public class RegisterActivity extends AppCompatActivity {

    private Button btnAlreadyHaveAccount;
    private Spinner spinnerCountryCode;
    private ImageView backArrow;
    private Button btnRegister;
    private EditText editFirstName, editLastName, editEmail, editPassword, editPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Gestion de l'orientation
        changeOrientation(getResources().getConfiguration().orientation);

        // Initialisation des composants
        initializeViews();

        // Gestion des boutons
        checkButton();

        // Configuration du Spinner pour les indicatifs téléphoniques
        setupCountryCodeSpinner();
    }

    private void initializeViews() {
        btnAlreadyHaveAccount = findViewById(R.id.btnAlreadyHaveAccount);
        spinnerCountryCode = findViewById(R.id.spinnerCountryCode);
        backArrow = findViewById(R.id.backArrow);
        btnRegister = findViewById(R.id.btnRegister);
        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        editEmail = findViewById(R.id.editRegisterMail);
        editPassword = findViewById(R.id.editRegisterPassword);
        editPhone = findViewById(R.id.editPhoneNumber);
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
            setContentView(R.layout.activity_register_landscape);
        } else {
            setContentView(R.layout.activity_register);
        }
    }

    private void checkButton() {
        if (btnAlreadyHaveAccount != null) {
            btnAlreadyHaveAccount.setOnClickListener(v -> {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            });
        }

        if (backArrow != null) {
            backArrow.setOnClickListener(v -> finish());
        }

        if (btnRegister != null) {
            btnRegister.setOnClickListener(v -> {
                if (validateFields()) {
                    Snackbar.make(v, "Inscription réussie !", Snackbar.LENGTH_LONG).show();

                    new Handler().postDelayed(() -> {
                        Intent intent = new Intent(this, ResumeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("FIRST_NAME", editFirstName.getText().toString());
                        bundle.putString("LAST_NAME", editLastName.getText().toString());
                        bundle.putString("EMAIL", editEmail.getText().toString());
                        bundle.putString("PHONE", editPhone.getText().toString());
                        bundle.putString("PASSWORD", editPassword.getText().toString());                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }, 1000);
                }
            });

        }
    }

    private boolean validateFields() {
        boolean isValid = true;

        // Vérification du prénom
        if (!validateFieldLength(editFirstName, 2, 25, "Le prénom doit contenir entre 2 et 25 caractères !")) {
            isValid = false;
        }

        // Vérification du nom
        if (!validateFieldLength(editLastName, 2, 25, "Le nom doit contenir entre 2 et 25 caractères !")) {
            isValid = false;
        }

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

        // Vérification du numéro de téléphone
        if (!validatePhoneNumber(editPhone)) {
            isValid = false;
        }

        return isValid;
    }

    private boolean validatePhoneNumber(EditText field) {
        String phoneNumber = field.getText().toString().trim();

        if (phoneNumber.length() < 8 || !phoneNumber.matches("\\d+")) {
            field.setError("Veuillez entrer un numéro de téléphone valide (au moins 8 chiffres) !");
            return false;
        }

        field.setError(null);
        return true;
    }

    private boolean validateFieldLength(TextView field, int min, int max, String errorMsg) {
        String text = field.getText().toString().trim();
        if (text.length() < min || text.length() > max) {
            field.setError(errorMsg);
            return false;
        }
        field.setError(null);
        return true;
    }
}