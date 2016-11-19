package com.abdelmun3m.prototype;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 3M on 19/11/2016.
 */
public class FeatureAdapter extends BaseExpandableListAdapter {

    private Context ctx;
    private HashMap<String,List<String>>Feature_category;
    private List<String>FeatureList;

    public FeatureAdapter(Context ctx,HashMap<String,List<String>>Feature_category,List<String>FeatureList)
    {
        this.ctx=ctx;
        this.Feature_category=Feature_category;
        this.FeatureList=FeatureList;
    }
    @Override
    public int getGroupCount() {
        return FeatureList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return Feature_category.get(FeatureList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return FeatureList.get(groupPosition);
    }

    @Override
    public Object getChild(int parent, int child ) {
        return Feature_category.get(FeatureList.get(parent)).get(child);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertView, ViewGroup parentView) {
        String group_title=(String)getGroup(parent);
        if(convertView==null)
        {
            LayoutInflater inflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.parent_list,parentView,false);
        }
        TextView parent_textView=(TextView)convertView.findViewById(R.id.textView2);
        parent_textView.setTypeface(null, Typeface.BOLD);
        parent_textView.setText(group_title);
        return convertView;
    }

    @Override
    public View getChildView(int parent, int child, boolean LastChild, View convertView, ViewGroup parentView) {
        String child_title=(String)getChild(parent,child);
        if(convertView==null)
        {
            LayoutInflater inflator= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflator.inflate(R.layout.child_parent_list,parentView,false);
        }
        TextView child_textView=(TextView)convertView.findViewById(R.id.textView4);
        child_textView.setText(child_title);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
