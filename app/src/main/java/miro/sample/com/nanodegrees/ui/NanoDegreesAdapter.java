package miro.sample.com.nanodegrees.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import miro.sample.com.nanodegrees.R;
import miro.sample.com.nanodegrees.data.NanoDegree;

/**
 * Created by mitil on 1/19/16.
 */
public class NanoDegreesAdapter extends RecyclerView.Adapter<NanoDegreesAdapter.NanoDegreesViewHolder> {

    private List<NanoDegree> nanoDegreesList;
    private Context context;


    public NanoDegreesAdapter(Context thisContext, List<NanoDegree> nanoDegrees) {
        nanoDegreesList = nanoDegrees;
        context = thisContext;
    }

    /**
     * Called when RecyclerView needs a new {@link RecyclerView.ViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(RecyclerView.ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(RecyclerView.ViewHolder, int)
     */
    @Override
    public NanoDegreesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.listitem_nanodegree_cardview, parent, false);

        NanoDegreesViewHolder viewHolder = new NanoDegreesViewHolder(view);
        return viewHolder;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link RecyclerView.ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link RecyclerView.ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p/>
     * Override {@link #onBindViewHolder(RecyclerView.ViewHolder, int, List)} instead if Adapter can
     * handle effcient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(NanoDegreesViewHolder holder, int position) {

        NanoDegree courseData = (NanoDegree)nanoDegreesList.get(position);
        holder.courseTitle.setText(courseData.getCourseTitle());

        // Download image using Glide library
        Glide.with(context)
                .load(courseData.getCourseImageUrl())
                .error(android.R.drawable.ic_menu_gallery)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .crossFade()
                .centerCrop()
                .into(holder.courseImage);


    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return (null != nanoDegreesList ? nanoDegreesList.size() : 0);
    }

    public void updateData(List<NanoDegree> courseData) {
        nanoDegreesList.clear();
        nanoDegreesList.addAll(courseData);
        notifyDataSetChanged();
    }
    public class NanoDegreesViewHolder extends RecyclerView.ViewHolder {

        protected TextView courseTitle;
        protected ImageView courseImage;

        public NanoDegreesViewHolder(View itemView) {
            super(itemView);
            courseTitle = (TextView)itemView.findViewById(R.id.nanodegree_title);
            courseImage = (ImageView) itemView.findViewById(R.id.nanodegree_image);
        }
    }
}
