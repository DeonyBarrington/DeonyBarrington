import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class File extends Node{
    private String type;

    //file cannot exist without all the details - parent folder cannot be null for file nodes
    public File(@NonNull String name, @NonNull Folder parentFolder, @NonNull String doe, @NonNull String type) {
        super(name, parentFolder, doe);
        this.type = type;
    }

    private void createFile(File f){
        //add f to parent folder list - assumption is that files cannot exist without parent folder
        Folder parentFolder = f.getParentFolder();
        parentFolder.getChildren().add(f);

    }

    private void deleteFile(File f){
        //remove from parent folder list
        Folder parentFolder = f.getParentFolder();
        parentFolder.getChildren().remove(f);


    }
    private void moveFile(File f, Folder d){

        if (d != null){ //check valid destination, do nothing
            System.out.println("Invalid destination folder");
            if (!f.getParentFolder().equals(d)){ //check if parents are same, to avoid unnecessary processing
                //remove from old folder
                Folder parentFolder =  f.getParentFolder();
                parentFolder.getChildren().remove(f);

                //add to list of children of new parent, change current parent
                f.setParentFolder(d);
                d.getChildren().add(f);
            }
            else { System.out.println("File already exists in destination folder. "); }

        }


    }

}
