package appngo.myapp.com.appforngo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {


    private ListView listView;
    private TextView welText, nameText;
    private ImageView imageView;
    private RecyclerView recyclerView;
    CustomViewAdapter customViewAdapter;
    //userDetails userDetails = new userDetails();
    //DatabaseHandler dba = new DatabaseHandler(Main2Activity.this);
    java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView) findViewById(R.id.list_item);
        nameText = (TextView) findViewById(R.id.name_id);
        imageView = (ImageView) findViewById(R.id.profile_id);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        long date = (java.lang.System.currentTimeMillis());

        String datedata = dateFormat.format(date+2);
        Log.d("date in long  ", datedata);
        /*Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);*/




        final String[] dateevent = new String[]{
                datedata,
                datedata,
                datedata,
                datedata,
                datedata,
                datedata,
                datedata,
                datedata,
                datedata,
                datedata

        };

        final String[] eventPlaces = new String[]{
                "Allahabad",
                "Lucknow",
                "Barabanki",
                "Delhi",
                "Faizabad",
                "Kanpur",
                "Mumbai",
                "Bangalore",
                "Pune",
                "Chandigarh"
        };

        final String[] eventTime = new String[]{
                "3:00 - 4:00 pm",
                "9:00 - 10:00 am",
                "11:00 - 12:00 am",
                "8:00 - 9:00 am",
                "7:00 - 8:00 am",
                "3:00 - 4:00 pm",
                "7:00 - 8:00 am",
                "9:00 - 10:00 am",
                "1:00 - 2:00 pm",
                "4:00 - 5:00 pm",

        };
        Bundle extra = getIntent().getExtras();

        /*nameText.setText(extra.getString("firstname"));
        imageView.setImageResource(R.drawable.ic_action_name);*/

        ArrayList<HashMap<String, String>> ngoEventList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> data = new HashMap<>();
            data.put("place", eventPlaces[i]);
            data.put("time", eventTime[i]);
            data.put("date", dateevent[i]);


            ngoEventList.add(data);
        }




        customViewAdapter = new CustomViewAdapter(getApplicationContext(), ngoEventList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(customViewAdapter);

        //listView.setAdapter(customViewAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener(){

            @Override
            public void onClick(View view, int position) {
                TextView date = (TextView) view.findViewById(R.id.event_date);
                TextView time = (TextView) view.findViewById(R.id.time_id);
                TextView place =(TextView) view.findViewById(R.id.place);
                String mplace = place.getText().toString();
                String mdate = date.getText().toString();
                String mtime = time.getText().toString();
                Intent i = new Intent(Main2Activity.this, Main3Activity.class);
                i.putExtra("place", mplace);
                i.putExtra("date", mdate);
                i.putExtra("time",mtime);

                startActivity(i);



            }

            @Override
            public void onLongClick(View view, int position) {

            }

            //String idclicked = listView.getItemAtPosition(mposition).toString();

                //Toast.makeText(getApplicationContext(), "id clicked" + idclicked, Toast.LENGTH_SHORT).show();

        }));
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Main2Activity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Main2Activity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
