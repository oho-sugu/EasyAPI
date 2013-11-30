/*
 * Created on 2013/11/30
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package net.ohoooo.easyAPI;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
 * @author Suguru Oho
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Context context = ZMQ.context(1);
		Socket socket = context.socket(ZMQ.REP);
		socket.bind("tcp://127.0.0.1:56818");
		File workDir = new File("./");
		String[] env = {};
		
		while(true){
			String recvStr = socket.recvStr();
			
			Process p = null;
			
			try {
				//p = Runtime.getRuntime().exec(parseCmdString(recvStr), env, workDir);
				p = Runtime.getRuntime().exec(recvStr);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				// TODO Print Error Log
				continue;
			}
			
			InputStream is = p.getInputStream();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			StringBuffer sb = new StringBuffer();
			
			try {
				String line;
				while((line = br.readLine())!=null){
					sb.append(line+"\n");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			socket.send(sb.toString());
		}
	}
	
	private static String[] parseCmdString(String cmd){
		StringTokenizer st = new StringTokenizer(cmd, " ");
		Vector<String> ret = new Vector<String>();
		while(st.hasMoreTokens()){
			ret.add(st.nextToken());
		}
		
		String[] retStr = new String[ret.size()];
		for(int i = 0; i< ret.size(); i++){
			retStr[i] = ret.get(i);
		}
		
		return retStr;
	}

}
