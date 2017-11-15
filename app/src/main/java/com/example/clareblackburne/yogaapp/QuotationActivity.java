package com.example.clareblackburne.yogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuotationActivity extends MainMenu {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quotation);
        Quotations quotes = new Quotations();
        TextView quotation = (TextView)findViewById(R.id.quotation);
        quotation.setText(quotes.randomQuotation());

    }


    public void onClickGetAnother(View button){

        Intent intent = new Intent(this, QuotationActivity.class);
        finish();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
