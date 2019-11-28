package ash.pickshunter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PreferenceHelper {

    private final String USER = "user";
    private final String PREF_NAME = "PICKS_HUNTER";
    private SharedPreferences sharedPreferences;

    public PreferenceHelper(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
    }

    public void putUser(User user) {
        this.sharedPreferences.edit().putString(USER, (new Gson()).toJson(user)).apply();
    }

    public User getUser() {
        User user = null;
        String data = this.sharedPreferences.getString(USER, "");
        if (!TextUtils.isEmpty(data)) {
            user = new Gson().fromJson(data, User.class);
        }

        return user;
    }
}