import java.util.*;

// Observable interface
interface WeatherStationI {
    void add(ObserverI obj);
    void remove(ObserverI obj);
    void notifyObservers();
    void setData(int i);
    int getData();
}

// Observer interface
interface ObserverI {
    void update();
}

// Concrete Observable
class WorkStation implements WeatherStationI {
    protected List<ObserverI> observers = new ArrayList<>();
    protected int data = 0;

    @Override
    public void add(ObserverI obj) {
        observers.add(obj);
    }

    @Override
    public void remove(ObserverI obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyObservers() {
        for (ObserverI obs : observers) {
            obs.update();
        }
    }

    @Override
    public void setData(int d) {
        this.data = d;
        notifyObservers();
    }

    @Override
    public int getData() {
        return data;
    }
}

// Concrete Observer: Mobile
class Mobile implements ObserverI {
    private WeatherStationI station;
    private String id;

    public Mobile(WeatherStationI station, String id) {
        this.station = station;
        this.id = id;
    }

    @Override
    public void update() {
        System.out.println("Mobile [" + id + "] received update: Weather Data = " + station.getData());
    }
}

// Concrete Observer: TV
class Tv implements ObserverI {
    private WeatherStationI station;
    private String id;

    public Tv(WeatherStationI station, String id) {
        this.station = station;
        this.id = id;
    }

    @Override
    public void update() {
        System.out.println("TV [" + id + "] received update: Weather Data = " + station.getData());
    }
}

// Main class to test
public class Main {
    public static void main(String[] args) {
        // Create observable
        WorkStation station = new WorkStation();

        // Create observers
        Mobile mobile1 = new Mobile(station, "M1");
        Mobile mobile2 = new Mobile(station, "M2");
        Tv tv1 = new Tv(station, "T1");

        // Attach observers to station
        station.add(mobile1);
        station.add(mobile2);
        station.add(tv1);

        // Update weather data
        System.out.println("Setting data to 25...");
        station.setData(25);

        System.out.println("Setting data to 30...");
        station.setData(30);

        // Remove one observer
        station.remove(mobile2);

        System.out.println("Setting data to 35...");
        station.setData(35);
    }
}
