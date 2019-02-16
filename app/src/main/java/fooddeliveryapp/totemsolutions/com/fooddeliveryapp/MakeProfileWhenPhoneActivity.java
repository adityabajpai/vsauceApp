package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MakeProfileWhenPhoneActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    public static final String STORAGE_PATH_USERDP = "userdp/";
    public static final String DATABASE_PATH_USERDP = "userdp";
    static final int DIALOG_ID = 0;
    Button saveInfoBtn;
    Spinner userGender;
    Button userDOB;
    EditText userName, userAddress, userEmail;
    int year_x, month_x, day_x;
    String gender;
    ImageView imageView;
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;
    StorageReference mStorageReference;
    Uri imageUri;
    DatePickerDialog.OnDateSetListener dpickerListerner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            year_x = year;
            month_x = month + 1;
            day_x = day;

            userDOB.setText(day_x + "/" + month_x + "/" + year_x);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_profile_when_phone);
        userName = (EditText) findViewById(R.id.userName);
        userAddress = (EditText) findViewById(R.id.userAddress);
        userDOB = (Button) findViewById(R.id.userDOB);
        userEmail = findViewById(R.id.userEmail);
        saveInfoBtn = (Button) findViewById(R.id.saveProfileBtn);
        imageView = findViewById(R.id.userImage);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mStorageReference = FirebaseStorage.getInstance().getReference();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(MakeProfileWhenPhoneActivity.this, UserRegisterActivity.class));
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosefileImage();
            }
        });
        saveInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
            }
        });
        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);
        userGender = (Spinner) findViewById(R.id.userGender);
        List<String> list = new ArrayList<>();
        list.add("Gender");
        list.add("Male");
        list.add("Female");
        list.add("Other");
        list.add("Prefer Not to say");
        showDialogOnButtonClick();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userGender.setAdapter(adapter);
        userGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    view.setEnabled(false);
                    return;
                } else {
                    String itemvalue = adapterView.getItemAtPosition(position).toString();
                    gender = itemvalue;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void showDialogOnButtonClick() {
        userDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerListerner, year_x, month_x, day_x);
        } else {
            return null;
        }
    }

    private void saveUserInfo() {
        if (imageUri != null) {
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Uploading Image...");
            dialog.show();
            //get the storage reference
            StorageReference ref = mStorageReference.child(STORAGE_PATH_USERDP + System.currentTimeMillis() + "." + getImageExt(imageUri));
            //add file to refrence
            ref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //dismiss dialog when success
                    dialog.dismiss();
                    Toast.makeText(MakeProfileWhenPhoneActivity.this, "IMAGE UPLOADED", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MakeProfileWhenPhoneActivity.this, AskScheduleActivity.class);
                    startActivity(intent);
                    String name = userName.getText().toString().trim();
                    String address = userAddress.getText().toString().trim();
                    String dob = userDOB.getText().toString().trim();
                    String email = userEmail.getText().toString().trim();
                    String uploadUrl = taskSnapshot.getDownloadUrl() + "";
                    UserInformation userInformation = new UserInformation(name, email, gender, dob, address, uploadUrl);
                    databaseReference.child("Users").child(user.getUid()).setValue(userInformation);
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();
                            Toast.makeText(MakeProfileWhenPhoneActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            dialog.setMessage("Uploaded" + (int) progress + "%");
                        }
                    });
        } else {
            Toast.makeText(MakeProfileWhenPhoneActivity.this, "Please Select image", Toast.LENGTH_LONG).show();
        }
    }

    private void choosefileImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getImageExt(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

}