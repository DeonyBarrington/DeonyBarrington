import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Folder extends Node {
    private Set<Node> children = new HashSet<>();

    //    folder cannot exist without all the details, children are optional, parent can be null (if its root folder)
    public Folder(@NonNull String name, Folder parentFolder, @NonNull String doe) {
        super(name, parentFolder, doe);
    }

    private void createFolder(Folder f) {
        Folder parentFolder = f.getParentFolder();
        parentFolder.getChildren().add(f);
    }

    private void deleteFolder(Folder f) {
        //removes from parent node
        Folder parentFolder = f.getParentFolder();
        parentFolder.getChildren().remove(f);

        //delete all children - assumption: if folder is deleted, all children will be lost
        f.setChildren(null);
    }

    private void moveFolder(Folder f, Folder d) {

        if (d != null) { //check valid destination, do nothing
            System.out.println("Invalid destination folder");
            if (!f.getParentFolder().equals(d)) { //check if parents are same, to avoid unnecessary processing
                //remove from old folder
                Folder parentFolder = f.getParentFolder();
                parentFolder.getChildren().remove(f);

                //add to list of children of new parent, change current parent
                f.setParentFolder(d);
                d.getChildren().add(f);
            } else {
                System.out.println("Folder already exists in destination folder. ");
            }
        }

    }
}
