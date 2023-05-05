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

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClientManagerTest {
    private ClientManager clientManager;

    @BeforeEach
    public void setUp() {
        clientManager = new ClientManager();
    }

    @Test
    public void testAddBooking() {
        clientManager.addBooking("Jacey Espinosa", LocalDate.of(2023, 6, 1));
        assertEquals(LocalDate.of(2023, 6, 1), clientManager.getClientBookings().get("Jacey Espinosa"));
    }

    @Test
    public void testRemoveBooking() {
        clientManager.addBooking("Jacey Espinosa", LocalDate.of(2023, 6, 1));
        clientManager.removeBooking("Jacey Espinosa", LocalDate.of(2023, 6, 1));
        assertNull(clientManager.getClientBookings().get("Jacey Espinosa"));
    }

    @Test
    public void testGetNextBookingClientName() {
        clientManager.addBooking("Jacey E", LocalDate.of(2023, 6, 1));
        clientManager.addBooking("Jacey Espinosa", LocalDate.of(2023, 5, 15));
        assertEquals("Jacey Espinosa", clientManager.getNextBookingClientName());
    }

    @Test
    public void testGetNextBookingReservationDate() {
        clientManager.addBooking("Jacey E", LocalDate.of(2023, 6, 1));
        clientManager.addBooking("Jacey Espinosa", LocalDate.of(2023, 5, 15));
        assertEquals(LocalDate.of(2023, 5, 15), clientManager.getNextBookingReservationDate());
    }
}