package com.juannarvaez.taskworkout.view.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.entily.Usuario;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int RC_SIGN_IN= 1 ;
    private  static  final  String TAG="MAINACTIVITY";
    private FirebaseAuth.AuthStateListener mAuthListener;

    private GoogleSignInClient mGoogleSignInClient;

    //defining view objects
    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnLogin;
    private TextView btnRegistrar;
    private TextView btnOlvideContra;
    private  Button btnGoogle;
    private ProgressDialog progressDialog;

    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;
    private ProgressDialog RegprogressDialog;


    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser() !=null){
            // Check if user is signed in (non-null) and update UI accordingly.

            Intent intent = new Intent(MainActivity.this, InicioActivity.class);
            startActivity(intent);

            finish();
        }
    }


    private void inicialize(){

        TextEmail = (EditText)findViewById(R.id.main_Correo);
        TextPassword = (EditText)findViewById(R.id.main_Password);
        btnLogin= (Button)findViewById(R.id.bottomIniciarSession);

        btnRegistrar = (TextView)findViewById(R.id.main_textViewRegistar);
        btnOlvideContra = (TextView) findViewById(R.id.main_textViewOlviContra);
        btnGoogle = (Button)findViewById(R.id.main_botonGoogle);
        progressDialog = new ProgressDialog(this);

        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        RegprogressDialog = new ProgressDialog(this);

        //asociamos un oyente al evento clic del botón
        btnRegistrar.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
        btnOlvideContra.setOnClickListener(this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicialize();
        /* btnLogin.setVisibility(View.GONE);*/
        // Configure Google Sign In
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }




    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN ) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        String email = TextEmail.getText().toString();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            registrarPorFirebaseStore();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Login Failed...", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loguearUsuario() {
        //Obtenemos el email y la contraseña desde las cajas de texto
        final String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {//(precio.equals(""))
            TextEmail.setError("Se debe ingresar un email");
            TextEmail.setFocusable(true);
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (password.length()<6) {
            TextPassword.setError("Contraseña debe tener minimo 6 caracteres");
            TextPassword.setFocusable(true);

            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Realizando consulta en linea...");
        progressDialog.closeOptionsMenu();
        progressDialog.show();

        //loguear usuario
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Bienvenido: " + TextEmail.getText(), Toast.LENGTH_LONG).show();
                            Intent intencion = new Intent(getApplication(), InicioActivity.class);
                            startActivity(intencion);
                            finish();

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(MainActivity.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }

                    }
                });


    }

    private void registrarPorFirebaseStore() {

        FirebaseUser user = firebaseAuth.getCurrentUser();
        FirebaseFirestore registro = FirebaseFirestore.getInstance();
        Usuario Registro = new Usuario(user.getDisplayName(),user.getEmail(),user.getPhoneNumber());

        registro.collection("usuarios").whereEqualTo("correo", user.getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult()!= null){
                        Toast.makeText(MainActivity.this, "Bienvenido "+ user.getEmail(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, InicioActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        registro.collection("usuarios").document(user.getUid()).set(Registro).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(MainActivity.this, "Bienvenido "+ user.getEmail(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }

                }
            }
        });




    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottomIniciarSession:
                //Invocamos al método:
                loguearUsuario();
                break;
            case R.id.main_textViewRegistar:
                Intent intencion = new Intent(getApplication(), RegistroActivity.class);
                startActivity(intencion);

                break;
            case R.id.main_textViewOlviContra:
                Intent intent = new Intent(getApplication(), OlvideContrasena.class);
                startActivity(intent);

                break;
            case R.id.main_botonGoogle:
                signIn();
                break;


        }
    }


}
