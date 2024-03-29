package _ServerPackage;


/**
* _ServerPackage/_Common_IntefaceStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from E:/CommonDirectory/New/Assignment2/6231_Assignment-2/src/Common_Interface.idl
* Friday, June 28, 2019 5:53:20 PM EDT
*/

public class _Common_IntefaceStub extends org.omg.CORBA.portable.ObjectImpl implements _ServerPackage.Common_Inteface
{

  /* (non-Javadoc)
   * @see _ServerPackage.Common_IntefaceOperations#getBookingSchedule(java.lang.String)
   */
  public String getBookingSchedule (String customerID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getBookingSchedule", true);
                $out.write_string (customerID);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getBookingSchedule (customerID        );
            } finally {
                _releaseReply ($in);
            }
  } // getBookingSchedule

  /* (non-Javadoc)
   * @see _ServerPackage.Common_IntefaceOperations#bookEvent(java.lang.String, java.lang.String, java.lang.String)
   */
  public String bookEvent (String customerID, String eventID, String eventType)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("bookEvent", true);
                $out.write_string (customerID);
                $out.write_string (eventID);
                $out.write_string (eventType);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return bookEvent (customerID, eventID, eventType        );
            } finally {
                _releaseReply ($in);
            }
  } // bookEvent

  /* (non-Javadoc)
   * @see _ServerPackage.Common_IntefaceOperations#cancelEvent(java.lang.String, java.lang.String, java.lang.String)
   */
  public String cancelEvent (String customerID, String eventID, String eventType)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("cancelEvent", true);
                $out.write_string (customerID);
                $out.write_string (eventID);
                $out.write_string (eventType);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return cancelEvent (customerID, eventID, eventType        );
            } finally {
                _releaseReply ($in);
            }
  } // cancelEvent

  /* (non-Javadoc)
   * @see _ServerPackage.Common_IntefaceOperations#addEvent(java.lang.String, java.lang.String, java.lang.String)
   */
  public String addEvent (String eventID, String eventType, String bookingCapacity)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("addEvent", true);
                $out.write_string (eventID);
                $out.write_string (eventType);
                $out.write_string (bookingCapacity);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return addEvent (eventID, eventType, bookingCapacity        );
            } finally {
                _releaseReply ($in);
            }
  } // addEvent

  /* (non-Javadoc)
   * @see _ServerPackage.Common_IntefaceOperations#removeEvent(java.lang.String, java.lang.String)
   */
  public String removeEvent (String eventID, String eventType)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("removeEvent", true);
                $out.write_string (eventID);
                $out.write_string (eventType);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return removeEvent (eventID, eventType        );
            } finally {
                _releaseReply ($in);
            }
  } // removeEvent

  /* (non-Javadoc)
   * @see _ServerPackage.Common_IntefaceOperations#listEventAvailability(java.lang.String)
   */
  public String listEventAvailability (String eventType)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("listEventAvailability", true);
                $out.write_string (eventType);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return listEventAvailability (eventType        );
            } finally {
                _releaseReply ($in);
            }
  } // listEventAvailability

  /* (non-Javadoc)
   * @see _ServerPackage.Common_IntefaceOperations#swapEvent(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
   */
  public String swapEvent (String customerID, String oldEventType, String oldEventID, String newEventType, String newEventID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("swapEvent", true);
                $out.write_string (customerID);
                $out.write_string (oldEventType);
                $out.write_string (oldEventID);
                $out.write_string (newEventType);
                $out.write_string (newEventID);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return swapEvent (customerID, oldEventType, oldEventID, newEventType, newEventID        );
            } finally {
                _releaseReply ($in);
            }
  } // swapEvent

  /** The ids. */
  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:ServerPackage/Common_Inteface:1.0"};

  /* (non-Javadoc)
   * @see org.omg.CORBA.portable.ObjectImpl#_ids()
   */
  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  /**
	 * Read object.
	 *
	 * @param s the s
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  /**
	 * Write object.
	 *
	 * @param s the s
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _Common_IntefaceStub
