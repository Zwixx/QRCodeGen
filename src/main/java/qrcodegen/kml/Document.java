/*
 * Copyright (C) 2013 Stefan Ganzer
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
package qrcodegen.kml;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Stefan Ganzer
 */
@XmlAccessorType( XmlAccessType.PUBLIC_MEMBER)
public class Document {

	private String name;
	private List<Placemark> placemarks = new ArrayList<Placemark>();
	private List<Folder> folders = new ArrayList<Folder>();
	private List<Document> documents = new ArrayList<Document>();

	public void setPlacemarks(List<Placemark> placemarks) {
		this.placemarks = placemarks;
	}

	@XmlElement( name = "Placemark")
	public List<Placemark> getPlacemarks() {
		return placemarks;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

	@XmlElement( name = "Folder")
	public List<Folder> getFolders() {
		return folders;
	}

	public void setDocuments(List<Document> d) {
		this.documents = d;
	}

	@XmlElement(name = "Document")
	public List<Document> getDocuments() {
		return documents;
	}

	public String getName() {
		return name;
	}

	public void setName(String s) {
		this.name = s;
	}

	@Override
	public String toString() {
		return name == null ? "Document" : name;
	}
}
