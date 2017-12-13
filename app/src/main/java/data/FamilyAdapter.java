package data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.familymap.R;

/**
 * Created by david on 12/12/17.
 */


public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.ViewHolder> {
    private String[] mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView familyMemberInfo;
        public ImageView genderImage;

        public ViewHolder(View v) {
            super(v);
            familyMemberInfo = (TextView) v.findViewById(R.id.family_member_info);
            genderImage = (ImageView) v.findViewById(R.id.family_member_gender);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FamilyAdapter(String[] Dataset, Context context)
    {
        this.mDataset = Dataset;
        this.context = context;

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
        holder.familyMemberInfo.setText(mDataset[position]);
        Drawable drawable = context.getResources().getDrawable(R.mipmap.ic_male);
        holder.genderImage.setImageDrawable(drawable);

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}

