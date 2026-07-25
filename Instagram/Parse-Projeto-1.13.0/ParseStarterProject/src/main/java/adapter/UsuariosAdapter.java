package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsuariosAdapter extends ArrayAdapter<ParseUser> {

    private Context context;
    private ArrayList<ParseUser> usuarios;
    public UsuariosAdapter(@NonNull Context c,  ArrayList<ParseUser> objects) {
        super(c, 0, objects);
        this.context = c;
        this.usuarios = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_user, parent, false);
        }

        TextView username = view.findViewById(R.id.textView);

        // Get the user from the adapter's own data management
        ParseUser parseUser = getItem(position);
        if (parseUser != null) {
            username.setText(parseUser.getUsername());
        }

        return view;
    }
}
