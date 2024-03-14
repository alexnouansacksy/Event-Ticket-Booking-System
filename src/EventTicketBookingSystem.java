import java.util.*;
import static java.lang.System.out;

public class EventTicketBookingSystem {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int result = 0;

        boolean[][] availableSeats = new boolean[6][6];
        ArrayList<int[]> seats = new ArrayList<>();

        for (int i = 0; i < availableSeats.length; i++)
            for (int j = 0; j < availableSeats[0].length; j++) {
                int min = 1;
                int max = 2;
                Random rand = new Random();

                int random = rand.nextInt(max - min + 1) + min;
                if (random == 1) availableSeats[i][j] = true;
                if (random == 2) availableSeats[i][j] = false;
            }

        while (result != 5) {
            print();
            result = keyboard.nextInt();
            doStuff(result, availableSeats, seats);
        }
    }

    public static void print() {
        out.println("Event Ticket Booking System");
        out.println("1) View Reservations");
        out.println("2) View Available Tickets");
        out.println("3) Reserve Tickets");
        out.println("4) Remove Ticket Reservations");
        out.println("5) Exit");
        out.println();
        out.print("Enter your choice: ");
    }

    public static void doStuff(int n, boolean[][] list, ArrayList<int[]> reservations) {
        if (n == 1) {
            if (reservations.isEmpty()) out.println("No current reservations.\n");
            else {
                for (int i = 0; i < reservations.size(); i ++) {
                    out.println(i+1 + ") You have a reservation in Row " + reservations.get(i)[0] + " Column " + reservations.get(i)[1] + ".");
                }
            }
        }

        if (n == 2) {
            out.println("\n    Event Seating");
            for (int i = 0; i < 5; i++) {
                out.print(i + 1 + " ");
                for (int j = 0; j < 5; j++) {
                    if (list[i][j]) out.print("[ ]");
                    else out.print("[x]");
                    out.print(" ");
                }
                out.println();
            }
            out.println(" " + "  1 " + "  2 " + "  3 "  + "  4 " + "  5 \n");
            return;
        }

        if (n == 3) {
            doStuff(2, list, reservations);

            Scanner keyboard = new Scanner(System.in);

            out.print("What row? ");
            String rowString = keyboard.next();
            out.print("What column? ");
            String colString = keyboard.next();

            try {
                int row = Integer.parseInt(rowString);
                try {
                    int col = Integer.parseInt(colString);
                    if ((row > 5 || row < 1) && (col > 5 || col < 1 )) out.println("Invalid Row Number and Invalid Column Number");
                    else if (row > 5 || row < 1) out.println("Invalid Row Number.") ;
                    else if (col > 5 || col < 1) out.println("Invalid Column Number.");

                    else {
                        if (list[row - 1][col - 1]){
                            list[row - 1][col - 1] = false;
                            out.println("Seat in row " + row + " and column " + col + " has been reserved.");
                            int[] temp = {row, col};
                            reservations.add(temp);
                        }
                        else out.println("Seat unavailable.");
                    }
                    out.println();

                }catch(NumberFormatException e) {
                    out.println("Invalid Column Number");
                    out.println();
                }

            }catch(NumberFormatException e) {
                out.println("Invalid Row Number");
                out.println();
            }


            return;
        }

        if (n == 4) {
            Scanner keyboard = new Scanner(System.in);
            doStuff(1, list, reservations);
            out.println();
            out.print("Which one of your reservations would you like to remove?");
            int remove = keyboard.nextInt();
            if (reservations.size() < remove + 1) {
                out.println("Removed a reservation in Row " + reservations.get(remove-1)[0] + " Column " + reservations.get(remove-1)[1] + ".");
                reservations.remove(remove-1);
            }
            else out.println("Not a valid reservation number.\n");
            out.println();
        }

    }
}