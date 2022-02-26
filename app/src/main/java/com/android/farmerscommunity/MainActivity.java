package com.android.farmerscommunity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ExtendedFloatingActionButton fab;

    JSONObject jsonObject = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(R.drawable.crop_insect,"Entertainment","Anyone please suggest insect name","Anonymous",0));
        posts.add(new Post(R.drawable.brown_leaves,"Leaf disease","Leaves are rotten. Looking with brownish color. Suggest some suitable urea.","Anonymous",0));
        posts.add(new Post(R.drawable.red_soil,"Crops","Suggest a profitable crop for this red soil.","Ramesh",0));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new PostAdapter(posts);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        fab = (ExtendedFloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View v = li.inflate(R.layout.layout_dialog, null);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                alertDialog.setTitle("Add Question");

                EditText category = v.findViewById(R.id.newCategory);
                EditText question = v.findViewById(R.id.newQuestion);
                Button imageSelect = v.findViewById(R.id.addImage);
                imageSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("This class","I am a working button");
                        imageChooser();
                    }
                });
                alertDialog.setView(v);

                alertDialog.setPositiveButton("Post",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Gson gson = new Gson();
                                try{
                                    jsonObject.put("mImageResource", 0);
                                    jsonObject.put("mCategory", category.getText().toString());
                                    jsonObject.put("mQuestion", question.getText().toString());
                                    jsonObject.put("mUsername","Anonymous");
                                    jsonObject.put("noAnswers",0);
                                    Post post = gson.fromJson(String.valueOf(jsonObject),Post.class);
                                    posts.add(post);
                                    mAdapter.notifyDataSetChanged();
                                } catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        });
                alertDialog.setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                alertDialog.show();
            }
        });
    }

    void imageChooser(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"Select Image"), 200);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            if(requestCode == 200)
            {
                try {
                    jsonObject.put("mNewImage", data.getData());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

