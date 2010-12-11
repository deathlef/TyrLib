package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Raw;
import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class AnonymousVicinityMessagePacket extends BaseServerPacket {

	public static final int TYPE = 35;
	public static final String NAME = "MSG_ANONVICINITY";
	
	private Text text;
	private Text message;
	private Raw raw;

	public AnonymousVicinityMessagePacket(DataInputStream input) throws IOException {
		this.text = new Text(input);
		this.message = new Text(input);
		this.raw = new Raw(input);
	}
	
	public AnonymousVicinityMessagePacket(String text, String message, String raw) {
		this.text = new Text(text);
		this.message = new Text(message);
		this.raw = new Raw(raw);
	}
	
	public String getText() {
		return this.text.getStringData();
	}
	
	public String getMessage() {
		return this.message.getStringData();
	}
	
	public String getRaw() {
		return this.raw.getStringData();
	}
	
	public int getPacketType() {
		return AnonymousVicinityMessagePacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		
		return getBytes(text, message, raw);
	}
	
	public String toString() {
		
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tText: '").append(this.text.getStringData()).append("'")
			.append("\n\tMessage: '").append(this.message.getStringData()).append("'")
			.append("\n\tRaw: ").append(this.raw.getStringData())
			.toString();
	
		return output;

	}
}
