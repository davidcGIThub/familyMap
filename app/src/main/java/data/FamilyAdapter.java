package data;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.familymap.PersonActivity;
import com.example.david.familymap.R;

import java.util.ArrayList;

import model.Person;

/**
 * Created by david on 12/12/17.
 */


public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.ViewHolder> {
    private Person[] mDataset;
    private Context context;
    private Person root;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView familyMemberInfo;
        public ImageView genderImage;
        private String personID;

        public ViewHolder(View v)
        {
            super(v);
            v.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent( FamilyAdapter.this.context, PersonActivity.class);
                    intent.putExtra("PERSON_ID", personID);
                    context.startActivity(intent);
                }
            });
            familyMemberInfo = (TextView) v.findViewById(R.id.family_member_info);
            genderImage = (ImageView) v.findViewById(R.id.family_member_gender);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FamilyAdapter(Person[] Dataset, Person root, Context context)
    {
        this.mDataset = Dataset;
        this.context = context;
        this.root = root;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public FamilyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.family_member_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.familyMemberInfo.setText(makePersonText(mDataset[position]));
        holder.personID = mDataset[position].getPersonID();
        Drawable drawable;
        if(mDataset[position].getGender().equals("m"))
        {
            drawable = context.getResources().getDrawable(R.mipmap.ic_male);
        }
        else
        {
            drawable = context.getResources().getDrawable(R.mipmap.ic_female);
        }
        holder.genderImage.setImageDrawable(drawable);

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return mDataset.length;
    }

    private String makePersonText(Person person)
    {
        String relation = "NA";

        if(root.getFather() != null && root.getFather().equals(person.getPersonID()))
        {
            relation = "Father";
        }
        else if(root.getMother() != null && root.getMother().equals(person.getPersonID()))
        {
            relation = "Mother";
        }
        else if(root.getSpouse() != null && root.getSpouse().equals(person.getPersonID()))
        {
           relation = "Spouse";
        }
        else
        {
            relation = "Child";
        }

        String personText = person.getFirstName() + " " + person.getLastName() + '\n' + relation;
        return personText;
    }
}

