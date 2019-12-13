package com.example.vksbermvvm.presentation.adapters;

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

/**
 * Адаптер списка групп, на которые подписан пользователь
 */
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
                .placeholder(R.drawable.ic_iconfinder_user)
                .error(R.drawable.vk_gray_transparent_shape)
                .into(holder.mGroupPhoto);

        if (group.getmIsClosed() == 0) {
            holder.mIsClosedIndicator.setBackground(mContext.getResources().getDrawable(R.drawable.ic_iconfinder_icon_open));
        }
        else holder.mIsClosedIndicator.setBackground(mContext.getResources().getDrawable(R.drawable.ic_iconfinder_close));
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