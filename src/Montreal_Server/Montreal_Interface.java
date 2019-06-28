package Montreal_Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.ArrayList;

/**
 * The Interface Montreal_Interface.
 */
public interface Montreal_Interface extends Remote {

	/**
	 * Gets the booking schedule corresponding to the custumerID.
	 *
	 * @param customerID the customer ID
	 * @return ArrayList the booking schedule
	 * @throws RemoteException The Remote Exception
	 */
	public ArrayList<String> getBookingSchedule(String customerID) throws RemoteException;

	/**
	 * Book event for the customer with given eventID and eventType.
	 *
	 * @param customerID the customer ID
	 * @param eventID    the event ID
	 * @param eventType  the event type
	 * @return the string confirmation of event booking if done
	 * @throws RemoteException the remote exception
	 */
	public String bookEvent(String customerID, String eventID, String eventType) throws RemoteException;

	/**
	 * Cancel event booking of customer.
	 *
	 * @param customerID the customer ID
	 * @param eventID    the event ID
	 * @param eventType  event ID
	 * @return the string
	 * @throws RemoteException the remote exception
	 */
	public String cancelEvent(String customerID, String eventID, String eventType) throws RemoteException;

	/**
	 * Adds a new event in a city. Done by manager only.
	 * 
	 * @param eventID         the event ID
	 * @param eventType       the event type
	 * @param bookingCapacity the booking capacity
	 * @return the string
	 * @throws RemoteException the remote exception
	 */
	public String addEvent(String eventID, String eventType, String bookingCapacity) throws RemoteException;

	/**
	 * Removes the event from the city. Done by manager only.
	 * 
	 * @param eventID   the event ID
	 * @param eventType the event type
	 * @return the string
	 * @throws RemoteException the remote exception
	 */
	public String removeEvent(String eventID, String eventType) throws RemoteException;

	/**
	 * List event availability for the respective eventID and eventType. Done by
	 * manager only.
	 *
	 * @param eventType the event type
	 * @return the array list
	 * @throws RemoteException the remote exception
	 */
	public ArrayList<String> listEventAvailability(String eventType) throws RemoteException;

	public String swapEvent(String customerID, String oldEventType, String oldEventID, String newEventType, String newEventID) throws RemoteException;

}
