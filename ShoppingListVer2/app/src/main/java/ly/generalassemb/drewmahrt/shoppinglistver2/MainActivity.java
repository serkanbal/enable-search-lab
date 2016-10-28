package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.List;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        //RECYCLERVIEW STUFF
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        //Create the Layout Manager:
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL, false);

        //Now that we created; call the layout manager
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //Populate the list.
        List<ItemObject> list = LabSQLiteOpenHelper.getInstance(this).getAllAsList();

        //Set the adapter.
        mRecyclerView.setAdapter(new RecyclerAdapter(list));
        //RECYCLERVIEW STUFF ENDS

        mAdapter = new RecyclerAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

    }

    //This is to put the search box on the toolbar.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        //Next we make the app searchable. right click on res > new resource file > XML file named
        //searchable.
        //Also added some lines to the android manifest file.

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //SearchView'da support.widget olanı seç
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        ComponentName componentName = new ComponentName(this,MainActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);

    }

    public void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            //perform search
            String query = intent.getStringExtra(SearchManager.QUERY);
            List<ItemObject> searchList = LabSQLiteOpenHelper.getInstance(this).
                    itemSearchForNameOrType(query);
            mAdapter.replaceData(searchList);
        }
    }
}
