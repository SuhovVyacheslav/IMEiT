package ru.vyaacheslav.suhov.imeit.Maps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.vyaacheslav.suhov.imeit.R;

public class Maps_Camus extends Fragment implements View.OnClickListener {

    LinearLayout k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11, ddf;
    Intent agr;
    Uri adresK;
    TextView q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11;

    public Maps_Camus() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_campus, container, false);

        k1 = v.findViewById(R.id.k1);
        k2 = v.findViewById(R.id.k2);
        k3 = v.findViewById(R.id.k3);
        k4 = v.findViewById(R.id.k4);
        k5 = v.findViewById(R.id.k5);
        k6 = v.findViewById(R.id.k6);
        k7 = v.findViewById(R.id.k7);
        k8 = v.findViewById(R.id.k8);
        k9 = v.findViewById(R.id.k9);
        k10 = v.findViewById(R.id.k10);
        k11 = v.findViewById(R.id.k11);

        q1 = v.findViewById(R.id.q1);
        q2 = v.findViewById(R.id.q2);
        q3 = v.findViewById(R.id.q3);
        q4 = v.findViewById(R.id.q4);
        q5 = v.findViewById(R.id.q5);
        q6 = v.findViewById(R.id.q6);
        q7 = v.findViewById(R.id.q7);
        q8 = v.findViewById(R.id.q8);
        q9 = v.findViewById(R.id.q9);
        q10 = v.findViewById(R.id.q10);
        q11 = v.findViewById(R.id.q11);
        ddf = v.findViewById(R.id.ddf);

        k1.setOnClickListener(this);
        k2.setOnClickListener(this);
        k3.setOnClickListener(this);
        k4.setOnClickListener(this);
        k5.setOnClickListener(this);
        k6.setOnClickListener(this);
        k7.setOnClickListener(this);
        k8.setOnClickListener(this);
        k9.setOnClickListener(this);
        k10.setOnClickListener(this);
        k11.setOnClickListener(this);

        LoadPreferences();
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.k1:
                adresK = Uri.parse("geo:0,0?q=ул. Коммунаров, д. 28,+Елец,+Липецкая+обл.");
                break;
            case R.id.k2:
                adresK = Uri.parse("geo:0,0?q=ул. Ленина, д. 91,+Елец,+Липецкая+обл.");
                break;
            case R.id.k3:
                adresK = Uri.parse("geo:0,0?q=ул. Коммунаров, д. 15,+Елец,+Липецкая+обл.");
                break;
            case R.id.k4:
                adresK = Uri.parse("geo:0,0?q=ул. 9 Декабря, д.72,+Елец,+Липецкая+обл.");
                break;
            case R.id.k5:
                adresK = Uri.parse("geo:0,0?q=ул. Ленина, д. 86,+Елец,+Липецкая+обл.");
                break;
            case R.id.k6:
                adresK = Uri.parse("geo:0,0?q=ул. 9 Декабря, д. 13а,+Елец,+Липецкая+обл.");
                break;
            case R.id.k7:
                adresK = Uri.parse("geo:0,0?q=ул. Коммунаров, д. 37,+Елец,+Липецкая+обл.");
                break;
            case R.id.k8:
                adresK = Uri.parse("geo:0,0?q=ул. Допризывников, д. 1,+Елец,+Липецкая+обл.");
                break;
            case R.id.k9:
                adresK = Uri.parse("geo:0,0?q=ул. Орджоникидзе, д.78,+Елец,+Липецкая+обл.");
                break;
            case R.id.k10:
                adresK = Uri.parse("geo:0,0?q=ул. Коммунаров, д. 39,+Елец,+Липецкая+обл.");
                break;
            case R.id.k11:
                adresK = Uri.parse("geo:0,0?q=ул. Коммунаров, д. 28,+Елец,+Липецкая+обл.");
                break;
        }

        agr = new Intent(Intent.ACTION_VIEW, adresK);
        agr.setPackage("com.google.android.apps.maps");
        startActivity(agr);
    }

    private void LoadPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String regular = prefs.getString(getString(R.string.pref_theme), "");

        switch (regular) {
            case "Светлая":
                ThemeWrite();
                break;
            case "Темная":
                ThemeDark();
                break;
        }
    }

    public void ThemeWrite() {

        ddf.setBackgroundColor(getResources().getColor(R.color.colorWhitee));
        k1.setBackground(getResources().getDrawable(R.drawable.card_row1));
        k2.setBackground(getResources().getDrawable(R.drawable.card_row1));
        k3.setBackground(getResources().getDrawable(R.drawable.card_row1));
        k4.setBackground(getResources().getDrawable(R.drawable.card_row1));
        k5.setBackground(getResources().getDrawable(R.drawable.card_row1));
        k6.setBackground(getResources().getDrawable(R.drawable.card_row1));
        k7.setBackground(getResources().getDrawable(R.drawable.card_row1));
        k8.setBackground(getResources().getDrawable(R.drawable.card_row1));
        k9.setBackground(getResources().getDrawable(R.drawable.card_row1));
        k10.setBackground(getResources().getDrawable(R.drawable.card_row1));
        k11.setBackground(getResources().getDrawable(R.drawable.card_row1));
        q1.setTextColor(getResources().getColor(R.color.colorTextBlack));
        q2.setTextColor(getResources().getColor(R.color.colorTextBlack));
        q3.setTextColor(getResources().getColor(R.color.colorTextBlack));
        q4.setTextColor(getResources().getColor(R.color.colorTextBlack));
        q5.setTextColor(getResources().getColor(R.color.colorTextBlack));
        q6.setTextColor(getResources().getColor(R.color.colorTextBlack));
        q7.setTextColor(getResources().getColor(R.color.colorTextBlack));
        q8.setTextColor(getResources().getColor(R.color.colorTextBlack));
        q9.setTextColor(getResources().getColor(R.color.colorTextBlack));
        q10.setTextColor(getResources().getColor(R.color.colorTextBlack));
        q11.setTextColor(getResources().getColor(R.color.colorTextBlack));
    }

    public void ThemeDark() {

        ddf.setBackgroundColor(getResources().getColor(R.color.colorPrimaryF));
        k1.setBackground(getResources().getDrawable(R.drawable.card_row0));
        k2.setBackground(getResources().getDrawable(R.drawable.card_row0));
        k3.setBackground(getResources().getDrawable(R.drawable.card_row0));
        k4.setBackground(getResources().getDrawable(R.drawable.card_row0));
        k5.setBackground(getResources().getDrawable(R.drawable.card_row0));
        k6.setBackground(getResources().getDrawable(R.drawable.card_row0));
        k7.setBackground(getResources().getDrawable(R.drawable.card_row0));
        k8.setBackground(getResources().getDrawable(R.drawable.card_row0));
        k9.setBackground(getResources().getDrawable(R.drawable.card_row0));
        k10.setBackground(getResources().getDrawable(R.drawable.card_row0));
        k11.setBackground(getResources().getDrawable(R.drawable.card_row0));
        q1.setTextColor(getResources().getColor(R.color.colorWhitee));
        q2.setTextColor(getResources().getColor(R.color.colorWhitee));
        q3.setTextColor(getResources().getColor(R.color.colorWhitee));
        q4.setTextColor(getResources().getColor(R.color.colorWhitee));
        q5.setTextColor(getResources().getColor(R.color.colorWhitee));
        q6.setTextColor(getResources().getColor(R.color.colorWhitee));
        q7.setTextColor(getResources().getColor(R.color.colorWhitee));
        q8.setTextColor(getResources().getColor(R.color.colorWhitee));
        q9.setTextColor(getResources().getColor(R.color.colorWhitee));
        q10.setTextColor(getResources().getColor(R.color.colorWhitee));
        q11.setTextColor(getResources().getColor(R.color.colorWhitee));
    }
}
