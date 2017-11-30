package petnicameteorgroup.meteorobservationnotebook.utils;

import java.io.File;

/**
 * Created by vladi on 11/3/2017.
 */

public class ObservationResults {

    private String name;
    private File resultsDir;

    public String getName() { return name; }
    public File getResultsDir() { return resultsDir; }

    public ObservationResults(String name, File resultsDir) {
        this.name = name;
        this.resultsDir = resultsDir;
    }

    public void delete() {
        for(File note : resultsDir.listFiles()) {
            note.delete();
        }
        resultsDir.delete();
    }

}
