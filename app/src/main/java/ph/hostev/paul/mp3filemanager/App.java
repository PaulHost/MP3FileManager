package ph.hostev.paul.mp3filemanager;

import android.app.Application;

public class App extends Application {

    StructureGenerator structureGenerator = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public StructureGenerator getStructureGenerator() {
        if (structureGenerator == null)
            structureGenerator = new StructureGenerator();
        return structureGenerator;
    }
}
