package chzy.org.adpter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import chzy.org.diary.R;

/**
 * Created by Administrator on 2017/5/29 0029.
 */

public class MyAdapter extends BaseAdapter {
    Cursor cursor;
    Context context;

    public MyAdapter(Cursor cursor, Context context) {
        this.cursor = cursor;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View v= View.inflate(context,R.layout.lv_item,null);
       TextView tvContent= (TextView) v.findViewById(R.id.iv_content);
        TextView tvTime= (TextView) v.findViewById(R.id.iv_time);
        cursor.moveToPosition(position);
       String content= cursor.getString(cursor.getColumnIndex("content"));
       String time= cursor.getString(cursor.getColumnIndex("time"));
        tvTime.setText(time);
        tvContent.setText(content);
        return v;
    }
}
