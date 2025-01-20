package com.example.itsupportapp;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private ArrayList<HashMap<String, String>> equipmentList;
    private static final String URL_GET_EQUIPMENT = "http://10.0.2.2/itsupportbackend/get_equipment.php";
    private static final String URL_UPDATE_STATUS = "http://10.0.2.2/itsupportbackend/update_status.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listView = findViewById(R.id.listView);
        equipmentList = new ArrayList<>();

        adapter = new SimpleAdapter(
                this,
                equipmentList,
                R.layout.equipment_item,
                new String[]{"name", "description", "email", "isChecked"},
                new int[]{R.id.textName, R.id.textDescription, R.id.textEmail, R.id.checkBox}
        ) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                CheckBox checkBox = view.findViewById(R.id.checkBox);
                HashMap<String, String> item = equipmentList.get(position);

                boolean isChecked = item.get("isChecked").equals("1");
                checkBox.setChecked(isChecked);
                checkBox.setText(isChecked ? "Vérifié" : "Non vérifié");
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateEquipmentStatus(item.get("id"), checkBox.isChecked());
                    }
                });

                return view;
            }
        };

        listView.setAdapter(adapter);
        loadEquipment();
    }

    private void loadEquipment() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET_EQUIPMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            equipmentList.clear();
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject equipment = array.getJSONObject(i);
                                HashMap<String, String> item = new HashMap<>();
                                item.put("id", equipment.getString("id"));
                                item.put("name", equipment.getString("name"));
                                item.put("description", equipment.getString("description"));
                                item.put("email", equipment.getString("email"));
                                item.put("isChecked", equipment.getString("isChecked"));
                                equipmentList.add(item);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminActivity.this, "Error loading equipment", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void updateEquipmentStatus(String id, boolean isChecked) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE_STATUS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            Toast.makeText(AdminActivity.this, "Status updated", Toast.LENGTH_SHORT).show();
                            loadEquipment();
                        } else {
                            Toast.makeText(AdminActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminActivity.this, "Error updating status", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("isChecked", isChecked ? "1" : "0");
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}