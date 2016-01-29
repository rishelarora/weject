package weject.example.rishel.weject;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.ContactsContract.RawContacts;

import android.text.Html;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class open extends Activity {
	private static String savedNumber;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    int gscore;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialoginvi);
        gscore=((global)getApplicationContext()).getcheck2();
        Bundle extras = getIntent().getExtras();
	    if (extras != null) {
	        savedNumber = extras.getString("number");
	    }
	    
	    Cursor c = getContentResolver().query(
                RawContacts.CONTENT_URI,
                new String[] { RawContacts.CONTACT_ID, RawContacts.DISPLAY_NAME_PRIMARY },
                RawContacts.ACCOUNT_TYPE + "= ?",
                new String[] { "com.whatsapp" },
                null);

        ArrayList<String> myWhatsappContacts = new ArrayList<String>();
        int contactNameColumn = c.getColumnIndex(RawContacts.DISPLAY_NAME_PRIMARY);
        while (c.moveToNext())
        {
            
            myWhatsappContacts.add(c.getString(contactNameColumn));
        }
        
        String[] projection = new String[] {
                ContactsContract.PhoneLookup.DISPLAY_NAME,
                ContactsContract.PhoneLookup._ID};
       
        
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(savedNumber));
        Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
        
        String contactName = null;
        
        if(cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME));
        }

        if(cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
         int a=((global)getApplication()).getcheck();

        if(myWhatsappContacts.contains(contactName)&&a==1){


        	AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(open.this);


            alertDialog1.setTitle("Weject");

            // Setting Dialog Message

            alertDialog1.setMessage(Html.fromHtml("A rejected/missed call from the WhatsApp contact <font color='green'><b>" +contactName+ "</b></font> was detected. Do you wish to send a Whatsapp message?"));

            // Setting Icon to Dialog
            alertDialog1.setIcon(R.drawable.wejectd);


            // Setting Positive "Yes" Button
            alertDialog1.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            // Write your code here to execute after dialog
                        	try{
                                myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                                myClip = ClipData.newPlainText("text", ((global)getApplicationContext()).getcopytext());
                                myClipboard.setPrimaryClip(myClip);
                                ((global)getApplication()).addscore(5);
                                ((global)getApplication()).update(((global)getApplication()).getcheck2());
                            Uri mUri = Uri.parse("smsto:"+savedNumber);
                    		Intent mIntent = new Intent(Intent.ACTION_SENDTO, mUri);
                    		mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    		mIntent.setPackage("com.whatsapp");
                    		startActivity(mIntent);
                    		finish();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT)
                                    .show();
                        }

                    }
                    });
            // Setting Negative "NO" Button
            alertDialog1.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog

                            dialog.cancel();
                            finish();
                        }
                    });

            // Showing Alert Message
            AlertDialog alertDialog2=alertDialog1.show();
            TextView messageView = (TextView)alertDialog2.findViewById(android.R.id.message);
            messageView.setGravity(Gravity.CENTER);

            TextView titleView = (TextView)alertDialog2.findViewById(this.getResources().getIdentifier("alertTitle", "id", "android"));
            if (titleView != null) {
                titleView.setGravity(Gravity.CENTER);
                titleView.setTextColor(Color.RED);
            }



        }
        else if(myWhatsappContacts.contains(contactName)&&a==2){
        	try{
                myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                myClip = ClipData.newPlainText("text", ((global)getApplicationContext()).getcopytext());
                myClipboard.setPrimaryClip(myClip);
                ((global)getApplication()).addscore(10);
                ((global)getApplication()).update(((global)getApplication()).getcheck2());
            Uri mUri = Uri.parse("smsto:"+savedNumber);
    		Intent mIntent = new Intent(Intent.ACTION_SENDTO, mUri);
    		mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		mIntent.setPackage("com.whatsapp");
    		startActivity(mIntent);
    		finish();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }
        else if(myWhatsappContacts.contains(contactName)&&a==3){

            Intent intent = new Intent(getApplicationContext(), wservice.class);
            intent.putExtra("number",savedNumber);
            intent.putExtra("cname",contactName);
            startService(intent);
            finish();
        }

        else
        {
            finish();

        }
	}
}
