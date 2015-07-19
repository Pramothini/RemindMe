package relic.remindme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.ListLab;
import entities.List;


public class HomePageListFragment extends ListFragment {
    private ArrayList<List> mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("List Title");
        mList = ListLab.get(getActivity()).getMlist();
        ListsAdapter adapter = new ListsAdapter(mList);
        setListAdapter(adapter);
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class ListsAdapter extends ArrayAdapter<List> {
        public ListsAdapter(ArrayList<List> students) {
            super(getActivity(), android.R.layout.simple_list_item_1, students);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                    .inflate(R.layout.activity_homepage, null);
            }


            TextView idTextView = (TextView)convertView.findViewById(R.id.listName);


                final List l = getItem(position);

                idTextView.setText(l.getListName() + "  ");
            if(position != 0 ){
                RelativeLayout rl = (RelativeLayout)convertView.findViewById(R.id.searchLayout);
                if(rl.getVisibility() == View.VISIBLE)
                     rl.setVisibility(View.GONE);
            }

            if(position == (mList.size()-1)) {
                Button donebtn = (Button) convertView.findViewById(R.id.addNewListBtn);
                donebtn.setVisibility(View.VISIBLE);
                donebtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), ListItemActivity.class);
                        startActivity(i);
                    }
                });
            }


            RelativeLayout rlList = (RelativeLayout)convertView.findViewById(R.id.listRL);
            rlList.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), ListItemActivity.class);
                    startActivity(i);
                }
            });


            return convertView;
        }
    }



}

