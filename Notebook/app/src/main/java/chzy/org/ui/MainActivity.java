package chzy.org.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import chzy.org.adpter.MyAdapter;
import chzy.org.db.NoteDb;
import chzy.org.diary.R;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private SQLiteDatabase database;
    private NoteDb db;
    private ListView lv;
    private Cursor cursor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_weixin);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                return false;
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivity(intent);
            }
        });
        lv = (ListView) findViewById(R.id.lv);
        db = new NoteDb(this);
        database = db.getReadableDatabase();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                Intent intent = new Intent(MainActivity.this, Details.class);
                intent.putExtra(NoteDb.TIME,cursor.getString(cursor.getColumnIndex(NoteDb.TIME)));
                intent.putExtra(NoteDb.CONTENT,cursor.getString(cursor.getColumnIndex(NoteDb.CONTENT)));
                intent.putExtra(NoteDb.ID,cursor.getInt(cursor.getColumnIndex(NoteDb.ID)));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryDb();
    }

    public void queryDb() {
        cursor = database.query(db.TABLE_NAME, null, null, null, null, null, null);
        MyAdapter myAdapter = new MyAdapter(cursor, this);
        lv.setAdapter(myAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.settings:
                Toast.makeText(this, "你好", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
