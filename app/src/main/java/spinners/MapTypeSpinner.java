package spinners;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

import data.DataManager;

/**
 * Created by david on 12/14/17.
 */

public class MapTypeSpinner extends Activity implements AdapterView.OnItemSelectedListener
{
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        String item = parent.getItemAtPosition(pos).toString();
        DataManager dman = DataManager.getInstance();
        dman.setMapType(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
