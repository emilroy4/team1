    package com.example.team1;

    import android.Manifest;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.content.pm.PackageManager;
    import android.graphics.Bitmap;
    import android.net.Uri;
    import android.os.Build;
    import android.os.Bundle;
    import android.provider.MediaStore;
    import android.view.Menu;
    import android.view.View;
    import android.widget.ImageView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AlertDialog;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.app.ActivityCompat;
    import androidx.core.content.ContextCompat;
    import androidx.drawerlayout.widget.DrawerLayout;
    import androidx.navigation.NavController;
    import androidx.navigation.Navigation;
    import androidx.navigation.ui.AppBarConfiguration;
    import androidx.navigation.ui.NavigationUI;

    import com.example.team1.databinding.ActivityMainBinding;
    import com.google.android.material.navigation.NavigationView;
    import com.google.android.material.snackbar.Snackbar;

    public class MainActivity extends AppCompatActivity {

        private static final int REQUEST_IMAGE_FROM_GALLERY = 1;
        private static final int REQUEST_IMAGE_FROM_CAMERA = 2;

        private AppBarConfiguration mAppBarConfiguration;
        private ActivityMainBinding binding;
        private ImageView profileImageView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            setSupportActionBar(binding.appBarMain.toolbar);

            profileImageView = binding.navView.getHeaderView(0).findViewById(R.id.imageView);

            // Add a click listener to the profile image view
            profileImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Check for runtime permissions
                    if (checkPermissions()) {
                        showImageSelectionDialog();
                    } else {
                        requestPermissions();
                    }
                }
            });

            //mail
            binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendEmail();
                }
            });//end mail

            DrawerLayout drawer = binding.drawerLayout;
            NavigationView navigationView = binding.navView;
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_gallery, R.id.nav_calender)
                    .setOpenableLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onSupportNavigateUp() {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                    || super.onSupportNavigateUp();
        }

        // Check for the required permissions
        private boolean checkPermissions() {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        }

        // Request the required permissions
        private void requestPermissions() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                ActivityCompat.requestPermissions(this, permissions, REQUEST_IMAGE_FROM_GALLERY);
            }
        }

        //image
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == RESULT_OK) {
                if (requestCode == REQUEST_IMAGE_FROM_GALLERY && data != null) {
                    // User selected an image from the gallery
                    Uri selectedImageUri = data.getData();
                    // Update the profile image view with the selected image
                    profileImageView.setImageURI(selectedImageUri);

                } else if (requestCode == REQUEST_IMAGE_FROM_CAMERA && data != null) {
                    // User took a photo with the camera
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = (Bitmap) extras.get("data");
                        // Update the profile image view with the photo
                        profileImageView.setImageBitmap(photo);
                    }
                }
            }
        }

        // Open the gallery or camera when the user clicks on the profile image
        private void showImageSelectionDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Image Source");
            String[] options = {"Gallery", "Camera"};

            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        openGallery();
                    } else if (which == 1) {
                        openCamera();
                    }
                }
            });

            // Show the dialog
            builder.show();
        }



        private void openGallery() {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, REQUEST_IMAGE_FROM_GALLERY);
        }

        private void openCamera() {
            if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, REQUEST_IMAGE_FROM_CAMERA);
                } else {
                    // Handle the case where no camera app is available
                    Snackbar.make(binding.appBarMain.getRoot(), "No camera app found", Snackbar.LENGTH_SHORT).show();
                }
            } else {
                // Handle the case where the device doesn't have a camera
                Snackbar.make(binding.appBarMain.getRoot(), "Device doesn't have a camera", Snackbar.LENGTH_SHORT).show();
            }
        }



        //mail floating
        private void sendEmail() {
            // Create an intent to send an email
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("message/rfc822");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"dublinstrikers@gmail.com"});
            //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject of the email");
            //emailIntent.putExtra(Intent.EXTRA_TEXT, "Body of the email");

            // Verify that the intent will resolve to an activity
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            } else {
                // Handle the case where no email app is available
                Snackbar.make(binding.appBarMain.getRoot(), "No email app found", Snackbar.LENGTH_SHORT).show();
            }
        }



    }//end main