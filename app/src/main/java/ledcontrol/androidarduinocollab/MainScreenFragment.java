package ledcontrol.androidarduinocollab;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 * Created by Danyon on 2015-10-12.
 */
public class MainScreenFragment extends Fragment{
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static BluetoothAdapter mAdapter = null;
    private static OutputStream outStream = null;
    private static BluetoothSocket mSocket = null;
    private static Timer timer;
    private final Handler handler = new Handler();
    private static BluetoothDevice device;
    private ImageView btButton;
    private ImageView disconnectButton;
    private static TimerTask sendMessage;
    private static boolean isSchedule = false;
    private int case_ = 0;
    private int message = 0;


    private int f1;
    private int s2;
    private int t3;
    private int f4;
    private int f5;
    private int s6;
    private int s7;
    private int e8;
    private int n9;
    private int t10;
    private int e11;
    private int t12;
    private int t13;

    private int CS;


    private int data_0 = 0;

    private static MainScreenFragment mMainFrag = null;


    public MainScreenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mMainFrag = MainScreenFragment.this;
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);



//        final TextView one = (TextView) rootView.findViewById(R.id.textView);
//        final TextView two = (TextView) rootView.findViewById(R.id.textView2);
//        final TextView three = (TextView) rootView.findViewById(R.id.textView3);
//        final TextView four = (TextView) rootView.findViewById(R.id.textView4);
//        final TextView five = (TextView) rootView.findViewById(R.id.textView5);
//        final TextView six = (TextView) rootView.findViewById(R.id.textView6);
//        final TextView seven = (TextView) rootView.findViewById(R.id.textView7);
//        final TextView eight = (TextView) rootView.findViewById(R.id.textView8);
//        final TextView nine = (TextView) rootView.findViewById(R.id.textView9);
//        final TextView ten = (TextView) rootView.findViewById(R.id.textView10);
//        final TextView eleven = (TextView) rootView.findViewById(R.id.textView11);
//        final TextView twelve = (TextView) rootView.findViewById(R.id.textView12);
//        final TextView thirteen = (TextView) rootView.findViewById(R.id.textView13);




        timer = new Timer();
        sendMessage = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
//                        rootView.setOnTouchListener(new View.OnTouchListener() {
//                            @Override
//                            public boolean onTouch(View v, MotionEvent event) {
                                sendInfo();
