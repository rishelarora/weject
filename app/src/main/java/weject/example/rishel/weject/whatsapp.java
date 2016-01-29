package weject.example.rishel.weject;


import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;


import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;


import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class whatsapp extends android.support.v4.app.Fragment {

	 ComponentName component;

	private ScrollView ll;
	private FragmentActivity fa;
	Switch s2,s3,s4;
	Switch s1;

	TextView t1,t2,t3,t4;




	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		fa = super.getActivity();
		ll = (ScrollView) inflater.inflate(R.layout.activity_main, container, false);

		((mainactivity) getActivity()).setActionBarTitle("Settings");



		s1 = (Switch) ll.findViewById(R.id.switch5);
		s2 = (Switch) ll.findViewById(R.id.switch2);
		s3 = (Switch) ll.findViewById(R.id.switch3);
		s4 = (Switch) ll.findViewById(R.id.switch1);


		t1 = (TextView)ll.findViewById(R.id.stext);
		t2 = (TextView)ll.findViewById(R.id.textView);
		t3 = (TextView)ll.findViewById(R.id.textView2);
		t4 = (TextView)ll.findViewById(R.id.textView3);






        component = new ComponentName(fa, callcheck.class);
        SharedPreferences sharedPrefs = fa.getSharedPreferences("hello", fa.MODE_PRIVATE);
        SharedPreferences sharedPrefs1 = fa.getSharedPreferences("hi", fa.MODE_PRIVATE);
		SharedPreferences sharedPrefs2 = fa.getSharedPreferences("bye", fa.MODE_PRIVATE);
		SharedPreferences sharedPrefs3 = fa.getSharedPreferences("dialog", fa.MODE_PRIVATE);

        s1.setChecked(sharedPrefs.getBoolean("state", true));
        s2.setChecked(sharedPrefs1.getBoolean("state1", false));
		s3.setChecked(sharedPrefs2.getBoolean("state2", false));
		s4.setChecked(sharedPrefs3.getBoolean("state3", false));
        s1.setOnCheckedChangeListener(changeChecker);
        s2.setOnCheckedChangeListener(changeChecker);
		s3.setOnCheckedChangeListener(changeChecker);
		s4.setOnCheckedChangeListener(changeChecker);
		if (!s1.isChecked()) {
			t1.setText("Turn Weject service ON to reject calls with WhatsApp.");
			s2.setVisibility(View.GONE);
			s3.setVisibility(View.GONE);
			s4.setVisibility(View.GONE);
			t2.setVisibility(View.GONE);
			t3.setVisibility(View.GONE);
			t4.setVisibility(View.GONE);




		}
		else if(s1.isChecked()==true&&s2.isChecked()==false&&s3.isChecked()==false&&s4.isChecked()==false){
			t1.setText("No mode selected. Choose a mode to use Weject. ");
		}
		else if(s1.isChecked()==true&&s2.isChecked()==true)
			t1.setText(Html.fromHtml(" <font color=\"#3F51B5\"> FastCut </font> mode selected."));
		else if(s1.isChecked()==true&&s3.isChecked()==true)
			t1.setText(Html.fromHtml(" <font color=\"#3F51B5\"> SwipeCut </font> mode selected."));
		else if(s1.isChecked()==true&&s4.isChecked()==true)
			t1.setText(Html.fromHtml(" <font color=\"#3F51B5\"> Dialog Box </font> mode selected."));







		return ll;


}



	OnCheckedChangeListener changeChecker = new OnCheckedChangeListener() {

	    @Override
	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

	            if (buttonView == s1) {
	            	if (isChecked) {
						s2.setVisibility(View.VISIBLE);
						s3.setVisibility(View.VISIBLE);
						s4.setVisibility(View.VISIBLE);
						t2.setVisibility(View.VISIBLE);
						t3.setVisibility(View.VISIBLE);
						t4.setVisibility(View.VISIBLE);





						t1.setText("Choose a Weject mode to reject calls and open the caller's chat.");
						final Toast toast=Toast.makeText(fa, "Weject service is ON", Toast.LENGTH_SHORT);
						toast.show();
						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								toast.cancel();
							}
						}, 350);
	        			fa.getPackageManager().setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_ENABLED , PackageManager.DONT_KILL_APP);
	        			SharedPreferences.Editor editor = fa.getSharedPreferences("hello", fa.MODE_PRIVATE).edit();
	        	        editor.putBoolean("state", true);
	        	        editor.commit();
	                } else {
	                	s2.setChecked(false);
						s3.setChecked(false);
						s4.setChecked(false);
						t1.setText("Turn Weject service ON to reject calls with WhatsApp.");
						s2.setVisibility(View.GONE);
						s3.setVisibility(View.GONE);
						s4.setVisibility(View.GONE);
						t2.setVisibility(View.GONE);
						t3.setVisibility(View.GONE);
						t4.setVisibility(View.GONE);





	    	            SharedPreferences.Editor editor1 = fa.getSharedPreferences("hi", fa.MODE_PRIVATE).edit();
	        	        editor1.putBoolean("state1", false);
	        	        editor1.commit();

						final Toast toast=Toast.makeText(fa, "Weject service is OFF", Toast.LENGTH_SHORT);
						toast.show();
						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								toast.cancel();
							}
						}, 350);
	        			fa.getPackageManager().setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_DISABLED , PackageManager.DONT_KILL_APP);
	        			SharedPreferences.Editor editor = fa.getSharedPreferences("hello", fa.MODE_PRIVATE).edit();
	        	        editor.putBoolean("state", false);
	        	        editor.commit();

	                }
					if(s1.isChecked()==true&&s2.isChecked()==false&&s3.isChecked()==false&&s4.isChecked()==false){
						t1.setText("No mode selected. Choose a mode to use Weject. ");
					}
	            }
	           if (buttonView == s2){
	        	   if(s1.isChecked()==true){
	            	 if (isChecked) {
						 s3.setChecked(false);
						 s4.setChecked(false);
						 t1.setText(Html.fromHtml(" <font color=\"#3F51B5\"> FastCut </font> mode selected."));


	         			((global)fa.getApplication()).setcheck(2);
						 fa.getPackageManager().setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
	         			SharedPreferences.Editor editor = fa.getSharedPreferences("hi", fa.MODE_PRIVATE).edit();
	        	        editor.putBoolean("state1", true);
	        	        editor.commit();

	                 } else {

						 t1.setText("No mode selected. Choose a mode to use Weject. ");
	                 	((global)fa.getApplication()).setcheck(0);
						 fa.getPackageManager().setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
	                 	SharedPreferences.Editor editor = fa.getSharedPreferences("hi", fa.MODE_PRIVATE).edit();
	        	        editor.putBoolean("state1", false);
	        	        editor.commit();
	                 }
	            }



	            else if(s1.isChecked()==false){
	            	s2.setChecked(false);

	            SharedPreferences.Editor editor = fa.getSharedPreferences("hi", fa.MODE_PRIVATE).edit();
    	        editor.putBoolean("state1", false);
    	        editor.commit();
	            }
	           }
			if (buttonView == s3)
			{
				if(s1.isChecked()==true){
				if (isChecked) {
					s2.setChecked(false);
					s4.setChecked(false);
					t1.setText(Html.fromHtml(" <font color=\"#3F51B5\"> SwipeCut </font> mode selected."));

					((global)fa.getApplication()).setcheck(3);
					fa.getPackageManager().setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
					SharedPreferences.Editor editor = fa.getSharedPreferences("bye", fa.MODE_PRIVATE).edit();
					editor.putBoolean("state2", true);
					editor.commit();
				}else {
					t1.setText("No mode selected. Choose a mode to use Weject. ");
					((global) fa.getApplication()).setcheck(0);
					fa.getPackageManager().setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
					SharedPreferences.Editor editor = fa.getSharedPreferences("bye", fa.MODE_PRIVATE).edit();
					editor.putBoolean("state2", false);
					editor.commit();
				}
				}
				else if(s1.isChecked()==false){
					s3.setChecked(false);
					SharedPreferences.Editor editor = fa.getSharedPreferences("bye", fa.MODE_PRIVATE).edit();
					editor.putBoolean("state2", false);
					editor.commit();
				}


			}
			if (buttonView == s4)
			{
				if(s1.isChecked()==true){
					if (isChecked) {
						s2.setChecked(false);
						s3.setChecked(false);


						t1.setText(Html.fromHtml(" <font color=\"#3F51B5\"> Dialog Box </font> mode selected."));
						((global)fa.getApplication()).setcheck(1);
						fa.getPackageManager().setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
						SharedPreferences.Editor editor = fa.getSharedPreferences("dialog", fa.MODE_PRIVATE).edit();
						editor.putBoolean("state3", true);
						editor.commit();
					}else {

						t1.setText("No mode selected. Choose a mode to use Weject. ");
						((global) fa.getApplication()).setcheck(0);
						fa.getPackageManager().setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
						SharedPreferences.Editor editor = fa.getSharedPreferences("dialog", fa.MODE_PRIVATE).edit();
						editor.putBoolean("state3", false);
						editor.commit();
					}
				}
				else if(s1.isChecked()==false){
					s4.setChecked(false);
					SharedPreferences.Editor editor = fa.getSharedPreferences("dialog", fa.MODE_PRIVATE).edit();
					editor.putBoolean("state3", false);
					editor.commit();
				}
			}
		
	    }
	};



}
