package Montreal_Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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

		Runnable taskUDP = () -> {

			try {
				serverReceive();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		};

		new Thread(taskUDP).start();

		Montreal_Class stub1 = new Montreal_Class();
		Registry registry_montreal = LocateRegistry.createRegistry(6968);
		registry_montreal.rebind("MTL", stub1);

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

	}

}
