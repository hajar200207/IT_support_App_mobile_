package com.example.itsupportapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Success extends AppCompatActivity {

    private EditText etEquipmentName, etEquipmentDescription,etEquipmentEmail;
    private Button btnSaveEquipment;
    private final String URL = "http://10.0.2.2/itsupportbackend/add_equipment.php"; // Ensure your server is running locally and accessible from the emulator

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);

        // Initialize UI components
        etEquipmentName = findViewById(R.id.etEquipmentName);
        etEquipmentDescription = findViewById(R.id.etEquipmentDescription);
        etEquipmentEmail= findViewById(R.id.etEquipmentEmail);
        btnSaveEquipment = findViewById(R.id.btnSaveEquipment);

        // Set click listener for the save button
        btnSaveEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEquipment();
            }
        });
    }

    private void saveEquipment() {
        String name = etEquipmentName.getText().toString().trim();
        String description = etEquipmentDescription.getText().toString().trim();
        String email = etEquipmentEmail.getText().toString().trim();  // Get the email input

        // Check if fields are empty
        if (name.isEmpty() || description.isEmpty() || email.isEmpty()) {
            Toast.makeText(Success.this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Make the POST request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equalsIgnoreCase("success")) {
                            Toast.makeText(Success.this, "Equipment added successfully!", Toast.LENGTH_SHORT).show();
                            etEquipmentName.setText(""); // Clear fields
                            etEquipmentDescription.setText("");
                            etEquipmentEmail.setText(""); // Clear the email field
                        } else {
                            Toast.makeText(Success.this, "Failed to add equipment. Server response: " + response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Success.this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("name", name);
                data.put("description", description);
                data.put("email", email);  // Include the email in the request
                return data;
            }
        };

        // Add the request to the Volley RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}

