package model;

public class Issue {
    private String title;
    private String description;
    private String location;
    private String imagePath;

    public Issue(String title, String description, String location, String imagePath) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.imagePath = imagePath;
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

    @Override
    public String toString() {
        return title;
    }

    public boolean isValid() {
        return (imagePath != null);
    }
}
