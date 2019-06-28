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
 * The Class Client.
 */
public class EventManagerClient {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(EventManagerClient.class.getName());

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
			System.out.println("Entered as a EventManager ");
			System.out.println("Enter your 8 digit ID including your city name (Montreal, Ottawa and Toronto) ");

			user_ID = input.next();
			if (user_ID.length() == 8) {
				String first2 = user_ID.toUpperCase().substring(0, 4);

				if (first2.charAt(3) == 'M') {
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

		boolean matches_montreal = Pattern.matches(expression1, user_ID);
		boolean matches_ottawa = Pattern.matches(expression2, user_ID);
		boolean matches_toronto = Pattern.matches(expression3, user_ID);

		System.out.println("\n\n\nYou have entered as a Event Manager");

		logger.info("Entered as a Manager with ID" + user_ID);

		menuAgain2: while (true) {
			System.out.println("\n\n\n\nSelect an action ");
			System.out.println("1. Book a event for customer ");
			System.out.println("2. Get Booking Schedule of a customer ");
			System.out.println("3. Cancel an event for a customer ");
			System.out.println("4. Add an event ");
			System.out.println("5. Remove a event ");
			System.out.println("6. List event availabilities ");
			System.out.println("7. Show menu again ");

			int option = input.nextInt();
			if( option < 1 || option > 8 ) {
				continue menuAgain2;
			}
			
			switch (option) {
			case 1:
				input.nextLine();
				System.out.println("Enter customerID of the respective customer ");
				String customerID1 = input.nextLine();
				System.out.println("Enter event type for the event ");
				String eventType = input.nextLine();
				System.out.println("Enter eventID for the event ");
				String eventID = input.nextLine();

				boolean matches_montreal1 = Pattern.matches(expression1, customerID1);
				boolean matches_ottawa1 = Pattern.matches(expression2, customerID1);
				boolean matches_toronto1 = Pattern.matches(expression3, customerID1);

				if (matches_montreal1) {
					logger.info("Request to montreal book event ");
					logger.info("Parameters passed are customerID " + customerID1 + " eventID " + eventID
							+ " and eventtype" + eventType);
					String reply = montreal_obj.bookEvent(customerID1, eventID, eventType);
					System.out.println(reply);
					logger.info("Reply " + reply);

				} else if (matches_ottawa1) {
					logger.info("Request to ottawa book event ");
					logger.info("Parameters passed are customerID " + customerID1 + " eventID " + eventID
							+ " and eventtype" + eventType);
					String reply = ottawa_obj.bookEvent(customerID1, eventID, eventType);
					System.out.println(reply);
					logger.info("Reply " + reply);

				} else if (matches_toronto1) {
					logger.info("Request to toronto book event ");
					logger.info("Parameters passed are customerID " + customerID1 + " eventID " + eventID
							+ " and eventtype" + eventType);
					String reply = toronto_obj.bookEvent(customerID1, eventID, eventType);
					System.out.println(reply);
					logger.info("Reply " + reply);

				}
				break;

			case 2:

				input.nextLine();
				System.out.println("Enter customerID of the respective customer ");
				String customerID2 = input.nextLine();

				boolean matches_montreal2 = Pattern.matches(expression1, customerID2);
				boolean matches_ottawa2 = Pattern.matches(expression2, customerID2);
				boolean matches_toronto2 = Pattern.matches(expression3, customerID2);

				if (matches_montreal2) {
					logger.info("Request: Booking Schedule ");
					logger.info("Parameter: student ID: " + customerID2);
					ArrayList<String> list = montreal_obj.getBookingSchedule(customerID2);
					for (String details : list) {
						System.out.println(details);
					}
					logger.info("Reply:" + list.toString());

				} else if (matches_ottawa2) {
					logger.info("Request: Booking Schedule ");
					logger.info("Parameter: student ID: " + customerID2);
					ArrayList<String> list = ottawa_obj.getBookingSchedule(customerID2);
					for (String details : list) {
						System.out.println(details);
					}
					logger.info("Reply:" + list.toString());

				} else if (matches_toronto2) {
					logger.info("Request: Booking Schedule ");
					logger.info("Parameter: student ID: " + customerID2);
					ArrayList<String> list = toronto_obj.getBookingSchedule(customerID2);
					for (String details : list) {
						System.out.println(details);
					}
					logger.info("Reply:" + list.toString());

				}
				break;

			case 3:

				input.nextLine();
				System.out.println("Enter customerID of the respective customer ");
				String customerID3 = input.nextLine();
				System.out.println("Enter event Type for the event ");
				String eventType1 = input.nextLine();
				System.out.println("Enter eventID for the event ");
				String eventID1 = input.nextLine();

				boolean matches_montreal3 = Pattern.matches(expression1, customerID3);
				boolean matches_ottawa3 = Pattern.matches(expression2, customerID3);
				boolean matches_toronto3 = Pattern.matches(expression3, customerID3);

				if (matches_montreal3) {
					logger.info("Request: Remove event ");
					logger.info("Parameter: event ID:" + eventID1 + ", Student ID:" + customerID3);
					String reply = montreal_obj.cancelEvent(customerID3, eventID1, eventType1);
					System.out.println(reply);
					logger.info("Reply " + reply);

				} else if (matches_ottawa3) {
					logger.info("Request: Remove event ");
					logger.info("Parameter: event ID:" + eventID1 + ", Student ID:" + customerID3);
					String reply = ottawa_obj.cancelEvent(customerID3, eventID1, eventType1);
					System.out.println(reply);
					logger.info("Reply " + reply);

				} else if (matches_toronto3) {
					logger.info("Request: Remove event ");
					logger.info("Parameter: event ID:" + eventID1 + ", Student ID:" + customerID3);
					String reply = toronto_obj.cancelEvent(customerID3, eventID1, eventType1);
					System.out.println(reply);
					logger.info("Reply " + reply);

				}
				break;

			case 4:
				System.out.println("Please enter  the event Type");
				String eventType2 = input.next();
				System.out.println("Please enter the event ID");
				String eventID2 = input.next();

				// to check if the advisor can add for his department only
				boolean matches_montreal4 = Pattern.matches(expression1, eventID2);
				boolean matches_ottawa4 = Pattern.matches(expression2, eventID2);
				boolean matches_toronto4 = Pattern.matches(expression3, eventID2);
//				System.out.println("mtlm "+matches_montreal);
//				System.out.println("mtle "+matches_montreal4);
//				System.out.println("otwm "+matches_ottawa);
//				System.out.println("otwe "+matches_ottawa4);
//				System.out.println("torm "+matches_toronto);
//				System.out.println("tore "+matches_toronto4);

				if (matches_montreal && matches_montreal4) {
					logger.info("Request: Add event for client by manager ");
					System.out.println("Please enter event capacity");
					String eventcapacity = input.next();
					logger.info("Parameters :" + eventID2 + " " + eventType2 + " " + eventcapacity);
					String reply = montreal_obj.addEvent(eventID2, eventType2, eventcapacity);
					System.out.println(reply);
					logger.info("Reply " + reply);

				} else if (matches_ottawa && matches_ottawa4) {
					logger.info("Request: Add event for client by manager ");
					System.out.println("Please enter event capacity");
					String eventcapacity = input.next();
					logger.info("Parameters :" + eventID2 + " " + eventType2 + " " + eventcapacity);
					String reply = ottawa_obj.addEvent(eventID2, eventType2, eventcapacity);
					System.out.println(reply);
					logger.info("Reply " + reply);

				} else if (matches_toronto && matches_toronto4) {
					logger.info("Request: Add course for client by manager ");
					System.out.println("Please enter event capacity");
					String eventcapacity = input.next();
					logger.info("Parameters :" + eventID2 + " " + eventType2 + " " + eventcapacity);
					String reply = toronto_obj.addEvent(eventID2, eventType2, eventcapacity);
					System.out.println(reply);
					logger.info("Reply " + reply);

				} else {
					System.out.println("Sorry you cannot add the event for other cities");
					logger.info("Sorry you cannot add the event for other cities");
				}

				break;

			case 5:

				System.out.println("Please enter the event type.");
				String eventType3 = input.next();
				System.out.println("Please enter the event ID");
				String eventID3 = input.next();

				System.out.println(eventType3 + " " + eventID3);

				if (matches_montreal) {
					logger.info("Request: Add course from manager ");
					logger.info("Parameters :" + eventID3 + " " + eventType3);
					String reply = montreal_obj.removeEvent(eventID3, eventType3);
					System.out.println(reply);
					logger.info("Reply " + reply);
				} else if (matches_ottawa) {
					logger.info("Request: Add course from manager ");
					logger.info("Parameters :" + eventID3 + " " + eventType3);
					String reply = ottawa_obj.removeEvent(eventID3, eventType3);
					System.out.println(reply);
					logger.info("Reply " + reply);

				} else if (matches_toronto) {
					logger.info("Request: Add course from manager ");
					logger.info("Parameters :" + eventID3 + " " + eventType3);
					String reply = toronto_obj.removeEvent(eventID3, eventType3);
					System.out.println(reply);
					logger.info("Reply " + reply);
				}

				break;

			case 6:
				System.out.println("Please enter the event type.");
				String eventType4 = input.next();

				if (matches_montreal) {
					logger.info("Request: list eventavailability req to Montreal server ");
					logger.info("Parameter:" + eventType4);
					ArrayList<String> reply = new ArrayList<String>();
					reply = montreal_obj.listEventAvailability(eventType4);

					for (String str : reply) {
						System.out.println(str);
					}
					logger.info("Reply " + reply.toString());

				} else if (matches_ottawa) {
					logger.info("Request: list eventavailability req to Ottawa server ");
					logger.info("Parameter:" + eventType4);
					ArrayList<String> reply = new ArrayList<String>();
					reply = ottawa_obj.listEventAvailability(eventType4);
					
					for (String str : reply) {
						System.out.println(str);
					}
					logger.info("Reply " + reply.toString());

				} else if (matches_toronto) {
					logger.info("Request: list eventavailability req to Toronto server ");
					logger.info("Parameter:" + eventType4);
					ArrayList<String> reply = new ArrayList<String>();
					reply = toronto_obj.listEventAvailability(eventType4);
					
					for (String str : reply) {
						System.out.println(str);
					}
					logger.info("Reply " + reply.toString());
				}

				break;
			case 7:
				continue menuAgain2;
			default:
				System.out.println("Invalid input. Please enter correct choice ");
				logger.info("Invalid input. Please enter correct choice ");
			}
		}

	}
}
