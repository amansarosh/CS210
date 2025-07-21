package WeekOne;

public class PA1A {
    static void startup() {
// Startup procedure
        System.out.println("Start engine");
        System.out.println("Untie from buoy");
        System.out.println("Move into forward gear");
        System.out.println("Acclerate");
        System.out.println("Cruise North");
        System.out.println("Cruise Northeast");
        System.out.println("Accelerate");
    }
    static void safetycheck() {
// Safety check
        System.out.println("Check Starboard");
        System.out.println("Check Port");
        System.out.println("Check speed");
        System.out.println("Check fuel level");
        System.out.println("Check temperature level");
        System.out.println("Check for debris in water");
    }
    static void monitor() {
// Monitor boat traffic
        System.out.println("Check VHF channel 16");
        System.out.println("Check for navigational lights");
        System.out.println("Listen for fog horns");
    }
    static void shutdown() {
// Shutdown boat and moor
        System.out.println("Secure boat to buoy");
        System.out.println("Turn off motor");
        System.out.println("Turn off electrical");
        System.out.println("Lock boat motor");
    }
    public static void main(String[] args)
    {
        startup();
        safetycheck();
        System.out.println("Cruise Northeast");
        safetycheck();
        System.out.println("Cruise Northwest");
        monitor();
        System.out.println("Cruise Northwest");
        safetycheck();
        monitor();
        safetycheck();
        shutdown();
    }
}
