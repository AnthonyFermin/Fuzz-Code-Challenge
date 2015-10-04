package nyc.anthonyfermin.codechallenge;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by c4q-anthonyf on 10/3/15.
 */
public class DataListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TEXT_VIEW_TYPE = 0;
    private static final int IMAGE_VIEW_TYPE = 1;
    private static final int ALL_VIEW_TYPE = 2;

    private Context context;
    private List<DisplayData> displayDataList;
    public static final String IMAGE_URL = "image_url";


    public DataListAdapter(Context context, List<DisplayData> displayDataList){
        this.displayDataList = displayDataList;
        this.context = context;
    }

    private class TextViewHolder extends RecyclerView.ViewHolder {

        public TextView textTV;

        public TextViewHolder(View itemView) {
            super(itemView);
            textTV = (TextView) itemView.findViewById(R.id.text);

            textTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent webActivityIntent = new Intent(context,WebActivity.class);
                    context.startActivity(webActivityIntent);
                }
            });

        }
    }

    private class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public LinearLayout container;

        public ImageViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            container = (LinearLayout) itemView.findViewById(R.id.container);

            image.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    String imageUrl = displayDataList.get(getAdapterPosition()).getData();
                    if(imageUrl != null) {
                        Intent imageViewActivityIntent = new Intent(context, ImageViewActivity.class);
                        imageViewActivityIntent.putExtra(IMAGE_URL, imageUrl);
                        context.startActivity(imageViewActivityIntent);
                    }
                }
            });

        }
    }

    private class AllDataViewHolder extends RecyclerView.ViewHolder {

        public TextView textTV, dateTV, idTV,typeTV;
        public LinearLayout container;

        public AllDataViewHolder(View itemView) {
            super(itemView);
            idTV = (TextView) itemView.findViewById(R.id.id);
            dateTV = (TextView) itemView.findViewById(R.id.date);
            textTV = (TextView) itemView.findViewById(R.id.text);
            typeTV = (TextView) itemView.findViewById(R.id.type);
            container = (LinearLayout) itemView.findViewById(R.id.container);

            textTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent webActivityIntent = new Intent(context,WebActivity.class);
                    context.startActivity(webActivityIntent);
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        switch(viewType){
            case TEXT_VIEW_TYPE:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_list_item, parent, false);
                return new TextViewHolder(itemView);
            case IMAGE_VIEW_TYPE:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item, parent, false);
                return new ImageViewHolder(itemView);
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_list_item, parent, false);
                return new AllDataViewHolder(itemView);
        }


    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int viewType = holder.getItemViewType();
        final DisplayData displayData = displayDataList.get(position);

        switch(viewType){
            case TEXT_VIEW_TYPE:
                final TextViewHolder textHolder = (TextViewHolder) holder;
                textHolder.textTV.setText(displayData.getData());
                break;
            case IMAGE_VIEW_TYPE:
                final ImageViewHolder imageHolder = (ImageViewHolder) holder;
                Picasso.Builder builder = new Picasso.Builder(context);
                builder.listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        imageHolder.image.setImageResource(R.drawable.error_image);
                    }
                });
                builder.build().load(displayData.getData()).centerCrop().fit().into(imageHolder.image);
                break;
            case ALL_VIEW_TYPE:
                AllDataViewHolder viewHolder = (AllDataViewHolder) holder;

                viewHolder.dateTV.setText(displayData.getDate());
                viewHolder.idTV.setText(displayData.getId());
                viewHolder.textTV.setText(displayData.getData());
                viewHolder.typeTV.setText(displayData.getType());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        String viewType = displayDataList.get(position).getType();
        switch(viewType){
            case "text":
                return TEXT_VIEW_TYPE;
            case "image":
                return IMAGE_VIEW_TYPE;
            default:
                return ALL_VIEW_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return displayDataList.size();
    }


}
