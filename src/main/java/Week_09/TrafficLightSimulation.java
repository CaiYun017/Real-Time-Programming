package Week_09;

import java.util.concurrent.locks.ReentrantLock;

public class TrafficLightSimulation {

    enum LightState {
        GREEN,
        YELLOW,
        RED
    }

    static class TrafficLightController {
        private final ReentrantLock lock;

        public TrafficLightController() {
            lock = new ReentrantLock(true);
        }

        public void controlTraffic(String direction) {
            while (true) {
                lock.lock();
                try {
                    System.out.println("[" + direction + "] light: GREEN");
                    Thread.sleep(3000);

                    System.out.println("[" + direction + "] light: YELLOW");
                    Thread.sleep(1000);

                    System.out.println("[" + direction + "] light: RED");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class DirectionLight extends Thread {
        private final TrafficLightController controller;
        private final String direction;

        public DirectionLight(TrafficLightController controller, String direction) {
            this.controller = controller;
            this.direction = direction;
        }

        @Override
        public void run() {
            controller.controlTraffic(direction);
        }
    }

    public static void main(String[] args) {
        TrafficLightController controller = new TrafficLightController();

        DirectionLight north = new DirectionLight(controller, "North");
        DirectionLight south = new DirectionLight(controller, "South");
        DirectionLight east = new DirectionLight(controller, "East");
        DirectionLight west = new DirectionLight(controller, "West");

        north.start();
        south.start();
        east.start();
        west.start();
    }
}
