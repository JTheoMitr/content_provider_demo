package android.example.contentproviderdemo;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Providers extends AppCompatActivity {

    public void getPhoneContacts() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CONTACTS}, 0);
        }

        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        Log.i("CONTACT_PROVIDER_DEMO", "TOTAL # of Contacts: " + Integer.toString(cursor.getCount()));
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String contactName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String contactNumber = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Log.i("CONTACT_PROVIDER_DEMO", "Contact Name: " + contactName + "Phone : " + contactNumber);
            }
        }
    }

    public void logItUp() {
        Log.i("NEW_LOG", "We Loggin' bruh");
    }

}
