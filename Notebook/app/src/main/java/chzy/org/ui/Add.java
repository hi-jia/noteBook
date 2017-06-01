package chzy.org.ui;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

import chzy.org.db.NoteDb;
import chzy.org.diary.R;
import widget.LinedEditText;

public class Add extends Activity {
    private SQLiteDatabase database;
    private NoteDb db;
    private int flag;
    private LinedEditText ed;
    private TextView time;
    FloatingActionButton save, cancle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        initView();
        initData();
    }

    private void initData() {
        time.setText(getTime());
        db = new NoteDb(this);
        database = db.getWritableDatabase();
    }

    private void initView() {
        ed = (LinedEditText) findViewById(R.id.addDiary_et_content);
        save = (FloatingActionButton) findViewById(R.id.save);
        cancle = (FloatingActionButton) findViewById(R.id.Cancle);
        time=(TextView) findViewById(R.id.adddiary_tv_date);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
                finish();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
      //  addData();
    }

    public void addData() {
        ContentValues values = new ContentValues();
        values.put(db.CONTENT, ed.getText().toString());
         values.put(db.TIME, getTime());

        database.insert(db.TABLE_NAME, null, values);
    }

    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String time = format.format(date);
        return time;
    }

}
