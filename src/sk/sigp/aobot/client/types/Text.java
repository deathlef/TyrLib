/**
 *  Copyright 2002 Quaseem
 *  Copyright 2004 Migisan
 *  Copyright 2008 Oest
 *
 *  This file is part of the aochat package.
 *
 *  The aochat package is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  The aochat package is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with the aochat package; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package sk.sigp.aobot.client.types;

import java.io.DataInputStream;
import java.io.IOException;


public class Text extends AbstractType {
	protected String mydata;

	public Text() {
		mydata = "";
	}

	public Text(String str) {
		mydata = str;
	}

	public Text(DataInputStream input) throws IOException {
		int size = input.readUnsignedShort();
		byte[] bytes = new byte[size];
		input.readFully(bytes);
		try {
			mydata = new String(bytes, ENCODING);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean equals(String s) {
		return mydata.equalsIgnoreCase(s);
	}

	public boolean equals(Text s) {
		return mydata.equalsIgnoreCase(s.mydata);
	}

	public String getStringData() {
		return mydata;
	}

	public byte[] getRaw() {
		try {
			byte[] str = mydata.getBytes(ENCODING);
			byte[] ret = new byte[str.length + 2];
			integerToBytes(str.length, ret, 2, 0);
			copy(str, ret, 2);
			return ret;
		} catch (Exception e) {
			byte[] ret = new byte[2];
			integerToBytes(0, ret, 2, 0);
			return ret;
		}
	}

	public int size() {
		return getRaw().length;
	}

        @Override
	public String toString() {
		char c = 34;
		return super.toString() + "( " + c + mydata + c + " )";
	}
}
