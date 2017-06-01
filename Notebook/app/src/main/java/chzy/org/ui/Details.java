package chzy.org.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import chzy.org.db.NoteDb;
import chzy.org.diary.R;
import widget.LinedEditText;

/**
 * Created by Administrator on 2017/5/29 0029.
 */

public class Details extends AppCompatActivity {
    FloatingActionButton save, cancle;
    private SQLiteDatabase database;
    private NoteDb db;
    private LinedEditText ledContent;
    private TextView tvTime;
    private TextView tvTime1;
    private Intent intent;
    private ImageButton ib;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitydetails);
        initView();
        initData();

    }

    private void initData() {
        intent = getIntent();
        String text = intent.getStringExtra(NoteDb.CONTENT);
        ledContent.setText(text);
        String time = intent.getStringExtra(NoteDb.TIME);
        tvTime.setText(time);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updata();
                finish();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
                finish();
            }
        });
    }


    public void click(View v){
        switch (v.getId()){
            case R.id.title_ib_back:
                finish();
                break;
        }
    }
    private void initView() {
        ledContent = (LinedEditText) findViewById(R.id.detail_content);
        save = (FloatingActionButton) findViewById(R.id.detail_save);
        cancle = (FloatingActionButton) findViewById(R.id.detail_Cancle);
        tvTime = (TextView) findViewById(R.id.detail_time);
        db = new NoteDb(this);
        database = db.getWritableDatabase();
    }

    public void delete() {
        int i = intent.getIntExtra(NoteDb.ID, 0);
        database.delete(NoteDb.TABLE_NAME, "_id=" + i + "", null);

    }

    public void updata() {
        int i = intent.getIntExtra(NoteDb.ID, 0);
        String content = ledContent.getText().toString();
        ContentValues values = new ContentValues();
        values.put(NoteDb.CONTENT, content);
        database.update(NoteDb.TABLE_NAME, values, "_id=" + i + "", null);
    }
}
