// Subsystem 1
class DVDPlayer {
    public void on() {
        System.out.println("DVD Player is ON");
    }
    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }
    public void off() {
        System.out.println("DVD Player is OFF");
    }
}

// Subsystem 2
class Projector {
    public void on() {
        System.out.println("Projector is ON");
    }
    public void setMode(String mode) {
        System.out.println("Projector mode set to: " + mode);
    }
    public void off() {
        System.out.println("Projector is OFF");
    }
}

// Subsystem 3
class SoundSystem {
    public void on() {
        System.out.println("Sound System is ON");
    }
    public void setVolume(int level) {
        System.out.println("Volume set to " + level);
    }
    public void off() {
        System.out.println("Sound System is OFF");
    }
}

// Facade
class HomeTheaterFacade {
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private SoundSystem soundSystem;

    public HomeTheaterFacade() {
        // Facade is responsible for creating subsystems
        this.dvdPlayer = new DVDPlayer();
        this.projector = new Projector();
        this.soundSystem = new SoundSystem();
    }

    public void watchMovie(String movie) {
        System.out.println("\nGet ready to watch a movie...");
        dvdPlayer.on();
        projector.on();
        projector.setMode("Cinema");
        soundSystem.on();
        soundSystem.setVolume(10);
        dvdPlayer.play(movie);
    }

    public void endMovie() {
        System.out.println("\nShutting down the home theater...");
        dvdPlayer.off();
        soundSystem.off();
        projector.off();
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        // Client only deals with the Facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();

        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();
    }
}


//FAcade can also another facade