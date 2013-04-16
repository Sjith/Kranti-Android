package repository;

import Storage.DataStorage;
import Storage.ServerStorage;
import android.content.Context;
import android.widget.Toast;
import model.Issue;

import java.util.List;

public class IssueRepository {
    private DataStorage dataStorage;
    private ServerStorage serverStorage;
    private final Context context;

    public IssueRepository(Context _context) {
        context = _context;
        dataStorage = new DataStorage(context);
        serverStorage = new ServerStorage();
    }

    public void createIssue(String title, String description, String location, String imagePath) {
        Issue issue = new Issue(title, description, location, imagePath);
        dataStorage.store(issue);
        serverStorage.store(issue);
        Toast.makeText(context, "Report captured", 1).show();
    }

    public List<Issue> getIssues() {
        return dataStorage.get();
    }
}
