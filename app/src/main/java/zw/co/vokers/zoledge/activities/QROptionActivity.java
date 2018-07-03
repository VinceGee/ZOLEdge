package zw.co.vokers.zoledge.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

import zw.co.vokers.zoledge.R;

public class QROptionActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "QROptionActivity";
    private LinearLayout llSave;
    private LinearLayout llShare, ll_mainQR;
    private String _uri;
    private TextView tv_wifiname;
    private String strData;
    private ImageView ivQRImage;
    private String strSSID, strType, strPassword;
    private boolean isAlreadySave = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_option);
        //getting all information of wifi
        Bundle extras = getIntent().getExtras();
        strSSID = extras.getString("strSSID");
        strType = extras.getString("strType");
        strPassword = extras.getString("strPassword");
        bindView();
    }

    private void bindView() {
        ll_mainQR = (LinearLayout) findViewById(R.id.ll_mainQR);
        ivQRImage = (ImageView) findViewById(R.id.iv_main_image);
        tv_wifiname = (TextView) findViewById(R.id.tv_wifiname);
        tv_wifiname.setText(strSSID);
        llSave = (LinearLayout) findViewById(R.id.ll_save);
        llSave.setOnClickListener(this);
        llShare = (LinearLayout) findViewById(R.id.ll_share);
        llShare.setOnClickListener(this);

        generateQR(strSSID, strPassword, strType);

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //generate qr code using generateQR method
    private void generateQR(String strSSID, String password, String type) {
        strData = "WIFI:S:" + strSSID + ";T:" + type + ";P:" + password + ";";
        Log.i(TAG, "generateQR: " + strData);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final int colorQR = Color.BLACK;
                final int colorBackQR = Color.WHITE;
                final int width = 500;
                final int height = 500;

                try {
                    final Bitmap bitmapQR = generateBitmap(strData, width, height,
                            MARGIN_AUTOMATIC, colorQR, colorBackQR);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ivQRImage.setImageBitmap(bitmapQR);

                        }
                    });
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static public int MARGIN_AUTOMATIC = -1;

    // generate qr code bitmap using width ,height and color code
    static public Bitmap generateBitmap(@NonNull String contentsToEncode,
                                        int imageWidth, int imageHeight,
                                        int marginSize, int color, int colorBack)
            throws WriterException, IllegalStateException {

        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Should not be invoked from the UI thread");
        }

        Map<EncodeHintType, Object> hints = null;
        if (marginSize != MARGIN_AUTOMATIC) {
            hints = new EnumMap<>(EncodeHintType.class);
            // We want to generate with a custom margin size
            hints.put(EncodeHintType.MARGIN, marginSize);
        }

        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result = writer.encode(contentsToEncode, BarcodeFormat.QR_CODE, imageWidth, imageHeight, hints);

        final int width = result.getWidth();
        final int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? color : colorBack;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // save image of generate qr code
            case R.id.ll_save:
                llSave.setEnabled(false);
                saveImage();
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(_uri.replace("file://", "")))));
                Toast.makeText(getApplicationContext(), "QR code save successfully..", Toast.LENGTH_SHORT).show();
                break;
            //share generated qr code after saved
            case R.id.ll_share:
                if (!isAlreadySave)
                    saveImage();
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(_uri));
                shareIntent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
                break;
        }
    }

    /*save generated qr code*/
    private void saveImage() {
        isAlreadySave = true;
        Log.v("TAG", "saveImage is called");
        Bitmap bitmap;
        OutputStream output;
        // Retrieve the image from the res folder
        bitmap = getMainFrameBitmap();
        File filepath = Environment.getExternalStorageDirectory();
        // Create a new folder in SD Card
        File dir = new File(filepath.getAbsolutePath() + "/" + "QR code");
        dir.mkdirs();
        // Create a name for the saved image
        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String FileName = strSSID + "_" + ts + ".jpeg";
        File file = new File(dir, FileName);
        file.renameTo(file);
        _uri = "file://" + filepath.getAbsolutePath() + "/" + "QR code" + "/" + FileName;
        Log.d("uri=", _uri);
        try {
            output = new FileOutputStream(file);
            // Compress into png format image from 0% - 100%
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*getting bitmap of generated qr code with wifi name*/
    private Bitmap getMainFrameBitmap() {
        ll_mainQR.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(ll_mainQR.getDrawingCache());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            bitmap.setConfig(Bitmap.Config.ARGB_8888);
        }
        ll_mainQR.setDrawingCacheEnabled(false);
        return bitmap;
    }
}

