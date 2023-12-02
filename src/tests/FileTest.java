package tests;

import com.File;
import com.Folder;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class FileTest {


    @Test
    public void test_createFile_setsParent() {
        // given
        Folder parentFolder = new Folder("FolderName", null, LocalDateTime.now());
        File newFile = new File("FileName", parentFolder, LocalDateTime.now(), ".exe");

        // when
        File.createFile(newFile);

        // then
        assertEquals(parentFolder, newFile.getParentFolder());
        assertTrue(parentFolder.getChildren().contains(newFile));
    }


    @Test
    public void test_deleteFile_removesFromParent() {
        // given
        Folder parentFolder = new Folder("FolderName", null, LocalDateTime.now());
        File newFile = new File("FileName", parentFolder, LocalDateTime.now(), ".exe");
        File.createFile(newFile);

        // when
        File.deleteFile(newFile);

        // then
        assertNull(newFile.getParentFolder());
        assertFalse(parentFolder.getChildren().contains(newFile));
    }

    @Test
    public void test_moveFileToAnotherFolder() {
        // given
        Folder sourceFolder = new Folder("SourceFolder", null, LocalDateTime.now());
        Folder destinationFolder = new Folder("DestinationFolder", null, LocalDateTime.now());
        File testFile = new File("TestFile", sourceFolder, LocalDateTime.now(), ".pdf");

        // when
        File.moveFile(testFile, destinationFolder);

        // then
        assertEquals(destinationFolder, testFile.getParentFolder()); //parent should update
        assertTrue(destinationFolder.getChildren().contains(testFile)); //file should exist in new parents' children list
        assertFalse(sourceFolder.getChildren().contains(testFile)); //should not be in previous children list
    }

    @Test
    public void test_createFolder_setsParent() {
        // given
        Folder parentFolder = new Folder("ParentFolder", null, LocalDateTime.now());
        Folder newFolder = new Folder("NewFolder", parentFolder, LocalDateTime.now());

        // when
        Folder.createFolder(newFolder);

        // then
        assertEquals(parentFolder, newFolder.getParentFolder());
        assertTrue(parentFolder.getChildren().contains(newFolder));
    }

    @Test
    public void test_deleteFolder_removesFromParentAndDeletesChildren() {
        // given
        Folder parentFolder = new Folder("ParentFolder", null, LocalDateTime.now());
        Folder childFolder = new Folder("ChildFolder", parentFolder, LocalDateTime.now());
        Folder.createFolder(childFolder);

        // when
        Folder.deleteFolder(childFolder);

        // then
        assertNull(childFolder.getParentFolder());
        assertFalse(parentFolder.getChildren().contains(childFolder));
    }

    @Test
    public void test_moveFolderToAnotherFolder() {
        // given
        Folder sourceFolder = new Folder("SourceFolder", null, LocalDateTime.now());
        Folder destinationFolder = new Folder("DestinationFolder", null, LocalDateTime.now());
        Folder testFolder = new Folder("TestFolder", sourceFolder, LocalDateTime.now());

        // when
        Folder.moveFolder(testFolder, destinationFolder);

        // then
        assertEquals(destinationFolder, testFolder.getParentFolder());
        assertTrue(destinationFolder.getChildren().contains(testFolder));
        assertFalse(sourceFolder.getChildren().contains(testFolder));
    }

    @Test
    public void test_createFile_inRootFolder() {
        // given
        File newFile = new File("RootFile", null, LocalDateTime.now(), ".txt");

        // when
        File.createFile(newFile);

        // then
        assertNull(newFile.getParentFolder());
    }

    @Test
    public void test_moveFolderToSameFolder_doesNothing() {
        // given
        Folder sourceFolder = new Folder("SourceFolder", null, LocalDateTime.now());
        Folder testFolder = new Folder("TestFolder", sourceFolder, LocalDateTime.now());

        // when
        Folder.moveFolder(testFolder, sourceFolder);

        // then
        assertEquals(sourceFolder, testFolder.getParentFolder());
        assertTrue(sourceFolder.getChildren().contains(testFolder));
    }

    @Test
    public void test_deleteFolder_withChildren_removesFromParentAndDeletesChildren() {
        // given
        Folder parentFolder = new Folder("ParentFolder", null, LocalDateTime.now());
        Folder childFolder = new Folder("ChildFolder", parentFolder, LocalDateTime.now());
        Folder.createFolder(childFolder);

        // when
        Folder.deleteFolder(parentFolder);

        // then
        assertNull(parentFolder.getParentFolder());
        assertFalse(parentFolder.getChildren().contains(childFolder));
        assertNull(childFolder.getParentFolder());
    }






}