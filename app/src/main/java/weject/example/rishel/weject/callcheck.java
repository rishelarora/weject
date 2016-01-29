package weject.example.rishel.weject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

public class callcheck extends BroadcastReceiver  {
	

	private static int lastState = TelephonyManager.CALL_STATE_IDLE;
	private static String savedNumber;
	private static boolean isIncoming;
	
	
	@Override
	public void onReceive(Context context, Intent intent) { 
		
		
		 
		String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
        String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        int state = 0;
        if(stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)){
            state = TelephonyManager.CALL_STATE_IDLE;
        }
        else if(stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
            state = TelephonyManager.CALL_STATE_OFFHOOK;
            
        }
        else if(stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            state = TelephonyManager.CALL_STATE_RINGING;
        }
        
        switch (state) {
        case TelephonyManager.CALL_STATE_RINGING:

        	savedNumber = number;
        	isIncoming = true;
            break;
        case TelephonyManager.CALL_STATE_OFFHOOK:
        	 isIncoming = false;
        	
            break;
        case TelephonyManager.CALL_STATE_IDLE:
        	
        	if(lastState == TelephonyManager.CALL_STATE_RINGING){


        		Intent mIntent = new Intent(context, open.class);
        		mIntent.putExtra("number",savedNumber);
        		mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        		context.startActivity(mIntent);
        	}
        	else if(isIncoming){
                                      
            }
        	break;
        	
        }
        lastState = state;
		
		
		
		
        

    }
    
}
