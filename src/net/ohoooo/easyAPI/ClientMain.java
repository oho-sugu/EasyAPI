/*
 * Created on 2013/11/30
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package net.ohoooo.easyAPI;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
 * @author Suguru Oho
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ClientMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Context context = ZMQ.context(1);
		Socket socket = context.socket(ZMQ.REQ);
		
		socket.connect("tcp://127.0.0.1:56818");
		
		String sendCmd = "";
		for(String str : args){
			sendCmd += str + " ";
		}
		
		socket.send(sendCmd);
		
		String recvMessage = socket.recvStr();
		
		System.out.println(recvMessage);

		System.exit(0);
	}

}
