package ph.hostev.paul.mp3filemanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.ViewHolder> {

    List<String> names;
    Context context;

    public AudioListAdapter(Context context, List<String> names) {
        this.names = names;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = names.get(position);
        if (name.contains(".mp3")) {
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.mp3icon));
        } else {
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.folder));
        }
        holder.textView.setText(name);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.name);
            imageView = v.findViewById(R.id.image);
        }
    }
}
