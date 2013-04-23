package Storage;

import android.os.AsyncTask;
import android.util.Log;
import model.Issue;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;

public class ServerStorage extends AsyncTask<String, Void, String> {

    private Issue issue;

    @Override
    protected String doInBackground(String... params) {
        return reportIssue();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public String reportIssue() {
        HttpClient httpClient = new DefaultHttpClient();
        String url = "http://kranti-api.herokuapp.com/issues";
        HttpPost httpPost = new HttpPost(url);
        String result = null;

        httpPost.setEntity(generatePostEntity());

        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode >= 200 && statusCode <= 210) {
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream content = httpEntity.getContent();
                result = toString(content);
            }
        } catch (IOException httpResponseError) {
              Log.e("HTTP Response", "IO error");
            return "404 error";
        }
        return result;
    }

    private MultipartEntity generatePostEntity() {
        try {
            MultipartEntity entity = new MultipartEntity();
            entity.addPart("issue[uuid]", new StringBody(issue.getOwnerDeviceId()));
            entity.addPart("issue[title]", new StringBody(issue.getTitle()));
            entity.addPart("issue[description]", new StringBody(issue.getDescription()));
            entity.addPart("issue[location]", new StringBody(issue.getLocation()));
            entity.addPart("image", new FileBody(new File(issue.getImagePath()), "image/png"));
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String resultString) {
        super.onPostExecute(resultString);
    }

    private String toString(InputStream content) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        StringBuilder result = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException readerException) {
            readerException.printStackTrace();
        }
        return result.toString();
    }

    public void store(Issue issue) {
        this.issue = issue;
        execute();
    }
}
