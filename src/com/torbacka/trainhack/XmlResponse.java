package com.torbacka.trainhack;

import java.time.LocalDateTime;

import com.mantono.webserver.Header;
import com.mantono.webserver.rest.HeaderField;
import com.mantono.webserver.rest.Response;
import com.mantono.webserver.rest.ResponseCode;

public class XmlResponse implements Response {
	
	private final ResponseCode code;
	private final Header header;
	private final String body;
	
	public XmlResponse()
	{
		this.code = ResponseCode.NOT_FOUND;
		this.body = "";
		this.header = setDefaultHeader();
	}
	
	public XmlResponse(final ResponseCode code)
	{
		this.code = code;
		this.body = "";
		this.header = setDefaultHeader();
	}
	
	public XmlResponse(final String body)
	{
		this.code = ResponseCode.OK;
		this.body = body;
		this.header = setDefaultHeader();
	}
	
	public XmlResponse(final ResponseCode code, final String body)
	{
		this.code = code;
		this.body = body;
		this.header = setDefaultHeader();
	}

	private Header setDefaultHeader()
	{
		Header header = new Header();
		
		header.set(HeaderField.DATE, LocalDateTime.now().toString());
		header.set(HeaderField.SERVER, "REST-in-Peace");
		header.set(HeaderField.CONTENT_LENGTH, "" + (body.length()+16));
		header.set(HeaderField.CONTENT_TYPE, "application/xml; charset=utf-8");
		
		return header;
	}

	@Override
	public ResponseCode getResponseCode()
	{
		return code;
	}

	@Override
	public Header getHeader()
	{
		return header;
	}

	@Override
	public CharSequence getBody()
	{
		return body;
	}


}
