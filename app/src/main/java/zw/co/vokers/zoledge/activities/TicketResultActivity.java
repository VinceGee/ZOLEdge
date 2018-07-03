package zw.co.vokers.zoledge.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.ZOLEdgeApp;
import zw.co.vokers.zoledge.adapters.TicketView;
import zw.co.vokers.zoledge.utils.Configs;

public class TicketResultActivity extends AppCompatActivity {
    private static final String TAG = TicketResultActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private TextView txtMealName, txtMealCode, txtMealDetails, txtUserBalance, txtMealPrice, txtError;
    private NetworkImageView imgMealImage;
    private Button btnBuy;
    private ProgressBar progressBar;
    private TicketView ticketView;
    private String mEmail;
    private String mType;
    private String mIDNum;
    private String mUserBal;
    private String fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtMealName = (TextView) findViewById(R.id.mname);
        txtMealCode = (TextView) findViewById(R.id.mcode);
        txtMealPrice = (TextView) findViewById(R.id.mcost);
        //txtUserBalance = (TextView) findViewById(R.id.balance);
        //imgPoster = (ImageView) findViewById(R.id.poster);
        txtMealDetails = (TextView) findViewById(R.id.mdesc);
        btnBuy = (Button) findViewById(R.id.btn_buy);
        //imgMealImage = (NetworkImageView) findViewById(R.id.mimage);
        txtError = (TextView) findViewById(R.id.txt_error);
        ticketView = (TicketView) findViewById(R.id.layout_ticket);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        fullname = preferences.getString("name", "");
        mIDNum = preferences.getString("user_id", "");
        mUserBal = preferences.getString("userbalance", "");

        Calendar date = Calendar.getInstance();
        Date timer = date.getTime();
        final String time = timer.toString();

        final String mcode = getIntent().getStringExtra("mealcode");

        //Toast.makeText(getApplicationContext(), "Meal Code: " + mcode, Toast.LENGTH_LONG).show();

        // close the activity in case of empty barcode
        if (TextUtils.isEmpty(mcode)) {
            Toast.makeText(getApplicationContext(), "Barcode is empty!", Toast.LENGTH_LONG).show();
            finish();
        }

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // search the barcode
        searchBarcode(mcode);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(TicketResultActivity.this)
                        .setTitle("Confirm Meal")
                        .setMessage("Are you sure you want to pay for this meal?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                transact(mIDNum, mcode, time);
                                startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(R.mipmap.ic_launcher)
                        .show();

                //Toast.makeText(getApplicationContext(), mUserBal+mIDNum , Toast.LENGTH_LONG).show();

            }
        });
    }

    /**
     * Searches the barcode by making http call
     * Request was made using Volley network library but the library is
     * not suggested in production, consider using Retrofit
     */
    private void searchBarcode(String mcode) {
        // making volley's json request
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Configs.URL_SEARCH+mcode, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                Log.e(TAG, "Ticket response: " + response.toString());

                // check for success status
                if (!response.has("error")) {
                    // received meal response
                    renderMovie(response);
                } else {
                    // no meal found
                    showNoTicket();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "ErrorModel: " + error.getMessage());
                showNoTicket();
            }
        });

        ZOLEdgeApp.getInstance().addToRequestQueue(jsonObjReq);
    }

    private void showNoTicket() {
        txtError.setVisibility(View.VISIBLE);
        ticketView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    /**
     * Rendering meal details on the ticket
     */
    private void renderMovie(JSONObject response) {
        try {

            // converting json to meal object
            Gson gson = new Gson();
            Meal meal = gson.fromJson(response.toString(), Meal.class);

            if (meal != null) {
                txtMealCode.setText(meal.getMealcode());
                txtMealName.setText(meal.getMealname());
                txtMealPrice.setText("$"+meal.getMealcost());
                //txtUserBalance.setText("$"+mUserBal);
                txtMealDetails.setText(meal.getMealdetails());
                //imgMealImage.setImageUrl(meal.getMealimage(), imageLoader);
                //Glide.with(this).load(meal.getMealimage()).into(imgMealImage);

                //Log.e("YYYYYYYYYYYYYY" , " I have "+mUserBal+" Meal code is" +meal.getMealcode());

                //if (mUserBal==null) {
                btnBuy.setText(getString(R.string.btn_buy_now));
                btnBuy.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                 /*else {
                    btnBuy.setText(getString(R.string.no_money));
                    btnBuy.setTextColor(ContextCompat.getColor(this, R.color.btn_disabled));
                }*/
                ticketView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            } else {
                // meal not found
                showNoTicket();
            }
        } catch (JsonSyntaxException e) {
            Log.e(TAG, "JSON Exception: " + e.getMessage());
            showNoTicket();
            Toast.makeText(getApplicationContext(), "JSON ErrorModel occurred. Check your LogCat for full report", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // exception
            showNoTicket();
            Toast.makeText(getApplicationContext(), "Exception ErrorModel occurred. Check your LogCat for full report", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void transact(final String user_id, final String mealcode, final String transtime) {
        // Tag used to cancel the request
        String tag_string_req = "sync";
        //Log.e("******", user_id + mealcode + transtime);
        pDialog.setMessage("Processing...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, Configs.URL_TRANSACT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("MainMenuActivity", "Register Response: " + response.toString());
                hideDialog();
                {

                    Toast.makeText(getApplicationContext(), "Payment made!", Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Menu Button Activity", "Registration ErrorModel: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Connection Problem. Request Assistance", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("user_id", user_id);
                params.put("mealcode", mealcode);
                params.put("transtime", transtime);

                return params;
            }

        };

        // Adding request to request queue
        ZOLEdgeApp.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private class Meal {
        String mealcode;
        String mealname;
        String mealcost;
        String mealdetails;
        String mealimage;

        public String getMealcode() {
            return mealcode;
        }

        public void setMealcode(String mealcode) {
            this.mealcode = mealcode;
        }

        public String getMealname() {
            return mealname;
        }

        public void setMealname(String mealname) {
            this.mealname = mealname;
        }

        public String getMealcost() {
            return mealcost;
        }

        public void setMealcost(String mealcost) {
            this.mealcost = mealcost;
        }

        public String getMealdetails() {
            return mealdetails;
        }

        public void setMealdetails(String mealdetails) {
            this.mealdetails = mealdetails;
        }

        public String getMealimage() {
            return mealimage;
        }

        public void setMealimage(String mealimage) {
            this.mealimage = mealimage;
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {

        try {
            if ((this.pDialog != null) && this.pDialog.isShowing()) {
                this.pDialog.dismiss();
            }
        } catch (final IllegalArgumentException e) {
            // Handle or log or ignore
        } catch (final Exception e) {
            // Handle or log or ignore
        } finally {
            this.pDialog = null;
        }
        // pDialog.dismiss();
    }
}

