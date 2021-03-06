/*
 * Copyright (C) 2012 Stefan Ganzer
 *
 * This file is part of QRCodeGen.
 *
 * QRCodeGen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * QRCodeGen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package qrcodegen.qrcode;

import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;

/**
 *
 * @author Stefan Ganzer
 */
public class NullQRCode implements QRCodeInterface {

	@Override
	public Mode getMode() {
		return null;
	}

	@Override
	public Version getVersion() {
		return null;
	}

	@Override
	public int getVersionNumber() {
		return 0;
	}

	@Override
	public int getNumTotalBytes() {
		return 0;
	}

	@Override
	public int getNumDataBytes() {
		return 0;
	}

	@Override
	public int getNumECBytes() {
		return 0;
	}

	@Override
	public int getNumRSBlocks() {
		return 0;
	}
}
