package data;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.familymap.MapActivity;
import com.example.david.familymap.PersonActivity;
import com.example.david.familymap.R;

import model.Event;
import model.Person;

/**
 * Created by david on 12/12/17.
 */


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private Event[] mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView eventInfo;
        private String eventID;
        //public ImageView locationImage;
        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(context, MapActivity.class);
                    intent.putExtra("EVENT_ID", eventID);
                    context.startActivity(intent);
                }
            });
            eventInfo = (TextView) v.findViewById(R.id.event_info);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EventAdapter(Event[] Dataset, Context context)
    {
        this.mDataset = Dataset;
        this.context = context;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.eventInfo.setText(makeEventText(mDataset[position]));
        holder.eventID = mDataset[position].getEventID();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    private String makeEventText(Event event)
    {
        DataManager dman = DataManager.getInstance();
        Person person = dman.getPerson(event.getPersonID());
        String eventText;
        eventText = event.getEventType() + ": " + event.getCity() + ", " + event.getCountry() + " (" + event.getYear() + ")" +
                    '\n' +  person.getFirstName() + " " + person.getLastName();
        return eventText;
    }
}


