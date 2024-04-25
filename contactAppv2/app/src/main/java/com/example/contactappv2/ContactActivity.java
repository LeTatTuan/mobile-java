package com.example.contactappv2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cloudinary.Configuration;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.contactappv2.model.Contact;
import com.example.contactappv2.viewModel.AppDatabase;
import com.example.contactappv2.viewModel.ContactDAO;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Map;


public class ContactActivity extends AppCompatActivity {
    private static final int CAMERA_INTENT=100;

    private ImageButton btnCancel;
    private ImageButton btnSave;
    private ImageButton btnTakeAPhoto;
    private TextInputEditText etName;
    private TextInputEditText etPhone;
    private TextInputEditText etEmail;
    private ImageView imageView;
    private Uri imageUri;
    private Bitmap bitmap;
    private String imagePath = "";


    private AppDatabase appDatabase;
    private ContactDAO contactDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        btnCancel = findViewById(R.id.btn_cancel);
        btnSave = findViewById(R.id.btn_save);
        btnTakeAPhoto = findViewById(R.id.btn_take_photo);
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        imageView = findViewById(R.id.image_view);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        appDatabase = AppDatabase.getInstance(getApplicationContext());
                        contactDAO = appDatabase.contactDAO();
                        contactDAO.deleteAll();
                        finish();
                    }
                });
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String email = etEmail.getText().toString();

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String avatar = uploadImage(imagePath);;
                        Contact contact = new Contact(name, phone, email, avatar);
                        appDatabase = AppDatabase.getInstance(getApplicationContext());
                        contactDAO = appDatabase.contactDAO();
                        contactDAO.insert(contact);

                        Intent intent = new Intent();
                        intent.putExtra("isSuccess", 1);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
        });

        btnTakeAPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture(view);
            }
        });
    }


    public void takePicture(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, CAMERA_INTENT);
    }

    public String uploadImage(String imagePath) {
        final String[] result = {""};
        MediaManager.get().upload(imagePath)
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        // Đã bắt đầu quá trình upload
                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        // Tiến trình upload
                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        String imageUrl = (String) resultData.get("url");
                        result[0] = imageUrl;
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        // Đã xảy ra lỗi trong quá trình upload
                        Log.e("DEBUG", "Error: " + error.getDescription());
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        // Đã xảy ra lỗi và quá trình upload sẽ được thử lại
                    }
                })
                .dispatch();
        return result[0];
    }

    private String getPathFromUri(Uri uri) {
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String imagePath = cursor.getString(columnIndex);
        cursor.close();
        return imagePath;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            String imagePath = getPathFromUri(imageUri);
            Log.d("DEBUG", imagePath);
            if (imageUri == null) {
                bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);

            } else
                imageView.setImageURI(imageUri);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
