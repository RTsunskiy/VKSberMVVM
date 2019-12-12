package com.example.vksbermvvm.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.domain.model.model.Profile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.FriendHolder> {

    private Context mContext;
    private List<Profile> mFriendsList;
    private OnFriendClickListener mClickListener;

    public FriendsListAdapter(Context context, List<Profile> friendsList) {
        mContext = context;
        mFriendsList = new ArrayList<>(friendsList);
    }

    public void setClickListener(@Nullable OnFriendClickListener clickListener) {
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FriendHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_friend, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        Profile profile = mFriendsList.get(position);
        holder.friendName.setText(profile.getmFirstName());
        holder.friendSername.setText(profile.getmLastName());
        Picasso.with(mContext.getApplicationContext())
                .load(profile.getmProfileImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.vk_gray_transparent_shape)
                .into(holder.friendPhoto);
        holder.itemView.setOnClickListener(v -> {
            if (mClickListener != null) {
                mClickListener.onItemClick(mFriendsList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mFriendsList.size();
    }

    class FriendHolder extends RecyclerView.ViewHolder {

        TextView friendName;
        TextView friendSername;
        CircleImageView friendPhoto;

        FriendHolder(@NonNull View itemView) {
            super(itemView);
            friendName = itemView.findViewById(R.id.friend_name);
            friendSername = itemView.findViewById(R.id.friend_surname);
            friendPhoto = itemView.findViewById(R.id.friends_photo);
        }

    }


}
