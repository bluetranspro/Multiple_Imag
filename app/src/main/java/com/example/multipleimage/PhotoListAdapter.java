package com.example.multipleimage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.topicViewHolder> {
    Context context;
    List<String> imagesEncodedList;
  //  ArrayList<ClassList> classLists;
    public static ArrayList<Uri> dayString;
    Drawable yourDrawable;
    public PhotoListAdapter(Context context, ArrayList<Uri> imagesEncodedList) {
        this.context = context;
        this.dayString = imagesEncodedList;
    }

    @NonNull
    @Override
    public topicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.img, parent,false);
        return new topicViewHolder(view);
    }



    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull topicViewHolder holder, final int position) {
       /* ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        int randomAndroidColor = generator.getColor(position);

        holder.rl_topic.setBackgroundColor(randomAndroidColor);*/
       /* holder.tv_teacher_name.setText(classLists.get(position).getBranchName());
        holder.tv_center_name.setText(classLists.get(position).getClassName());
            holder.tv_class_name.setText("₹"+classLists.get(position).getAdmissionFees());
        holder.tv_act_stat.setVisibility(View.GONE);*/
                //holder.tv_act_stat.setText(classLists.get(position).getMonthlyFees());
        Uri imageUri = dayString.get(position);
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.img.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return dayString.size();
    }

    public class topicViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public topicViewHolder(@NonNull View itemView) {
            super(itemView);
         /*   tv_act_stat=itemView.findViewById(R.id.tv_act_stat);
            tv_teacher_name=itemView.findViewById(R.id.tv_teacher_name);
            tv_center_name=itemView.findViewById(R.id.tv_center_name);
            tv_class_name=itemView.findViewById(R.id.tv_class_name);*/
            img=itemView.findViewById(R.id.img);
        }
    }

    public Bitmap loadBitmap(Uri url)
    {
        Bitmap bm = null;
        InputStream is = null;
        BufferedInputStream bis = null;
        try
        {
            URLConnection conn = new URL(String.valueOf(url)).openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is, 8192);
            bm = BitmapFactory.decodeStream(bis);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (bis != null)
            {
                try
                {
                    bis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return bm;
    }
}
