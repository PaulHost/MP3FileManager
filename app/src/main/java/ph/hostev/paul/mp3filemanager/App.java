package ph.hostev.paul.mp3filemanager;

import android.app.Application;

import ph.hostev.paul.mp3filemanager.file_manager.StructureGenerator;

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
