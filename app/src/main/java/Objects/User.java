package Objects;

import android.graphics.Bitmap;

/**
 * Created by Ofek on 21-Jul-17.
 */

public class User {
    String name;
    Bitmap photo;

    public User() {
        this.name =null;
        this.photo = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public Bitmap getPhoto() {
        return photo;
    }
}
