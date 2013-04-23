package model;

import android.provider.Settings;

public class Issue {
    private String title;
    private String description;
    private String location;
    private String imagePath;
    private String device_uuid;

    public Issue(String title, String description, String location, String imagePath, String device_uuid) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.imagePath = imagePath;
        this.device_uuid = device_uuid;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getOwnerDeviceId() {
        return device_uuid;
    }

    @Override
    public String toString() {
        return title;
    }

    public boolean isValid() {
        return (imagePath != null);
    }
}
