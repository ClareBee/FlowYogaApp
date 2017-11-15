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
        Quotations quotes = new Quotations();
        TextView quotation = (TextView)findViewById(R.id.quotation);
        quotation.setText(quotes.randomQuotation());
//is there a way to refresh a page without an intent?
        finish();
        Intent intent = new Intent(this, QuotationActivity.class);
        finish();
        overridePendingTransition( 0, 0);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);
        startActivity(intent);
    }
}
