package ledcontrol.androidarduinocollab;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



public class MainScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        ValueBar vBar;
        ColorPicker picker = (ColorPicker) findViewById(R.id.picker);

        vBar = (ValueBar) findViewById(R.id.valuebar);
//        int colorFinal = vBar.getColor();
//        TextView cText = (TextView) findViewById(R.id.txtB);
//        cText.setText(Integer.toString(colorFinal));
        picker.addValueBar(vBar);
        //To get the color
        picker.getColor();

        //To set the old selected color u can do it like this
        picker.setOldCenterColor(picker.getColor());
        // adds listener to the colorpicker which is implemented
        //in the activity
//        picker.setOnColorChangedListener(this);

        //to turn of showing the old color
        picker.setShowOldCenterColor(false);

        CircleButton color1 = (CircleButton) findViewById(R.id.circle1);
        CircleButton color2 = (CircleButton) findViewById(R.id.circle2);
        CircleButton color3 = (CircleButton) findViewById(R.id.circle3);
        CircleButton color4 = (CircleButton) findViewById(R.id.circle4);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
