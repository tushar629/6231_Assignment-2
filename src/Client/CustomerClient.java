package Client;

import java.io.IOException;
import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

import Montreal_Server.Montreal_Interface;
import Ottawa_Server.Ottawa_Interface;
import Toronto_Server.Toronto_Interface;

/**
 * The Class CustomerClient.
 */
public class CustomerClient {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(CustomerClient.class.getName());

	/** The fh. */
	public static FileHandler filehandle = null;

	/** The login info. */
	public static HashMap<String, String> login_info = new HashMap<String, String>();

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {

		Registry registry_montreal = LocateRegistry.getRegistry(6968);
		Registry registry_ottawa = LocateRegistry.getRegistry(8085);
		Registry registry_toronto = LocateRegistry.getRegistry(4200);

		Montreal_Interface montreal_obj = (Montreal_Interface) registry_montreal.lookup("MTL");
		Ottawa_Interface ottawa_obj = (Ottawa_Interface) registry_ottawa.lookup("OTW");
		Toronto_Interface toronto_obj = (Toronto_Interface) registry_toronto.lookup("TOR");

		Scanner input = new Scanner(System.in);

		String user_ID = null;
		boolean type = false; // If type is false then input user ID is incorrect

		do {
			System.out.println("\n\n\nEntered as a Client ");
			System.out.println("Enter your 8 digit ID including your city name (Montreal, Ottawa and Toronto) ");

			user_ID = input.next();
			if (user_ID.length() == 8) {
				String first2 = user_ID.toUpperCase().substring(0, 4);

				if (first2.charAt(3) == 'C') {
					type = true;
				}
			} else {
				System.out.println("Please Enter a valid ID ");
			}
		} while (!type);

		try {
			// This block configure the logger with handler and formatter
			filehandle = new FileHandler(
					Paths.get(".").toAbsolutePath().normalize().toString() + "\\log\\" + user_ID + ".log", true);
			logger.addHandler(filehandle);
			SimpleFormatter formatter = new SimpleFormatter();
			filehandle.setFormatter(formatter);

			logger.setUseParentHandlers(false);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("User id entered is " + user_ID);
		String expression1 = ".*MTL.*";
		String expression2 = ".*OTW.*";
		String expression3 = ".*TOR.*";

		System.out.println("You have entered as a client ");
		String customerID = user_ID;
		logger.info("entered as a client with ID" + customerID);

		boolean matches_montreal = Pattern.matches(expression1, user_ID);
		boolean matches_ottawa = Pattern.matches(expression2, user_ID);
		boolean matches_toronto = Pattern.matches(expression3, user_ID);

		menuAgain: while (true) {

			System.out.println("\n\n\n\nSelect an action ");
			System.out.println("1. Book a event ");
			System.out.println("2. Get your Booking Schedule ");
			System.out.println("3. Cancel an event ");
			System.out.println("4. Swap event");
			System.out.println("5. Show menu again ");

			int option = input.nextInt();
			
			if( option < 1 || option > 6 ) {
				continue menuAgain;
			}

			switch (option) {
			case 1:
				System.out.println("Enter event type for the event ");
				input.nextLine();
				String eventType = input.nextLine();
				System.out.println("Enter eventID for the event ");
				String eventID = input.nextLine();

				System.out.println(eventType + " 1 " + eventID);

				if (matches_montreal) {
					logger.info("Request to montreal book event ");
					logger.info("Parameters passed are customerID " + customerID + " eventID " + eventID
							+ " and eventtype" + eventType);
					String reply = montreal_obj.bookEvent(customerID, eventID, eventType);
					System.out.println(reply);
					logger.info("Reply " + reply);

				} else if (matches_ottawa) {
					logger.info("Request to ottawa book event ");
					logger.info("Parameters passed are customerID " + customerID + " eventID " + eventID
							+ " and eventtype" + eventType);
					String reply = ottawa_obj.bookEvent(customerID, eventID, eventType);
					System.out.println(reply);
					logger.info("Reply " + reply);

				} else if (matches_toronto) {
					logger.info("Request to ottawa book event ");
					logger.info("Parameters passed are customerID " + customerID + " eventID " + eventID
							+ " and eventtype" + eventType);
					String reply = toronto_obj.bookEvent(customerID, eventID, eventType);
					System.out.println(reply);
					logger.info("Reply " + reply);

				}
				break;
			case 2:

				if (matches_montreal) {
					logger.info("Request: Booking Schedule ");
					logger.info("Parameter: student ID: " + customerID);
					ArrayList<String> list = montreal_obj.getBookingSchedule(customerID);
					if (list.get(0).equalsIgnoreCase("The customerID entered is not in our records.")) {
						System.out.println("You have entered as a client ");
						user_ID = input.nextLine();
						customerID = user_ID;
						logger.info("entered as a client with ID" + customerID);
					} else {
						for (String details : list) {
							System.out.println(details);
						}
						logger.info("Reply:" + list.toString());
					}

				} else if (matches_ottawa) {
					logger.info("Request: Booking Schedule ");
					logger.info("Parameter: student ID: " + customerID);

					ArrayList<String> list = ottawa_obj.getBookingSchedule(customerID);
					if (list.get(0).equalsIgnoreCase("The customerID entered is not in our records.")) {
						System.out.println("You have entered as a client ");
						user_ID = input.nextLine();
						customerID = user_ID;
						logger.info("entered as a client with ID" + customerID);
					} else {
						for (String details : list) {
							System.out.println(details);
						}
						logger.info("Reply:" + list.toString());
					}

				} else if (matches_toronto) {
					logger.info("Request: Booking Schedule ");
					logger.info("Parameter: student ID: " + customerID);
					ArrayList<String> list = toronto_obj.getBookingSchedule(customerID);
					if (list.get(0).equalsIgnoreCase("The customerID entered is not in our records.")) {
						System.out.println("You have entered as a client ");
						user_ID = input.nextLine();
						customerID = user_ID;
						logger.info("entered as a client with ID" + customerID);
					} else {
						for (String details : list) {
							System.out.println(details);
						}
						logger.info("Reply:" + list.toString());
					}

				}
				break;

			case 3:
				System.out.println("Enter event Type for the event ");
				input.nextLine();
				String eventType1 = input.nextLine();
				System.out.println("Enter eventID for the event ");
				String eventID1 = input.nextLine();

				System.out.println(eventType1 + " 2 " + eventID1);

				if (matches_montreal) {
					logger.info("Request: Remove event ");
					logger.info("Parameter: event ID:" + eventID1 + ", Student ID:" + user_ID);
					String reply = montreal_obj.cancelEvent(customerID, eventID1, eventType1);
					System.out.println(reply);
					logger.info("Reply " + reply);

				} else if (matches_ottawa) {
					logger.info("Request: Remove event ");
					logger.info("Parameter: event ID:" + eventID1 + ", Student ID:" + user_ID);
					String reply = ottawa_obj.cancelEvent(customerID, eventID1, eventType1);
					System.out.println(reply);
					logger.info("Reply " + reply);

				} else if (matches_toronto) {
					logger.info("Request: Remove event ");
					logger.info("Parameter: event ID:" + eventID1 + ", Student ID:" + user_ID);
					String reply = toronto_obj.cancelEvent(customerID, eventID1, eventType1);
					System.out.println(reply);
					logger.info("Reply " + reply);

				}
				break;
				
			case 4:
				input.nextLine();
				System.out.println("Enter old eventType");
				String oldEventType = input.nextLine();
				System.out.println("Enter old eventID");
				String oldEventID = input.nextLine();
				System.out.println("Enter new eventType");
				String newEventType = input.nextLine();
				System.out.println("Enter new eventID");
				String newEventID = input.nextLine();
				
				if(matches_montreal) {
					String reply = montreal_obj.swapEvent(customerID, oldEventType, oldEventID, newEventType, newEventID);
					System.out.println(reply);
				} else if(matches_ottawa) {
					String reply = ottawa_obj.swapEvent(customerID, oldEventType, oldEventID, newEventType, newEventID);					
					System.out.println(reply);
				} else if(matches_toronto) {
					String reply = toronto_obj.swapEvent(customerID, oldEventType, oldEventID, newEventType, newEventID);
					System.out.println(reply);
				}
				
			case 5:
				continue menuAgain;
			default:
				System.out.println("Invalid input .Please enter the choice ");
				logger.info("Invalid input .Please enter the choice ");

			}
		}

	}
}
