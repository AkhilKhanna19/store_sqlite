package appngo.myapp.com.appforngo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Akhil Khanna on 14-Apr-16.
 */
public class CustomViewAdapter extends RecyclerView.Adapter<CustomViewAdapter.MyViewHolder> {

    private Context mcontext;
    private ArrayList<HashMap<String, String>> events;
    private MyViewHolder myViewHolder;
    private TextView place, time, date;


    public CustomViewAdapter(Context context, ArrayList<HashMap<String, String>> event) {
        mcontext = context;
        events = event;

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
            place = (TextView) view.findViewById(R.id.place);
            time = (TextView) view.findViewById(R.id.time_id);
            date = (TextView) view.findViewById(R.id.event_date);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design, parent, false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        HashMap<String, String> eventlist;
        eventlist = events.get(position);
        place.setText(eventlist.get("place"));
        time.setText(eventlist.get("time"));
        date.setText(eventlist.get("date"));
    }


    @Override
    public int getItemCount() {
        return events.size();
    }
}
