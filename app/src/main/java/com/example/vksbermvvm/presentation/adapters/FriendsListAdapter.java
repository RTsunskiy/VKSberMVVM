package com.example.vksbermvvm.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.domain.model.model.Profile;
import com.example.vksbermvvm.presentation.utils.OnFriendClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Адаптер списка друзей пользователя унаследованный от интерфейса Filterable
 * для осуществления поиска друга в списке
 */
public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.FriendHolder> implements Filterable {

    private Context mContext;
    /**
     * Список профилей друзей пользователя, которые необходимо отобразить
     */
    private List<Profile> mFriendsList;
    /**
     * Слушатель нажатий на элемент списка
     */
    private OnFriendClickListener mClickListener;
    /**
     * Список профилей друзей, используемый при фильтрации списка (SearchView)
     */
    private List<Profile> mFriendsListFull;
    /**
     * Реализация класса, осуществляющего фильтрацию списка друзей и наполнение коллекции с отфильтрованным списком
     */
    private Filter friendFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Profile> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mFriendsListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Profile item : mFriendsListFull) {
                    if (item.getmFirstName().toLowerCase().contains(filterPattern) || item.getmLastName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        /**
         * Метод для публикации результатов фильтрации
         */
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mFriendsList.clear();
            mFriendsList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    public FriendsListAdapter(Context context, List<Profile> friendsList) {
        mContext = context;
        mFriendsList = new ArrayList<>(friendsList);
        mFriendsListFull = new ArrayList<>(mFriendsList);
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
                .placeholder(R.drawable.ic_iconfinder_user)
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

    /**
     * Метод для получения отфильтрованного списка друзей
     *
     * @return возвращает объект класса Filter
     */
    @Override
    public Filter getFilter() {
        return friendFilter;
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
