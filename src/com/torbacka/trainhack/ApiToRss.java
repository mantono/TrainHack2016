package com.torbacka.trainhack;

import java.io.IOException;

import com.mantono.webserver.SocketListener;

public class ApiToRss
{
	public static void main(String[] args) throws ClassNotFoundException, IOException
	{
		new SocketListener(1234).listen();
	}

}
