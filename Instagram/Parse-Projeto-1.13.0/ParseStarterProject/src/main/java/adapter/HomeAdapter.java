package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parse.ParseObject;
import com.parse.starter.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends ArrayAdapter<ParseObject> {
    private Context context;
    private ArrayList<ParseObject> postagens;
    public HomeAdapter( Context c, ArrayList<ParseObject> objects) {
        super(c, 0 ,objects);
        this.context = c;
        this.postagens = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View view = convertView;

       if(view == null){
           LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
           view = inflater.inflate(R.layout.lista_postagem, parent, false);
       }
        if (postagens.size()>0) {
            ImageView imageView = view.findViewById(R.id.image_view);
            ParseObject parseObject = postagens.get(position);
            parseObject.getParseFile("imagem");
            Picasso.get().load(parseObject.getParseFile("imagem").getUrl()).fit().into(imageView);

        }

       return  view;
    }


}
