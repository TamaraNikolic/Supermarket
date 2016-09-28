package supermarket.main.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

import supermarket.main.R;
import supermarket.main.components.CustomTextView;
import supermarket.main.data.DataCategory;

/**
 * Created by cubesschool3 on 9/28/16.
 */
public class ExtendableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<DataCategory> _listDataHeader; // header titles
    // child data in format of header title, child title


    public ExtendableListAdapter(Context context, List<DataCategory> listDataHeader) {
        this._context = context;
        this._listDataHeader = listDataHeader;

    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return _listDataHeader.get(i).subcategories.size();
    }

    @Override
    public DataCategory getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public String getChild(int groupPosition, int childPosititon) {

    return this._listDataHeader.get(groupPosition).subcategories.get(childPosititon).name;


    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = getGroup(groupPosition).name;
        if (convertView == null) {
            LayoutInflater infalInflater = LayoutInflater.from(_context);
            convertView = infalInflater.inflate(R.layout.drawer_item_layout, null);
        }

        CustomTextView name = (CustomTextView) convertView
                .findViewById(R.id.textViewDrawerListParent);
        name.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.drawer_item_child, null);
        }

        CustomTextView txtListChild = (CustomTextView) convertView
                .findViewById(R.id.textViewChild);

        txtListChild.setText(childText);
        return convertView;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
