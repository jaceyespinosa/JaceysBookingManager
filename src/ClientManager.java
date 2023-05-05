/***************************************************************
 * Name : JaceysClientManager
 * Author: Jacey Espinosa
 * Created : 05/04/2023
 * Course: CIS 152 - Data Structure
 * Version: 1.0
 * OS: Windows 10
 * IDE: Eclipse 2022-03 (4.23.0)
 * Copyright : This is my own original work
 * based on specifications issued by our instructor
 * Description : An app that constructs two DFS/BFS methods.
 *
 Input: Final project data
 *
 Output: Jaceys Client Manager
 * Academic Honesty: I attest that this is my original work.
 * I have not used unauthorized source code, either modified or
 * unmodified. I have not given other fellow student(s) access
 * to my program.
 ***************************************************************
 */
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ClientManager {
    // Use HashMap to store each client and their scheduled reservation date
    private Map<String, LocalDate> clientBookings;
    // Use a heap (PriorityQueue) to organize appointments by the nearest date
    private PriorityQueue<LocalDate> sortedBookings;

    public ClientManager() {
        clientBookings = new HashMap<>();
        sortedBookings = new PriorityQueue<>();
    }

    // Add a booking with the client's name as a key and reservation date as a value
    public void addBooking(String clientName, LocalDate reservationDate) {
        clientBookings.put(clientName, reservationDate);
        sortedBookings.add(selectionSortSoonestDate(reservationDate));
    }

    public Map<String, LocalDate> getClientBookings() {
        return clientBookings;
    }

    // Get the client's name for the next upcoming booking
    public String getNextBookingClientName() {
        LocalDate soonestDate = sortedBookings.peek();
        return getClientNameByDate(soonestDate);
    }

    // Get the reservation date of the next upcoming booking
    public LocalDate getNextBookingReservationDate() {
        return sortedBookings.peek();
    }

    // Use selection sort to find the reservation date that is the soonest
    private LocalDate selectionSortSoonestDate(LocalDate newDate) {
        LocalDate minDate = newDate;
        for (LocalDate date : clientBookings.values()) {
            if (date.isBefore(minDate)) {
                minDate = date;
            }
        }
        return minDate;
    }

    // Helper method to get the client's name by a specific date
    private String getClientNameByDate(LocalDate date) {
        for (Map.Entry<String, LocalDate> entry : clientBookings.entrySet()) {
            if (entry.getValue().equals(date)) {
                return entry.getKey();
            }
        }
        return null;
    }

    // Remove the next booking
//    public void removeBooking() {
//        LocalDate soonestDate = sortedBookings.poll();
//        String clientName = getClientNameByDate(soonestDate);
//        if (clientName != null) {
//            clientBookings.remove(clientName);
//        }
//    }

    // Remove the booking
    public void removeBooking(String clientName, LocalDate reservationDate) {
        if (clientBookings.containsKey(clientName) && clientBookings.get(clientName).equals(reservationDate)) {
            clientBookings.remove(clientName);
            sortedBookings.remove(reservationDate);
        }
    }
}