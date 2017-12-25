package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.Locale;

public class AccountActivity extends AppCompatActivity {

    TextView id;
    TextView infoLabel;
    TextView info;
    Button bforward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
    bforward = (Button) findViewById(R.id.bForward);
        bforward.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            Intent it = new Intent(AccountActivity.this, Schedule.class);
                                            startActivity(it);
                                        }
            });


       // FontHelper.setCustomTypeface(findViewById(R.id.view_root));

        id = (TextView) findViewById(R.id.id);
        infoLabel = (TextView) findViewById(R.id.info_label);
        info = (TextView) findViewById(R.id.info);
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
                // Get Account KIT ID
                String accountKitId = account.getId();
                id.setText(accountKitId);

                PhoneNumber phoneNumber = account.getPhoneNumber();
                if (account.getPhoneNumber() != null) {
                    //if the phone number is available, display it
                    String formattedPhoneNumber = formatPhoneNumber(phoneNumber.toString());
                    info.setText(formattedPhoneNumber);
                    infoLabel.setText(R.string.phone_label);

                } else {
                    String emailString = account.getEmail();
                    info.setText(emailString);
                    infoLabel.setText(R.string.email_label);

                    //if the email address is available, display it

                }
            }

            @Override
            public void onError(final AccountKitError error) {
                //display an error
                String toastMessage = error.getErrorType().getMessage();
                Toast.makeText(AccountActivity.this, toastMessage, Toast.LENGTH_LONG).show();
            }


        });
    }

    public void onLogout(View view) {
        //logout of Account Kit
        AccountKit.logOut();
        launchLoginActivity();
    }




    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private String formatPhoneNumber(String phoneNumber) {
        // helper method to format the phone number for display
        try {
            PhoneNumberUtil pnu = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber pn = pnu.parse(phoneNumber, Locale.getDefault().getCountry());
            phoneNumber = pnu.format(pn, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
        }
        catch (NumberParseException e) {
            e.printStackTrace();
        }
        return phoneNumber;
    }

}
