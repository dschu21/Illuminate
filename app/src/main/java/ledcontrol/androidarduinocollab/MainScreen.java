package ledcontrol.androidarduinocollab;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
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

    private static ImageView pattern1;
    private static ImageView pattern2;
    private static ImageView pattern3;
    private static ImageView pattern4;

    private static boolean pattern1Select;
    private static boolean pattern2Select;
    private static boolean pattern3Select;
    private static boolean pattern4Select;

    private static ObjectAnimator pressedAnimator;

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
        ctv2 = (CheckedTextView) findViewById(R.id.checkedTextView2);
        ctv3 = (CheckedTextView) findViewById(R.id.checkedTextView3);
        ctv4 = (CheckedTextView) findViewById(R.id.checkedTextView4);

        pattern1 = (ImageView) findViewById(R.id.pattern1);
        pattern2 = (ImageView) findViewById(R.id.pattern2);
        pattern3 = (ImageView) findViewById(R.id.pattern3);
        pattern4 = (ImageView) findViewById(R.id.pattern4);

        pattern1Select = true;
        pattern1Select = false;
        pattern1Select = false;
        pattern1Select = false;

        pattern1.setColorFilter(0xFF11BBFF, PorterDuff.Mode.MULTIPLY);

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
                uncheckCTV(color1, ctv1, ctv2, ctv3, ctv4);
            }
        });
        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uncheckCTV(color2, ctv2, ctv1, ctv3, ctv4);


            }
        });
        color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uncheckCTV(color3, ctv3, ctv1, ctv2, ctv4);

            }
        });
        color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uncheckCTV(color4, ctv4, ctv1, ctv2, ctv3);
            }
        });

        pattern1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFilter(pattern1, pattern1Select, pattern2Select, pattern3Select, pattern4Select);
            }
        });
        pattern2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFilter(pattern2, pattern2Select, pattern1Select, pattern3Select, pattern4Select);
            }
        });
        pattern3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFilter(pattern3, pattern3Select, pattern1Select, pattern2Select, pattern4Select);
            }
        });
        pattern4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFilter(pattern4, pattern4Select, pattern1Select, pattern2Select, pattern3Select);
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

    public void uncheckCTV(CircleButton button, CheckedTextView ctv, CheckedTextView ctv1,
                           CheckedTextView ctv2, CheckedTextView ctv3){

        if (!ctv.isChecked()) {
            ctv.toggle();
        }
        ctv1.setChecked(false);
        ctv2.setChecked(false);
        ctv3.setChecked(false);
        ColorPicker.mAngle = ColorPicker.colorToAngle(button.getColor());
        System.out.println("mangle" + ColorPicker.mAngle);
        ColorPicker.mCenterNewColor = button.getColor();
        System.out.println("center" + ColorPicker.mCenterNewColor);
        ColorPicker.mCenterNewPaint.setColor(button.getColor());
        System.out.println("centerpaint" + ColorPicker.mCenterNewPaint);
        ColorPicker.mPointerColor.setColor(button.getColor());
        //reset bar position
        invalidateOptionsMenu();

    }

    public void setFilter(ImageView pattern, boolean pattern1, boolean pattern2,
                          boolean pattern3, boolean pattern4){

        pattern2 = false;
        pattern3 = false;
        pattern4 = false;
        this.pattern1.clearColorFilter();
        this.pattern2.clearColorFilter();
        this.pattern3.clearColorFilter();
        this.pattern4.clearColorFilter();

        if(!pattern1){
            pattern.setColorFilter(0xFF11BBFF, PorterDuff.Mode.MULTIPLY);
            pattern1 = true;
        }


    }


}
