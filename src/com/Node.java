package com;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class Node {
    //Node cannot exist without following details
    @NonNull
    private String name;

    private Folder parentFolder;
    @NonNull
    private LocalDateTime doe;

}
