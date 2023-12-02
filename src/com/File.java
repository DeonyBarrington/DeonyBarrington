package com;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class File extends Node {
    private String type;

    // file cannot exist without all the details - parent folder cannot be null for file nodes
    public File(@NonNull String name, Folder parentFolder, @NonNull LocalDateTime doe, @NonNull String type) {
        super(name, parentFolder, doe);
        this.type = type;
    }

    public static void createFile(File f) {
        // add f to parent folder list - assumption is that files cannot exist without parent folder
        Folder parentFolder = f.getParentFolder();
        parentFolder.getChildren().add(f);
    }

    public static void deleteFile(File f) {
        // remove from parent folder list
        Folder parentFolder = f.getParentFolder();
        if (parentFolder != null) {
            parentFolder.getChildren().remove(f);
            f.setParentFolder(null);
        }
    }

    public static void moveFile(File f, Folder d) {
        if (d != null) { // check valid destination, do nothing
            System.out.println("Invalid destination folder");
            if (!f.getParentFolder().equals(d)) { // check if parents are the same, to avoid unnecessary processing
                // remove from the old folder
                Folder parentFolder = f.getParentFolder();
                parentFolder.getChildren().remove(f);

                // add to the list of children of the new parent, change the current parent
                f.setParentFolder(d);
                d.getChildren().add(f);
            } else {
                System.out.println("File already exists in the destination folder.");
            }
        }
    }
}
