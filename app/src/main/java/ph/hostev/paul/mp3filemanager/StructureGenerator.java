package ph.hostev.paul.mp3filemanager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StructureGenerator {

    private Map<String, List<String>> tree = null;

    public StructureGenerator() {
        this.tree = new HashMap<>();
    }

    public void generateTree(Context context, Callback<Boolean> callback) {

        List<String> audios = getAllAudioFromDevice(context);
        Map<String, List<String>> tree = new HashMap<>();
        String[] pathParts;
        boolean absent;
        String name;

        for (String path : audios) {
            pathParts = path.split("/");
            for (int current = 0, previous = -1; current < pathParts.length; current++, previous++) {
                if (tree.get(pathParts[current]) == null) {
                    tree.put(pathParts[current], new ArrayList<String>());
                } else {
                    List<String> twegs = tree.get(pathParts[previous]);
                    absent = true;
                    for (String s : twegs) {
                        if (s.equals(pathParts[current])) absent = false;
                    }
                    if (!absent) {
                        twegs.add(pathParts[current]);
                        tree.put(pathParts[previous], twegs);
                    }
                }
            }
        }

        callback.onSuccess(true);
    }

    List<String> getAllAudioFromDevice(final Context context) {
        final List<String> audioList = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.AudioColumns.DATA};

        Cursor c = context.getContentResolver().query(uri, projection, MediaStore.Audio.Media.DATA + " like ? ", null, null);

        if (c != null) {
            while (c.moveToNext()) {
                audioList.add(c.getString(0));
            }
            c.close();
        }
        Collections.sort(audioList);
        return audioList;
    }
}
