package ru.vyaacheslav.suhov.imeit.Teachers;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.vyaacheslav.suhov.imeit.R;

 class MyViewHolder {

    ImageView img;
    TextView nameTxt;



     MyViewHolder(View v) {
        img=  v.findViewById(R.id.movieImage);
        nameTxt=  v.findViewById(R.id.nameTxt);


    }

}
