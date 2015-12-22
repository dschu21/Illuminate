package ledcontrol.androidarduinocollab;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Comparator;
import java.util.Set;

public class MainScreen extends Activity {

    protected static CircleButton color1;
    protected static CircleButton color2;
    protected static CircleButton color3;
    protected static CircleButton color4;

    private static CheckedTextView ctv1;
    private static CheckedTextView ctv2;
    private static CheckedTextView ctv3;
    private static CheckedTextView ctv4;

    protected static ImageView pattern1;
    protected static ImageView pattern2;
    protected static ImageView pattern3;
    protected static ImageView pattern4;
    protected static ImageView pattern5;
    protected static ImageView pattern6;
    protected static ImageView pattern7;


    private static int barPosition1;
    private static int barPosition2;
    private static int barPosition3;
    private static int barPosition4;

    private int patternColor = 0xFF11BBFF;

    private static ObjectAnimator pressedAnimator;

    private static MainScreen mMainScreen = null;
    private static MainScreenFragment mMainFrag = null;
    private static Set<BluetoothDevice> pairedDevices = null;
    private static ArrayAdapter<String> BTArrayAdapter = null;
    private static ListView connectController = null;
    private Toast warning;

    private ColorPicker picker;
    private ValueBar vBar;

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
        mMainScreen = MainScreen.this;
        mMainFrag = new MainScreenFragment();
        BTArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);


        picker = (ColorPicker) findViewById(R.id.picker);
        vBar = (ValueBar) findViewById(R.id.valuebar);

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
        pattern5 = (ImageView) findViewById(R.id.pattern5);
        pattern6 = (ImageView) findViewById(R.id.pattern6);
        pattern7 = (ImageView) findViewById(R.id.pattern7);


        barPosition1 = vBar.mBarPointerHaloRadius;
        barPosition2 = vBar.mBarPointerHaloRadius;
        barPosition3 = vBar.mBarPointerHaloRadius;
        barPosition4 = vBar.mBarPointerHaloRadius;

        pattern1.setColorFilter(patternColor, PorterDuff.Mode.MULTIPLY);

        ctv1.setChecked(true);

        picker.addValueBar(vBar);
        picker.getColor();
        picker.setOldCenterColor(picker.getColor());
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
                mMainFrag.sendInfo();
                setFilter(pattern1);
            }
        });
        pattern2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainFrag.sendInfo();
                setFilter(pattern2);
            }
        });
        pattern3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainFrag.sendInfo();
                setFilter(pattern3);
            }
        });
        pattern4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainFrag.sendInfo();
                setFilter(pattern4);
            }
        });
        pattern5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainFrag.sendInfo();
                setFilter(pattern5);
            }
        });
        pattern6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainFrag.sendInfo();
                setFilter(pattern6);
            }
        });
        pattern7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainFrag.sendInfo();

                setFilter(pattern7);
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
        int color = button.getColor();
        if (!ctv.isChecked()) {
            ctv.toggle();
        }
        ctv1.setChecked(false);
        ctv2.setChecked(false);
        ctv3.setChecked(false);
        picker.mAngle = picker.colorToAngle(color);
        picker.mCenterNewColor = color;
        picker.mCenterNewPaint.setColor(color);
        picker.mPointerColor.setColor(color);
        picker.mPointerHaloPaint.setColor(color);
        picker.mPointerHaloPaint.setAlpha(0x50);
        vBar.setBarPointerPosition(getBarPosition());
        vBar.setColor(color);
        vBar.invalidate();
        picker.invalidate();

    }

    public void setFilter(ImageView pattern){

        pattern1.clearColorFilter();
        pattern2.clearColorFilter();
        pattern3.clearColorFilter();
        pattern4.clearColorFilter();
        pattern5.clearColorFilter();
        pattern6.clearColorFilter();
        pattern7.clearColorFilter();

        pattern.setColorFilter(0xFF11BBFF, PorterDuff.Mode.MULTIPLY);
        mMainFrag.sendInfo();
    }

    public static void setBarPosition(int position){
        if (ctv1.isChecked())
            barPosition1 = position;
        else if (ctv2.isChecked())
            barPosition2 = position;
        else if (ctv3.isChecked())
            barPosition3 = position;
        else
            barPosition4 = position;
    }

    public int getBarPosition(){
        if (ctv1.isChecked())
            return barPosition1;
        else if (ctv2.isChecked())
            return barPosition2;
        else if (ctv3.isChecked())
            return barPosition3;
        else
            return barPosition4;
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
           // mHandler.post(immersiveView);
        }
    }

    public static void explodeDialog2(){
        mMainScreen.explodeDialog();
    }

    public void explodeDialog(){
        if (MainScreenFragment.isConnected()) {
            showToast("Please disconnect any bluetooth device before connecting a new device");
            return;
        }
        // Clear BlueTooth list
        BTArrayAdapter.clear();

        // Get paired devices
        pairedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();

        for (BluetoothDevice device : pairedDevices) {
            BTArrayAdapter.add(device.getName() + "\n"
                    + device.getAddress());
        }

        // Sort the ListView
        BTArrayAdapter.sort(new Comparator<String>() {
            public int compare(String object1, String object2) {
                int res = String.CASE_INSENSITIVE_ORDER.compare(
                        object1.toString(), object2.toString());
                return res;
            }
        });

        // Build an alert dialog
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainScreen.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.custom, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("Connect");
        connectController = (ListView) convertView
                .findViewById(R.id.listView1);
        connectController.setAdapter(BTArrayAdapter);
        final Dialog dialog = alertDialog.show();

        connectController.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Get BlueTooth Address
                String temp = (String) connectController.getAdapter().getItem(
                        position);
                String address = temp.substring(temp.length() - 17,
                        temp.length());

                Log.e("HI", address);

                // Connect Device
                MainScreenFragment.connect(address,getApplicationContext());

                // Exit Dialog
                dialog.dismiss();
            }
        });
    }

    private void showToast(String text) {
        if (warning == null) {
            warning = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        }
        warning.setText(text);
        warning.setDuration(Toast.LENGTH_SHORT);
        warning.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mMainFrag.sendInfo();

        return false;
    }

}
