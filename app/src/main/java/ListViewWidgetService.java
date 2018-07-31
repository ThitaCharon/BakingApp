import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.thita.bakingapp.Model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class ListViewWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetListView(this.getApplicationContext(), intent);
    }
}

class WidgetListView implements RemoteViewsService.RemoteViewsFactory{

    private Context context = null;
    private Cursor cursor = null;
    List<Ingredient> ingredients = new ArrayList<>();

    public WidgetListView(Context context, Intent intent) {
        this.context = context;
    }

    @Override
    public void onCreate() { }

    @Override
    public void onDataSetChanged() {
        if (cursor != null){
            cursor.close(); }
    }

    @Override
    public void onDestroy() {
        if (cursor != null){
            cursor.close(); }
    }

    @Override
    public int getCount() {
        if (cursor != null){
            return cursor.getCount();
        }
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        return null;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}