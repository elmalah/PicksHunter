package ash.pickshunter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PreferenceHelper {

    private final String USER = "user";
    private final String TRIP_ID = "tripId";
    private final String USER_TYPE = "user_type";
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

    public void putUserType(String userType) {
        this.sharedPreferences.edit().putString(USER_TYPE, userType).apply();
    }

    public String getUserType() {
        String userType = "";
        String data = this.sharedPreferences.getString(USER_TYPE, "");
        if (!TextUtils.isEmpty(data)) {
            userType = data;
        }

        return userType;
    }

    public void putTripId(int tripId) {
        this.sharedPreferences.edit().putInt(TRIP_ID, tripId).apply();
    }

    public int getTripId() {
        int tripId;
        int data = this.sharedPreferences.getInt(TRIP_ID, 0);
        tripId = data;

        return tripId;
    }

}