package ph.hostev.paul.mp3filemanager.file_manager;

import java.util.ArrayList;
import java.util.List;

import ph.hostev.paul.mp3filemanager.Callback;
import ph.hostev.paul.mp3filemanager.models.AudioModel;
import ph.hostev.paul.mp3filemanager.models.Twigs;

public class StructureGenerator {

    private List<Twigs> tree = null;
    private StructureGenerator creator = null;

    public StructureGenerator() {
        this.tree = new ArrayList<>();
    }

    public List<Twigs> getTree() {
        return tree;
    }

    public void generateTree(List<AudioModel> audios, Callback<Boolean> callback) {

        List<Twigs> tree = new ArrayList<>();
        String[] path;
        Twigs twigs;
        String name;

        for (AudioModel audio : audios) {
            path = audio.getPath().split("/");
            for (int current = 0, previous = -1; current < path.length; current++, previous++) {
                if (tree.get(current) == null) {
                    Twigs twig = new Twigs();
                    twig.setContent(new ArrayList<String>());
                    twig.setName(path[current]);
                    tree.add(twig);
                } else {
                    for (String s : tree.get(previous).getContent())
                        if (!s.equals(path[current]))
                            tree.get(previous).getContent().add(path[current]);
                }
            }
        }

        for (int i = 0; i < tree.size(); i++) {
            twigs = new Twigs();
            name = "";
            if (tree.get(i).getContent().get(0) == null) {
                name += "/" + tree.get(i).getName();
            } else {
                twigs.setName(name);
                twigs.setContent(tree.get(i).getContent());
                this.tree.add(twigs);

            }
        }

        callback.onSuccess(true);

    }

}
