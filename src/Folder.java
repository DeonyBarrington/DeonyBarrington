import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Folder extends Node {
    Set<Node> children = new HashSet<>();

//    folder cannot exist without all the details, children are optional, parent can be null (if its root folder)
    public Folder(@NonNull String name, Folder parentFolder, @NonNull String doe) {
        super(name, parentFolder, doe);
    }

    private void createFolder(Folder f){
        Folder parentFolder = f.getParentFolder();
        parentFolder.getChildren().add(f);
    }

    private void deleteFolder(Folder f){
        //removes from parent node
        Folder parentFolder = f.getParentFolder();
        parentFolder.getChildren().remove(f);

        //delete all children
        f.getChildren();
    }

    private void moveFolder(Folder f, Folder d){
        //validation required
        //update parent node
    }
}
