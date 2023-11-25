import java.util.HashSet;
import java.util.Set;

public class Folder {
    Set<String> children = new HashSet<>();

    private void createFolder(Folder f){
        //set node level values
        //assumes root level folder exists already
    }
    private void deleteFolder(Folder f){
        //removes from parent node

    }
    private void moveFolder(Folder f, Folder d){
        //validation required
        //update parent node
    }
}
