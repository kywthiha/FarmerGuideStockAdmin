package farm.com.farmerguidestockadmin.sharepref;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import farm.com.farmerguidestockadmin.security.MD5Helper;

public class SharePrefHelper {
    private static String passName = "";
    private static String prefName = "";
    private Editor editor = null;
    private MD5Helper helper = new MD5Helper();
    private String password;
    private SharedPreferences preferences = null;

    public SharePrefHelper(Context context) {
        prefName = this.helper.decryptMD("shareName");
        passName = this.helper.decryptMD("pass");
        this.preferences = context.getSharedPreferences(prefName, 0);
        this.editor = this.preferences.edit();
    }

    public String getPassword() {
        return this.preferences.getString(this.helper.decryptMD("pass"), "");
    }

    public void setPassword(String password) {
        this.password = this.helper.decryptMD(password);
        this.editor.putString(passName, this.password);
        this.editor.commit();
    }

    public void setLogOut() {
        this.editor.putString(passName, "");
        this.editor.commit();
    }
}
