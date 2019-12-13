package com.example.vksbermvvm.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.domain.model.model.Group;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupsListAdapter extends RecyclerView.Adapter<GroupsListAdapter.GroupHolder> {

    private Context mContext;
    private List<Group> mGroupList;

    public GroupsListAdapter(Context context, List<Group> groupsList) {
        mContext = context;
        mGroupList = new ArrayList<>(groupsList);
    }


    @NonNull
    @Override
    public GroupsListAdapter.GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupsListAdapter.GroupHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_group, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull  GroupsListAdapter.GroupHolder holder, int position) {
        Group group = mGroupList.get(position);
        holder.mGroupName.setText(group.getmName());
        holder.mGroupScreenName.setText(group.getmScreenName());
        Picasso.with(mContext.getApplicationContext())
                .load(group.getmPhoto())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.vk_gray_transparent_shape)
                .into(holder.mGroupPhoto);

        if (group.getmIsClosed() == 0) {
            holder.mIsClosedIndicator.setBackgroundColor(mContext.getResources().getColor(R.color.colorGreen));
        }
        else holder.mIsClosedIndicator.setBackgroundColor(mContext.getResources().getColor(R.color.colorRed));
    }

    @Override
    public int getItemCount() {
        return mGroupList.size();
    }

    class GroupHolder extends RecyclerView.ViewHolder {

        TextView mGroupName;
        TextView mGroupScreenName;
        CircleImageView mIsClosedIndicator;
        CircleImageView mGroupPhoto;

        GroupHolder(@NonNull View itemView) {
            super(itemView);
            mGroupName = itemView.findViewById(R.id.group_name);
            mGroupScreenName = itemView.findViewById(R.id.group_screen_name);
            mGroupPhoto = itemView.findViewById(R.id.group_photo);
            mIsClosedIndicator = itemView.findViewById(R.id.closed_indicator);
        }

    }


}