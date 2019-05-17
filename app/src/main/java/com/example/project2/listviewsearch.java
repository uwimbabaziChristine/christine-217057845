package com.example.project2;

import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class listviewsearch extends AppCompatActivity {

    DatabaseHelper mydb;
    ArrayAdapter adapter;
    ListView userlist;
    ArrayList<String> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewsearch);
        mydb=new DatabaseHelper(this);
        userlist=findViewById(R.id.user_listview);
        listItem=new ArrayList<>();
        viewdata();
    }

    private void viewdata() {

        Cursor cursor=mydb.viewdata();
        if (cursor.getCount()==0)
        {
            Toast.makeText(this, "no data inside database", Toast.LENGTH_LONG).show();


        }
        else
        {
            while (cursor.moveToNext())
            {
                listItem.add(cursor.getString(1));


            }
            adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
            userlist.setAdapter(adapter);
        }

    }

    public  boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem searchitem=menu.findItem(R.id.itemsearch);
        SearchView searchView =(SearchView) MenuItemCompat.getActionView(searchitem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {





                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                ArrayList<String>userslist=new ArrayList<>();
                for (String user:listItem)
                {
                    if (user.toLowerCase().contains(s.toLowerCase()))
                    {
                        userslist.add(user);
                    }
                }

                ArrayAdapter<String>adapter=new ArrayAdapter<String>(listviewsearch.this,android.R.layout.simple_list_item_1,userslist);
                userlist.setAdapter(adapter);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }


}




