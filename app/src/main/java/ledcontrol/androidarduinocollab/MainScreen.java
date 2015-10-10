package ledcontrol.androidarduinocollab;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Set;


public class MainScreen extends Activity {

    private static CircleButton color1;
    private static CircleButton color2;
    private static CircleButton color3;
    private static CircleButton color4;

    private static CheckedTextView ctv1;
    private static CheckedTextView ctv2;
    private static CheckedTextView ctv3;
    private static CheckedTextView ctv4;

    private static MainScreen mMainScreen = null;
    private static Set<BluetoothDevice> pairedDevices = null;
    private static ArrayAdapter<String> BTArrayAdapter = null;
    private static ListView connectController = null;
    private Toast warning;

    private Handler mHandler = new Handler();
    private Runnable immersiveView = new Runnable() {
        public void run() {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        ValueBar vBar;
        ColorPicker picker = (ColorPicker) findViewById(R.id.picker);

        color1 = (CircleButton) findViewById(R.id.circle1);
        color2 = (CircleButton) findViewById(R.id.circle2);
        color3 = (CircleButton) findViewById(R.id.circle3);
        color4 = (CircleButton) findViewById(R.id.circle4);

        ctv1 = (CheckedTextView) findViewById(R.id.checkedTextView1);
        ctv2= (CheckedTextView) findViewById(R.id.checkedTextView2);
        ctv3 = (CheckedTextView) findViewById(R.id.checkedTextView3);
        ctv4 = (CheckedTextView) findViewById(R.id.checkedTextView4);

        ctv1.setChecked(true);

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


        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uncheckCTV(ctv1, ctv2, ctv3, ctv4);
            }
        });
        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uncheckCTV(ctv2, ctv1, ctv3, ctv4);


            }
        });
        color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uncheckCTV(ctv3, ctv1, ctv2, ctv4);

            }
        });
        color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uncheckCTV(ctv4, ctv1, ctv2, ctv3);
            }
        });


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
    
    public void uncheckCTV(CheckedTextView ctv, CheckedTextView ctv1, CheckedTextView ctv2,
                      CheckedTextView ctv3){
        if (!ctv.isChecked())
            ctv.toggle();
        ctv1.setChecked(false);
        ctv2.setChecked(false);
        ctv3.setChecked(false);

    }

    public static CircleButton getActiveButton() {
        if(ctv1.isChecked())
            return color1;
        else if (ctv2.isChecked())
            return color2;
        else if (ctv3.isChecked())
            return color3;
        else
            return color4;
    }

}
