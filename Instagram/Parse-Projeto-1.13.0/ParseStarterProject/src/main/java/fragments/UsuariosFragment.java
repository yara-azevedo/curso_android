package fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.activity.UserFActivity;

import java.util.ArrayList;
import java.util.List;

import adapter.UsuariosAdapter;


public class UsuariosFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<ParseUser> arrayAdapter;
    private ArrayList<ParseUser> usuarios;
    private ParseQuery<ParseUser> query;

    public UsuariosFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usuarios, container, false);

        
        usuarios = new ArrayList<>();
        listView = view.findViewById(R.id.list_view);

        if (listView != null) {
            arrayAdapter = new UsuariosAdapter(getActivity(), usuarios);
            listView.setAdapter(arrayAdapter);
            getUsuarios();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ParseUser parseUser = usuarios.get(i);

                Intent intent = new Intent(getActivity(), UserFActivity.class);
                intent.putExtra("username", parseUser.getUsername());
                startActivity(intent);


            }
        });

        return  view;
    }

    private void getUsuarios() {
        query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.orderByAscending("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    // Always clear and update, even if the result is empty
                    Log.i("ParseQuery", "Users found: " + objects.size());
                    arrayAdapter.clear();
                    if (objects.size() > 0) {
                        arrayAdapter.addAll(objects);
                    }
                    arrayAdapter.notifyDataSetChanged();
                } else {
                    e.printStackTrace();
                }
            }

        });
    }
}