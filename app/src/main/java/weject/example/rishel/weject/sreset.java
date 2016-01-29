package weject.example.rishel.weject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.RelativeLayout;


/**
 * Created by rishel on 24/09/2015.
 */
public class sreset extends android.support.v4.app.Fragment  {
    private RelativeLayout ll;
    private FragmentActivity fa;
    Button b1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fa = super.getActivity();
        ll = (RelativeLayout) inflater.inflate(R.layout.sreset, container, false);
        ((mainactivity) getActivity()).setActionBarTitle("Settings");

        b1  = (Button) ll.findViewById(R.id.reset);
        b1.setText("Your score is "+((global)fa.getApplication()).getcheck2()+". Use this button to RESET SCORE.");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((global)fa.getApplication()).setcheck2(0);
                ((global)fa.getApplication()).update(((global) fa.getApplication()).getcheck2());
                b1.setText("Your score is " + ((global) fa.getApplication()).getcheck2() + ". Use this button to RESET SCORE.");
            }
        });


        return ll;
    }

}