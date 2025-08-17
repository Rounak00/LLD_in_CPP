//Composite design pattern 

// Composite Design Pattern - File System Example

import java.util.ArrayList;
import java.util.List;

// Component
interface FileSystemComponent {
    void showDetails();
}

// Leaf - File
class File implements FileSystemComponent {
    private String name;
    private int size; // in KB

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name + " (" + size + "KB)");
    }
}

// Composite - Directory
class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : children) {
            component.showDetails();
        }
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        // Creating files
        File file1 = new File("resume.pdf", 120);
        File file2 = new File("photo.jpg", 250);
        File file3 = new File("song.mp3", 5000);

        // Creating directories
        Directory documents = new Directory("Documents");
        Directory music = new Directory("Music");
        Directory pictures = new Directory("Pictures");

        // Adding files to directories
        documents.add(file1);
        pictures.add(file2);
        music.add(file3);

        // Creating root directory
        Directory root = new Directory("Root");
        root.add(documents);
        root.add(pictures);
        root.add(music);

        // Show full hierarchy
        root.showDetails();
    }
}
