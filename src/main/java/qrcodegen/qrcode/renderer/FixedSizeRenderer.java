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
package qrcodegen.qrcode.renderer;

import qrcodegen.tools.ImmutableDimension;

/**
 *
 * @author Stefan Ganzer
 */
public class FixedSizeRenderer extends AbstractSizeRenderer {

	public FixedSizeRenderer(ImmutableDimension sizeLimit) {
		super(sizeLimit);
	}

	public FixedSizeRenderer() {
	}

	@Override
	public ImmutableDimension calculateActualDimension(int qrWidth, int qrHeight, int multiple, int requestedOutputWidth, int requestedOutputHeight) {
		return new ImmutableDimension(requestedOutputWidth, requestedOutputHeight);
	}
}
