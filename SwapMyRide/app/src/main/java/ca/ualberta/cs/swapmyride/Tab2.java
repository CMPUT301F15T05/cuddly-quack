package ca.ualberta.cs.swapmyride;

/**
 * Created by Daniel on 2015-10-24.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Tab2 extends Fragment {

    ListView invetory;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab2,container,false);

        invetory = (ListView) v.findViewById(R.id.inventoryView);

        return v;
    }
}