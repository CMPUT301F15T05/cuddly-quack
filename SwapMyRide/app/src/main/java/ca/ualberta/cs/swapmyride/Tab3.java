package ca.ualberta.cs.swapmyride;

/**
 * Created by Daniel on 2015-10-24.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Tab3 extends Fragment {

    TextView viewFriends;
    TextView pastTrades;
    TextView activeTrades;
    TextView editProfile;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab3,container,false);

        viewFriends = (TextView) v.findViewById(R.id.viewFriends);
        pastTrades = (TextView) v.findViewById(R.id.pastTrades);
        activeTrades = (TextView) v.findViewById(R.id.activeTrades);
        editProfile = (TextView) v.findViewById(R.id.editeProfile);


        viewFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ViewFriendsActivity.class);
                startActivity(intent);
            }
        });

        pastTrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        activeTrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }
}