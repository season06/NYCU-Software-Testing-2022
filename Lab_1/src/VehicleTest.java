import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private static Vehicle vehicle;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        vehicle = new Vehicle();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        vehicle.finalize();
    }

    @org.junit.jupiter.api.Test
    void testFinalize() {
        vehicle.finalize();
        assertEquals(0, vehicle.totalVehicle());

        vehicle = new Vehicle();
    }

    @org.junit.jupiter.api.Test
    void setSpeed() {
        vehicle.setSpeed(5);
        assertEquals(5, vehicle.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void setDir() {
        vehicle.setDir("south");
        assertEquals("south", vehicle.getDir());
    }

    @org.junit.jupiter.api.Test
    void getSpeed() {
        assertEquals(0, vehicle.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void getDir() {
        assertEquals("north", vehicle.getDir());
    }

    @org.junit.jupiter.api.Test
    void totalVehicle() {
        this.vehicle = vehicle;
        assertEquals(1, vehicle.totalVehicle());
    }
}