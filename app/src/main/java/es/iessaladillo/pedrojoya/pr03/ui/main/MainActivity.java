package es.iessaladillo.pedrojoya.pr03.ui.main;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr03.R;
import es.iessaladillo.pedrojoya.pr03.data.local.Database;
import es.iessaladillo.pedrojoya.pr03.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr03.utils.ValidationUtils;

import static com.google.android.material.snackbar.Snackbar.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    private EditText txtWeb;
    private EditText txtName;
    private EditText txtAddress;
    private EditText txtPhonenumber;
    private EditText txtEmail;
    private TextView lblName;
    private TextView lblEmail;
    private TextView lblAddress;
    private TextView lblAvatar;
    private TextView lblPhoneNumber;
    private ImageView imgAddress;
    private ImageView imgWeb;
    private ImageView imgEmail;
    private ImageView imgAvatar;
    private ImageView imgPhoneNumber;
    private TextView lblWeb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        // QUITA LOS COMENTARIOS TODO CUANDO LO IMPLEMENTES
        // TODO
    }

    private void initView() {
        txtWeb = ActivityCompat.requireViewById(this, R.id.txtWeb);
        txtName = ActivityCompat.requireViewById(this, R.id.txtName);
        txtAddress = ActivityCompat.requireViewById(this, R.id.txtAddress);
        txtPhonenumber = ActivityCompat.requireViewById(this, R.id.txtPhonenumber);
        txtEmail = ActivityCompat.requireViewById(this, R.id.txtEmail);
        lblName = ActivityCompat.requireViewById(this, R.id.lblName);
        lblEmail = ActivityCompat.requireViewById(this, R.id.lblEmail);
        lblAddress = ActivityCompat.requireViewById(this, R.id.lblAddress);
        lblAvatar = ActivityCompat.requireViewById(this, R.id.lblAvatar);
        lblPhoneNumber = ActivityCompat.requireViewById(this, R.id.lblPhonenumber);
        lblWeb = ActivityCompat.requireViewById(this, R.id.lblWeb);
        imgAddress = ActivityCompat.requireViewById(this, R.id.imgAddress);
        imgWeb = ActivityCompat.requireViewById(this, R.id.imgWeb);
        imgEmail = ActivityCompat.requireViewById(this, R.id.imgEmail);
        imgAvatar = ActivityCompat.requireViewById(this, R.id.imgAvatar);
        imgPhoneNumber = ActivityCompat.requireViewById(this, R.id.imgPhonenumber);


        initAvatar();
        imgAvatar.setOnClickListener(v -> changeAvatar());
        lblAvatar.setOnClickListener(v -> changeAvatar());
        // EL CÓDIGO DE LOS onFocusChange ES PRÁCTICAMENTE EL MISMO. DEFINE UN MÉTODO.
        txtAddress.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblAddress.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblAddress.setTypeface(Typeface.DEFAULT);
        });
        txtName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblName.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblName.setTypeface(Typeface.DEFAULT);
        });
        txtPhonenumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblPhoneNumber.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblPhoneNumber.setTypeface(Typeface.DEFAULT);
        });
        txtWeb.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblWeb.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblWeb.setTypeface(Typeface.DEFAULT);
        });
        txtEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblEmail.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblEmail.setTypeface(Typeface.DEFAULT);
        });
        txtWeb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateWeb();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateName();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateAddress();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtPhonenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validatePhoneNumber();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateEmail();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtWeb.setOnEditorActionListener((v, actionId, event) -> {
            save();
            return false;
        });


    }

    // EL CÓDIGO DE TODOS ESTOS MÉTODOS validate... ES PRÁCTICAMENTE EL MISMO.
    // DEFINE UN MÉTODO QUE RECIBA ENTRE OTRAS COSAS UN BOOLEANO INDICATIVO DE SI
    // EL CAMPO ES VÁLIDO O NO Y QUE REALICE LOS CAMBIOS VISUALES OPORTUNOS.
    private boolean validateWeb() {
        boolean result = false;
        if (!ValidationUtils.isValidUrl(txtWeb.getText().toString())) {
            imgWeb.setEnabled(false);
            lblWeb.setEnabled(false);
            txtWeb.setError(getString(R.string.main_invalid_data));
        } else {
            result = true;
            imgWeb.setEnabled(true);
            lblWeb.setEnabled(true);
            txtWeb.setError(null);
        }

        return result;
    }

    private boolean validateAddress() {
        boolean result = false;
        if (TextUtils.isEmpty(txtAddress.getText())) {
            imgAddress.setEnabled(false);
            lblAddress.setEnabled(false);
            txtAddress.setError(getString(R.string.main_invalid_data));
        } else {
            result=true;
            imgAddress.setEnabled(true);
            lblAddress.setEnabled(true);
            txtAddress.setError(null);
        }
        return result;
    }

    private boolean validatePhoneNumber() {
        boolean result = false;
        if (!ValidationUtils.isValidPhone(txtPhonenumber.getText().toString())) {
            imgPhoneNumber.setEnabled(false);
            lblPhoneNumber.setEnabled(false);
            txtPhonenumber.setError(getString(R.string.main_invalid_data));
        } else {
            result=true;
            imgPhoneNumber.setEnabled(true);
            lblPhoneNumber.setEnabled(true);
            txtPhonenumber.setError(null);
        }

        return result;
    }

    private boolean validateEmail() {
        boolean result = false;
        if (!ValidationUtils.isValidEmail(txtEmail.getText().toString())) {
            imgEmail.setEnabled(false);
            lblEmail.setEnabled(false);
            txtEmail.setError(getString(R.string.main_invalid_data));
        } else {
            result=true;
            imgEmail.setEnabled(true);
            lblEmail.setEnabled(true);
            txtEmail.setError(null);
        }

        return result;
    }

    private boolean validateName() {
        boolean result = false;
        if (TextUtils.isEmpty(txtName.getText())) {
            lblName.setEnabled(false);
            txtName.setError(getString(R.string.main_invalid_data));
        } else {
            result=true;
            lblName.setEnabled(true);
            txtName.setError(null);
        }

        return result;
    }

    private boolean validateAll() {
        boolean result = false;
        boolean address = validateAddress();
        boolean email = validateEmail();
        boolean name = validateName();
        boolean phoneNumber = validatePhoneNumber();
        boolean web = validateWeb();

        // DEFINE UN MÉTODO isFormValid()
        if (address&&name&&email&&phoneNumber&&web) {
            result = true;
        }
        return result;
    }

    private void initAvatar() {
        imgAvatar.setImageResource(Database.getInstance().getDefaultAvatar().getImageResId());
        // ESTAS DOS LÍNEAS SE REPITEN EN EL SIGUIENTE MÉTODO. DEFINE UN MÉTODO showAvatar(avatar)
        lblAvatar.setText(Database.getInstance().getDefaultAvatar().getName());
        imgAvatar.setTag(Database.getInstance().getDefaultAvatar().getImageResId());
    }

    public void changeAvatar() {
        Avatar avatar = Database.getInstance().getRandomAvatar();
        imgAvatar.setImageResource(avatar.getImageResId());
        lblAvatar.setText(avatar.getName());
        imgAvatar.setTag(avatar.getImageResId());
    }


    // DO NOT TOUCH
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // DO NOT TOUCH
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Checks if form is valid or not and shows a Snackbar accordingly
     **/
    private void save() {
        String message=validateAll()?getString(R.string.main_saved_succesfully):getString(R.string.main_error_saving);
        Snackbar.make(txtWeb, message, LENGTH_SHORT).show();
        // TODO
    }

}
