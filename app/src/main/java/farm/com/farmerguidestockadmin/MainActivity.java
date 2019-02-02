package farm.com.farmerguidestockadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.admin.stockadminfarmapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import farm.com.farmerguidestockadmin.dialog.LogOutDialog;
import farm.com.farmerguidestockadmin.security.MD5Helper;
import farm.com.farmerguidestockadmin.sharepref.SharePrefHelper;
import farm.com.farmerguidestockadmin.statics.RefStatic;

public class MainActivity extends AppCompatActivity {
    MD5Helper helper = new MD5Helper();
    String pass = "";
    SharePrefHelper sharePrefHelper = null;

    class LogOutResult implements LogOutDialog.OnDialogResult {
        LogOutResult() {
        }

        public void finish() {
            MainActivity.this.sharePrefHelper.setLogOut();
            System.exit(0);
        }
    }

    class PassListener implements ValueEventListener {
        PassListener() {
        }

        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (!MainActivity.this.pass.equals((String) dataSnapshot.child("pass").getValue())) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, EnterPassword.class));
                MainActivity.this.finish();
            }
        }

        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_change_password:
                ShowSetting();
                return true;
            case R.id.menu_log_out:
                LogOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void LogOut() {
        new LogOutDialog(this, new LogOutResult()).show();
    }

    private void ShowSetting() {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.sharePrefHelper = new SharePrefHelper(getApplicationContext());
        this.pass = this.sharePrefHelper.getPassword();
        if (this.pass.equals("")) {
            startActivity(new Intent(this, EnterPassword.class));
            finish();
            return;
        }
        RefStatic.stockPass.addListenerForSingleValueEvent(new PassListener());
    }
}
