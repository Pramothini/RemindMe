package relic.remindme;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.ListLab;
import entities.List_entity;

/**
 * Populates the list details of all the lists that the user had created in the home page
 */
public class HomePageListFragment extends ListFragment {
    private ArrayList<List_entity> mListEntity = new ArrayList<List_entity>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("List Title");
        mListEntity = ListLab.get(getActivity()).getMlist();
        if(mListEntity != null && mListEntity.size() > 0) {
            Toast.makeText(getActivity(), "mlistentity is not null and "+mListEntity.get(0)+" is the first list item", Toast.LENGTH_SHORT).show();
            ListsAdapter adapter = new ListsAdapter(mListEntity);
            setListAdapter(adapter);
        }
        else{
            Toast.makeText(getActivity(), "mlistentity is null", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }


    private class ListsAdapter extends ArrayAdapter<List_entity> {
        public ListsAdapter(ArrayList<List_entity> students) {
            super(getActivity(), android.R.layout.simple_list_item_1, students);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                    .inflate(R.layout.controller_activity_homepage, null);
            }


            TextView idTextView = (TextView)convertView.findViewById(R.id.listName);


                final List_entity l = getItem(position);

                idTextView.setText(l.getListName() + "  ");
            /**
             * To make the search visible only before the first item of the list
             */
            if(position != 0 ){
                RelativeLayout rl = (RelativeLayout)convertView.findViewById(R.id.searchLayout);
                if(rl.getVisibility() == View.VISIBLE)
                     rl.setVisibility(View.GONE);
            }

            /**
             * To make the add new list button visible only after the last list item
             */
            if(position == (mListEntity.size()-1)) {
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