//                                return false;
//                            }
//                        });

                    }
                });
            }
        };


        // Get Bluetooth adapter
        mAdapter = BluetoothAdapter.getDefaultAdapter();
        checkBTState();

        btButton = (ImageView)rootView.findViewById(R.id.btButton);
        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainScreen.explodeDialog2();
            }
        });

        disconnectButton = (ImageView)rootView.findViewById(R.id.disconnect);
        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disconnectDevice();
            }
        });

        return rootView; //what should i return if i have no rootview. do i need a rootview?
    }
    protected void sendInfo() {
        if (outStream != null) {
            // encode data into blocks

            data_0 = 0;

            if(MainScreen.pattern1.getColorFilter()!= null)
                data_0 = data_0 | 0 ;
            else if(MainScreen.pattern2.getColorFilter()!= null)
                data_0 = data_0 | 1 ;
            else if(MainScreen.pattern3.getColorFilter()!= null)
                data_0 = data_0 | 2 ;
            else if(MainScreen.pattern4.getColorFilter()!= null)
                data_0 = data_0 | 3 ;
            else if(MainScreen.pattern5.getColorFilter()!= null)
                data_0 = data_0 | 4 ;
            else if(MainScreen.pattern6.getColorFilter()!= null)
                data_0 = data_0 | 5 ;
            else
                data_0 = data_0 | 6 ;



            String bin1 =  new BigInteger(String.format("%08X", MainScreen.color1.getColor()), 16).toString(2);
            String bin2 =  new BigInteger(String.format("%08X", MainScreen.color2.getColor()), 16).toString(2);
            String bin3 =  new BigInteger(String.format("%08X", MainScreen.color3.getColor()), 16).toString(2);
            String bin4 =  new BigInteger(String.format("%08X", MainScreen.color4.getColor()), 16).toString(2);
            int full = 255;
            // break down into 4 bytes for transmission
            f1 = data_0;
            s2 = Integer.parseInt(bin1.substring(8, 16), 2)*(Integer.parseInt(bin1.substring(0, 8), 2)/full);
            t3 = Integer.parseInt(bin1.substring(16, 24), 2)*(Integer.parseInt(bin1.substring(0, 8), 2)/full);
            f4 = Integer.parseInt(bin1.substring(24, 32), 2)*(Integer.parseInt(bin1.substring(0, 8), 2)/full);
            f5 = Integer.parseInt(bin2.substring(8, 16), 2)*(Integer.parseInt(bin2.substring(0, 8), 2)/full);
            s6 = Integer.parseInt(bin2.substring(16, 24), 2)*(Integer.parseInt(bin2.substring(0, 8), 2)/full);
            s7 = Integer.parseInt(bin2.substring(24, 32), 2)*(Integer.parseInt(bin2.substring(0, 8), 2)/full);
            e8 = Integer.parseInt(bin3.substring(8, 16), 2)*(Integer.parseInt(bin3.substring(0, 8), 2)/full);
            n9 = Integer.parseInt(bin3.substring(16, 24), 2)*(Integer.parseInt(bin3.substring(0, 8), 2)/full);
            t10 = Integer.parseInt(bin3.substring(24, 32), 2)*(Integer.parseInt(bin3.substring(0, 8), 2)/full);
            e11 = Integer.parseInt(bin4.substring(8, 16), 2)*(Integer.parseInt(bin4.substring(0, 8), 2)/full);
            t12 = Integer.parseInt(bin4.substring(16, 24), 2)*(Integer.parseInt(bin4.substring(0, 8), 2)/full);
            t13 = Integer.parseInt(bin4.substring(24, 32), 2)*(Integer.parseInt(bin4.substring(0, 8), 2)/full);



//                            one.setText(String.valueOf(String.format("%03d", f1)));
//                            two.setText(String.valueOf(String.format("%03d", s2)));
//                            three.setText(String.valueOf(String.format("%03d", t3)));
//                            four.setText(String.valueOf(String.format("%03d", f4)));
//                            five.setText(String.valueOf(String.format("%03d", f5)));
//                            six.setText(String.valueOf(String.format("%03d", s6)));
//                            seven.setText(String.valueOf(String.format("%03d", s7)));
//                            eight.setText(String.valueOf(String.format("%03d", e8)));
//                            nine.setText(String.valueOf(String.format("%03d", n9)));
//                            ten.setText(String.valueOf(String.format("%03d", t10)));
//                            eleven.setText(String.valueOf(String.format("%03d", e11)));
//                            twelve.setText(String.valueOf(String.format("%03d", t12)));
//                            thirteen.setText(String.valueOf(String.format("%03d", t13)));

            CS = 26;
            // XOR checksum
            CS ^= f1;
            CS ^= s2;
            CS ^= t3;
            CS ^= f4;
            CS ^= f5;
            CS ^= s6;
            CS ^= s7;
            CS ^= e8;
            CS ^= n9;
            CS ^= t10;
            CS ^= e11;
            CS ^= t12;
            CS ^= t13;

            try {
                message = 0x06;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;

            try {
                message = 0x85;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;


            try {
                message = 0x1A;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;

            try {
                Thread.sleep(1);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }


            try {
                message = f1;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;
            try {
                message = 0;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;


            try {
                message = s2;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;
            try {
                message = 0;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;

            try {
                message = t3;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;
            try {
                message = 0;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;


            try {
                message = f4;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;
            try {
                message = 0;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;

            try {
                Thread.sleep(1);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            try {
                message = f5;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;
            try {
                message = 0;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;


            try {
                message = s6;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;
            try {
                message = 0;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;

            try {
                message = s7;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;
            try {
                message = 0;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;


            try {
                message = e8;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;
            try {
                message = 0;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;

            try {
                Thread.sleep(1);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            try {
                message = n9;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;
            try {
                message = 0;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;

            try {
                message = t10;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;
            try {
                message = 0;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;

            try {
                message = e11;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;
            try {
                message = 0;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;


            try {
                message = t12;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;
            try {
                message = 0;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;

            try {
                Thread.sleep(1);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            try {
                message = t13;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;
            try {
                message = 0;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_++;

            try {
                message = CS;
                outStream.write(message);
            } catch (Exception e) {
            }
            case_= 0;

        }
    }

    private static BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        if (Build.VERSION.SDK_INT >= 10) {
            try {
                final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[] {UUID.class});
                return (BluetoothSocket)m.invoke(device,MY_UUID);
            } catch (Exception e) { }
        }
        return device.createInsecureRfcommSocketToServiceRecord(MY_UUID);
    }

    public static void connect (String address, Context context) {

        try {
            device = mAdapter.getRemoteDevice(address);
        }catch (Exception e){
            Toast.makeText(context,"Invalid Address",Toast.LENGTH_LONG).show();
        }

        try {
            mSocket = createBluetoothSocket(device);
        } catch (Exception e) {

        }

        mAdapter.cancelDiscovery();

        try {
            // try connecting to socket
            mSocket.connect();
        } catch (Exception e) {
            try {
                mSocket.close();
            } catch (Exception e1) {
            }
        }

        try {
            outStream = mSocket.getOutputStream();

            if (!isSchedule) {
                timer.schedule(sendMessage, 0, 1500);
                mMainFrag.sendInfo();
                isSchedule = true;
            } else {
                Log.e("HI", "Timer already schedule");
            }
            Toast.makeText(context, "Connection Established", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {

        }
    }

    public static boolean isConnected() {
        // Check if the phone has already connected to bluetooth device
        return device != null;
    }

    private void disconnectDevice() {
        if (device != null) {
            device = null;

            try {
                outStream.close();
            } catch (Exception e) {
            }
            outStream = null;
            mSocket = null;
            Toast.makeText(getActivity(), "Bluetooth Device Disconnected", Toast.LENGTH_SHORT).show();
        }
    }


    private void checkBTState() {
        if (mAdapter == null) {
            Toast.makeText(getActivity().getApplicationContext(), "Bluetooth Not Supported", Toast.LENGTH_SHORT).show();
        } else {
            if (!mAdapter.isEnabled()) {
                startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
            }
        }
    }

}