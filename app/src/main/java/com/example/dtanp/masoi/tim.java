package com.example.dtanp.masoi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageButton;

public class tim  extends Activity{
    ImageButton imguser1,imguser2,imguser3,imguser4,imguser5,imguser6,imguser7,imguser8,imguser9,imguser10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim);
        imguser1=findViewById(R.id.bguser1);
        imguser2=findViewById(R.id.bguser2);
        imguser3=findViewById(R.id.bguser3);
        imguser4=findViewById(R.id.bguser4);
        imguser5=findViewById(R.id.bguser5);
        imguser6=findViewById(R.id.bguser6);
        imguser7=findViewById(R.id.bguser7);
        imguser8=findViewById(R.id.bguser8);
        imguser9=findViewById(R.id.bguser9);
        imguser10=findViewById(R.id.bguser10);


    }
}
