package weject.example.rishel.weject;

import android.app.Application;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class global extends Application {

    private int check=0;
    private int check2=0;
    private boolean dupcheck=false;
    public String name=null;
    public String copyText="";
    public int getcheck() {
        return check;
    }

    public void setcheck(int check) {
        this.check = check;
    }

    public int getcheck2() {
        return check2;
    }
    public void setdupcheck(boolean dupcheck) {
        this.dupcheck = dupcheck;
    }

    public boolean getdupcheck() {
        return dupcheck;
    }

    public void setcheck2(int check) {
        this.check2 = check;
    }
    public void addscore(int check) {
        this.check2 = check2+check;
    }
    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }
    public void setcopytext(String string) {
        this.copyText = string;
    }
    public String getcopytext() {
        return copyText;
    }
    public void update(final int scoreee) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");

        if (name != null) {
            query.whereEqualTo("Name", name);
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject score, ParseException e) {
                    if (score == null) {

                    } else {
                        String objectId = score.getObjectId();
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");


                        query.getInBackground(objectId, new GetCallback<ParseObject>() {
                            public void done(ParseObject score, ParseException e) {
                                if (e == null) {

                                    score.put("Score", scoreee);

                                    score.saveEventually();
                                }
                            }
                        });
                    }
                }
            });

        }
    }

        @Override
        public void onCreate() {
            super.onCreate();
            Parse.enableLocalDatastore(this);

            Parse.initialize(this, "5TnLmNePzWsbbTyVyNCE74rKldGC35lyg0KKf4zX", "YbKa55Gq65kA8CfC4LmZujDLj94fEVt2NuT2w4vp");

        }
}
