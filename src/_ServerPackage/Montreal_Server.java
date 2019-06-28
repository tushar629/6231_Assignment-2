package _ServerPackage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;


/**
 * The Class Montreal_Server.
 */
public class Montreal_Server {

	/** The logger. */
	public static Logger logger = Logger.getLogger("MontrealServer");

	/** The filehandler. */
	public static FileHandler filehandler = null;

	/**
	 * The main method.
	 *
	 * @throws Exception the exception
	 */

	public static void serverReceive() throws Exception {

		DatagramSocket socket = null;
		try {
			byte[] data = new byte[1024];
			Montreal_Class act = new Montreal_Class();
			socket = new DatagramSocket(6000);

			while (true) {
				DatagramPacket incoming = new DatagramPacket(data, data.length);
				socket.receive(incoming);

				String incoming_message = new String(incoming.getData(), 0, incoming.getLength());

				String[] separate = incoming_message.split("\\s");

				if (separate.length == 2) {
					act.remote_removeEvent(incoming_message, incoming);
				} else if (separate.length == 1) {
					act.remote_list(incoming_message, incoming);
				} else if (separate[3].equalsIgnoreCase("check")) {
					act.updateBooking(incoming_message, incoming);
				} else if (separate[3].equalsIgnoreCase("cancel")) {
					act.cancelBooking(incoming_message, incoming);
				}
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				socket.close();
			}
		}

		// System.out.println("Server > "+ new String(packet.getData()));

	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String args[]) throws Exception {

		Montreal_Class stub1 = new Montreal_Class();

		System.out.println(" Montreal server has been started");
		try {
			// This block configure the logger with handler and formatter
			filehandler = new FileHandler(
					Paths.get(".").toAbsolutePath().normalize().toString() + "\\log\\MontrealServer.log", true);

			logger.addHandler(filehandler);
			SimpleFormatter formatter = new SimpleFormatter();
			filehandler.setFormatter(formatter);

			// to remove the console handler *******
			logger.setUseParentHandlers(false);
			// the following statement is used to log any messages
			logger.info("Inside the Montreal Server");

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		Runnable taskUDP = () -> {

			try {
				serverReceive();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		};

		new Thread(taskUDP).start();

		System.out.println("Application Terminating ...");
		// properties value to help the ORB
		Properties props = new Properties();
		props.put("org.omg.CORBA.ORBInitialPort", "1050");
		props.put("org.omg.CORBA.ORBInitialHost", "localhost");
		/// ORB orb2 = ORB.init(args, props);

		// create and initialize the ORB
		ORB orb = ORB.init(args, props);
		// ORB orb = ORB.init(args, null);
		// get reference to rootpoa & activate the POAManager
		POA rootpoa = (POA) orb.resolve_initial_references("RootPOA");
		rootpoa.the_POAManager().activate();
		// create servant and register it with the ORB

		stub1.setORB(orb);
		// get object reference from the servant
		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(stub1);
		// and cast the reference to a CORBA reference
		Common_Inteface href = Common_IntefaceHelper.narrow(ref);

		// get the root naming context
		// NameService invokes the transient name service
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		// Use NamingContextExt, which is part of the
		// Interoperable Naming Service (INS) specification.
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		// bind the Object Reference in Naming
		String name = "MTL";
		NameComponent path[] = ncRef.to_name(name);
		ncRef.rebind(path, href);
		System.out.println("Montreal Serevr ready and listening ...  ...");
		// wait for invocations from clients
		orb.run();


	}

}
