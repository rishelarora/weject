package weject.example.rishel.weject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rishel on 24/09/2015.
 */
public class ctext extends android.support.v4.app.Fragment  {
    private RelativeLayout ll;
    private FragmentActivity fa;
    Button b2;
    TextView t5;
    EditText ed1;
    String ctext="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fa = super.getActivity();
        ll = (RelativeLayout) inflater.inflate(R.layout.ctext, container, false);
        ((mainactivity) getActivity()).setActionBarTitle("Settings");
        ctext=((global)fa.getApplication()).getcopytext();
        b2  = (Button) ll.findViewById(R.id.button);
        t5 = (TextView)ll.findViewById(R.id.textView4);
        ed1 = (EditText)ll.findViewById(R.id.editText);
        if(!ctext.equals(""))
            t5.setText("Current CopyText : "+((global)fa.getApplication()).getcopytext());
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = ed1.getText().toString();
                if(!text.equals("")){
                    ((global)fa.getApplication()).setcopytext(text);
                    Toast.makeText(fa, "Text Copied", Toast.LENGTH_SHORT).show();
                    t5.setText("Current CopyText : "+((global)fa.getApplication()).getcopytext());}
                else
                    Toast.makeText(fa, "Text field empty",Toast.LENGTH_SHORT).show();
                ed1.setText("");
            }
        });


        return ll;
    }

    }
