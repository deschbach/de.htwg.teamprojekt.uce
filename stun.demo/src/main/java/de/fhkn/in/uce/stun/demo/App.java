package de.fhkn.in.uce.stun.demo;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import de.fhkn.in.uce.stun.attribute.Attribute;
import de.fhkn.in.uce.stun.attribute.AttributeDecoder;
import de.fhkn.in.uce.stun.attribute.OtherAddress;
import de.fhkn.in.uce.stun.attribute.XorMappedAddress;
import de.fhkn.in.uce.stun.header.STUNMessageClass;
import de.fhkn.in.uce.stun.header.STUNMessageMethod;
import de.fhkn.in.uce.stun.message.Message;
import de.fhkn.in.uce.stun.message.MessageReader;
import de.fhkn.in.uce.stun.message.MessageStaticFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final String STUN_PRIMARY_IP = "213.239.218.18";
    private static final String STUN_PRIMARY_PORT = "3478";
    
    
	
    public static void main( String[] args )
    {
    	System.out.println("Starting Stun Demo...");
    	
    	InetSocketAddress stunServerAddress = new InetSocketAddress("213.239.218.18", 3478);
    	
    	final MessageReader messageReader = MessageReader.createMessageReader();
    	
    	Socket toStunServer = new Socket();
    	
    	try {
    		
    		toStunServer.setReuseAddress(true);
        	toStunServer.bind(new InetSocketAddress(55554));
        	System.out.printf("Connecting to Stun address %s on local port %s\n", STUN_PRIMARY_IP, STUN_PRIMARY_PORT);
        	toStunServer.connect(stunServerAddress);
        	final Message bindingRequest = MessageStaticFactory.newSTUNMessageInstance(STUNMessageClass.REQUEST, STUNMessageMethod.BINDING);
            bindingRequest.writeTo(toStunServer.getOutputStream());
            final Message bindingResponse = messageReader.readSTUNMessage(toStunServer.getInputStream());
            
            Attribute attr = bindingResponse.getAttribute(XorMappedAddress.class);
            XorMappedAddress addr = (XorMappedAddress) attr;
            InetSocketAddress otherAddress;

            System.out.printf("Received Respones from Stun Server: %s\n",  addr.getEndpoint());
            
    	} catch (Exception ex) {
    		System.out.println(ex.getStackTrace());
    	}   	
    }
}
