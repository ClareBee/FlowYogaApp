package com.example.clareblackburne.yogaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class QuotationActivity extends MainMenu {

    TextView quotation;
    Quotations quotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quotation);
        Quotations quotes = new Quotations();
        TextView quotation = (TextView)findViewById(R.id.quotation);
        quotation.setText(quotes.randomQuotation());
    }
}
