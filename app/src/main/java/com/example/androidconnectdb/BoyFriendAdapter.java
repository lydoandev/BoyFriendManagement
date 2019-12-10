package com.example.androidconnectdb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BoyFriendAdapter extends RecyclerView.Adapter<BoyFriendAdapter.UserViewHolder> {
    List<BoyFriend> boyFriends;
    OnItemListener listener;
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.txtName.setText(boyFriends.get(position).name);
        holder.txtDate.setText(boyFriends.get(position).date);
    }

    @Override
    public int getItemCount() {
        return boyFriends.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtDate;
        Button btnDelete;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.name);
            txtDate = itemView.findViewById(R.id.date);

            btnDelete = itemView.findViewById(R.id.delete);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClicked(getAdapterPosition());
                }
            });
        }
    }

    interface OnItemListener {
        void onDeleteClicked(int position);
    }
}
