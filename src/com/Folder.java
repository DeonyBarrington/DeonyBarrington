package com;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Folder extends Node {
    private Set<Node> children = new HashSet<>();

    // folder cannot exist without all the details, children are optional, the parent can be null (if it's the root folder)
    public Folder(@NonNull String name, Folder parentFolder, @NonNull LocalDateTime doe) {
        super(name, parentFolder, doe);
    }

    public static void createFolder(Folder f) {
        Folder parentFolder = f.getParentFolder();
        if (parentFolder != null) {
            parentFolder.getChildren().add(f);
        }
    }

    public static void deleteFolder(Folder f) {
        // removes from the parent node
        Folder parentFolder = f.getParentFolder();
        if (parentFolder != null) {
            parentFolder.getChildren().remove(f);

            // delete all children - assumption: if the folder is deleted, all children will be lost
            f.setChildren(null);
        }
    }

    public static void moveFolder(Folder f, Folder d) {
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
                System.out.println("Folder already exists in the destination folder.");
            }
        }
    }
}
